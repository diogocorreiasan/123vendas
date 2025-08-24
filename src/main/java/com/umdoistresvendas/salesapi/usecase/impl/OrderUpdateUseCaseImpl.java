package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.adapter.ItemAdapter;
import com.umdoistresvendas.salesapi.adapter.OrderAdapter;
import com.umdoistresvendas.salesapi.domain.Order;
import com.umdoistresvendas.salesapi.domain.Status;
import com.umdoistresvendas.salesapi.producer.OrderUpdateProducer;
import com.umdoistresvendas.salesapi.usecase.CompanyRulesUseCase;
import com.umdoistresvendas.salesapi.usecase.OrderUpdateUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static com.umdoistresvendas.salesapi.utils.OrderLogger.logOrder;

@Slf4j
@Service
@AllArgsConstructor
public class OrderUpdateUseCaseImpl implements OrderUpdateUseCase {

    private final ItemAdapter itemAdapter;
    private final OrderAdapter orderAdapter;
    private final OrderUpdateProducer orderUpdateProducer;
    private final CompanyRulesUseCase companyRulesUseCase;

    @Override
    public Order execute(final Order order) {
        log.info("[OrderUpdateUseCaseImpl][Update]");
        final var calculatedOrder = Optional.of(order)
                .map(companyRulesUseCase::execute)
                .map(orderRequest ->  orderRequest.withStatus(Status.CREATED))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.PRECONDITION_FAILED));

        log.info("[OrderUpdateUseCaseImpl][Update][Calculated Order]");
        logOrder(order);
        final var orderSaved = orderAdapter.updateOrCreate(calculatedOrder);
        log.info("[OrderUpdateUseCaseImpl][Update][Order Saved]");
        final var itemSaved = calculatedOrder
                .getItems()
                .stream()
                .map(item -> itemAdapter.updateOrSave(item, orderSaved.getId()))
                .toList();

        log.info("[OrderUpdateUseCaseImpl][Update][Items Saved]");
        final var response = orderSaved.withItems(itemSaved);
        orderUpdateProducer.send(response);
        return response;
    }
}
