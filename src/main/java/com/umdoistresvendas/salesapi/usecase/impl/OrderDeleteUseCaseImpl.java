package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.adapter.OrderAdapter;
import com.umdoistresvendas.salesapi.usecase.OrderDeleteUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderDeleteUseCaseImpl implements OrderDeleteUseCase {

    private final OrderAdapter orderAdapter;

    @Override
    public void execute(final Long orderNumber) {
        final var order = orderAdapter.getOrder(orderNumber);
        orderAdapter.delete(order);
    }
}
