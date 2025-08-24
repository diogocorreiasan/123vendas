package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.adapter.OrderAdapter;
import com.umdoistresvendas.salesapi.domain.Status;
import com.umdoistresvendas.salesapi.producer.OrderCancelProducer;
import com.umdoistresvendas.salesapi.usecase.OrderCancelUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderCancelUseCaseImpl implements OrderCancelUseCase {

    private final OrderCancelProducer orderCancelProducer;
    private final OrderAdapter orderAdapter;

    @Override
    public void execute(final Long orderNumber) {
        final var order = orderAdapter.getOrder(orderNumber);
        order.setStatus(Status.CANCELLED);
        final var orderCanceled = orderAdapter.updateOrCreate(order);
        orderCancelProducer.send(orderCanceled);
    }
}
