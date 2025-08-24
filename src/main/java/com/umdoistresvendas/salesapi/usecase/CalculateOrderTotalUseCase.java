package com.umdoistresvendas.salesapi.usecase;

import com.umdoistresvendas.salesapi.domain.Order;

public interface CalculateOrderTotalUseCase {
    Order execute(final Order order);
}
