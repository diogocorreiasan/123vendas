package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.adapter.OrderAdapter;
import com.umdoistresvendas.salesapi.domain.Order;
import com.umdoistresvendas.salesapi.exception.DuplicateOrderException;
import com.umdoistresvendas.salesapi.usecase.OrderValidUseCase;
import com.umdoistresvendas.salesapi.utils.OrderLogger;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.umdoistresvendas.salesapi.utils.OrderLogger.logOrder;

@Slf4j
@Component
@AllArgsConstructor
public class OrderValidUseCaseImpl implements OrderValidUseCase {

    private final OrderAdapter orderAdapter;

    @Override
    public Order execute(final Order order) {
        log.info("[ORDER VALIDITY ALREADY EXISTS]");
        logOrder(order);

        if(orderAdapter.getHash(order.getHash())){
            throw new DuplicateOrderException("Duplicate Order");
        }

        return order;
    }
}
