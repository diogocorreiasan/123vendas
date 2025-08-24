package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.adapter.ItemAdapter;
import com.umdoistresvendas.salesapi.adapter.OrderAdapter;
import com.umdoistresvendas.salesapi.domain.Order;
import com.umdoistresvendas.salesapi.domain.Status;
import com.umdoistresvendas.salesapi.producer.OrderCreateProducer;
import com.umdoistresvendas.salesapi.usecase.CompanyRulesUseCase;
import com.umdoistresvendas.salesapi.usecase.GenerationHashOrderUseCase;
import com.umdoistresvendas.salesapi.usecase.OrderCreateUseCase;
import com.umdoistresvendas.salesapi.usecase.OrderValidUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderCreateUseCaseImpl implements OrderCreateUseCase {

    private final ItemAdapter itemAdapter;
    private final OrderAdapter orderAdapter;
    private final OrderCreateProducer orderCreateProducer;
    private final OrderValidUseCase orderValidUseCase;
    private final CompanyRulesUseCase companyRulesUseCase;
    private final GenerationHashOrderUseCase generationHashOrderUseCase;

    @Override
    public Order execute(final Order order) {
        final var orderWithHash = Optional.of(generationHashOrderUseCase.execute(order))
                .map(orderValidUseCase::execute)
                .map(companyRulesUseCase::execute)
                .map(this::setNumberOrder)
                .map(orderRequest -> orderRequest.withStatus(Status.CREATED))
                .map(orderRequest -> orderRequest.withOrderDate(LocalDate.now()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.PRECONDITION_FAILED));

        final var orderSaved = orderAdapter.updateOrCreate(orderWithHash);
        final var items = orderWithHash.getItems()
                .stream()
                .map(item -> itemAdapter.updateOrSave(item, orderSaved.getId()))
                .toList();

        final var response = orderSaved.withItems(items);
        orderCreateProducer.send(response);
        return response;
    }

    private Order setNumberOrder(final Order orderRequest) {
        final var uuid = UUID.randomUUID().toString().replaceAll("[^0-9]", "");
        final var number = Long.parseLong(uuid.substring(0, 8));
        return orderRequest.withOrderNumber(number);
    }
}
