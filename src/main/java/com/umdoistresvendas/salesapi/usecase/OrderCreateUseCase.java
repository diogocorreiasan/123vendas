package com.umdoistresvendas.salesapi.usecase;

import com.umdoistresvendas.salesapi.domain.Order;

public interface OrderCreateUseCase {
    Order execute(final Order order);
}
