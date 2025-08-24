package com.umdoistresvendas.salesapi.usecase;


import com.umdoistresvendas.salesapi.domain.Order;

public interface OrderValidUseCase {
    Order execute(final Order order);
}
