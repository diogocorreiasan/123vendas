package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.adapter.OrderAdapter;
import com.umdoistresvendas.salesapi.domain.Order;
import com.umdoistresvendas.salesapi.usecase.OrderSearchByIdUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderSearchByIdUseCaseImpl implements OrderSearchByIdUseCase {

    private final OrderAdapter orderAdapter;

    @Override
    public Order execute(final Long orderNumber) {
        return orderAdapter.getOrder(orderNumber);
    }
}
