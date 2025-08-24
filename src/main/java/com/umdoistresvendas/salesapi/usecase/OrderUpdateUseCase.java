package com.umdoistresvendas.salesapi.usecase;

import com.umdoistresvendas.salesapi.domain.Order;

public interface OrderUpdateUseCase {
    Order execute(final Order order);
}
