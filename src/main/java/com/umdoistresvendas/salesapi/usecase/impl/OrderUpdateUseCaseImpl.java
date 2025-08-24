package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.adapter.ItemAdapter;
import com.umdoistresvendas.salesapi.adapter.OrderAdapter;
import com.umdoistresvendas.salesapi.domain.Order;
import com.umdoistresvendas.salesapi.domain.Status;
import com.umdoistresvendas.salesapi.producer.OrderUpdateProducer;
import com.umdoistresvendas.salesapi.usecase.CompanyRulesUseCase;
import com.umdoistresvendas.salesapi.usecase.OrderUpdateUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderUpdateUseCaseImpl implements OrderUpdateUseCase {

    private final ItemAdapter itemAdapter;
    private final OrderAdapter orderAdapter;
    private final OrderUpdateProducer orderUpdateProducer;
    private final CompanyRulesUseCase companyRulesUseCase;

    @Override
    public Order execute(final Order order) {
        final var calculatedOrder = Optional.of(order)
                .map(companyRulesUseCase::execute)
                .map(orderRequest ->  orderRequest.withStatus(Status.CREATED))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.PRECONDITION_FAILED));

        final var orderSaved = orderAdapter.updateOrCreate(calculatedOrder);

        final var itemSaved = calculatedOrder
                .getItems()
                .stream()
                .map(item -> itemAdapter.updateOrSave(item, orderSaved.getId()))
                .toList();

        final var response = orderSaved.withItems(itemSaved);
        orderUpdateProducer.send(response);
        return response;
    }
}
