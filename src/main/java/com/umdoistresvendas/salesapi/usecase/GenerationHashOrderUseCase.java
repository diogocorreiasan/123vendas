package com.umdoistresvendas.salesapi.usecase;

import com.umdoistresvendas.salesapi.domain.Order;

public interface GenerationHashOrderUseCase {
    Order execute(final Order order);
}
