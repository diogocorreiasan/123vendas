package com.umdoistresvendas.salesapi.usecase;

import com.umdoistresvendas.salesapi.domain.Order;

public interface JoinItemsUseCase {
    Order execute(final Order order);
}
