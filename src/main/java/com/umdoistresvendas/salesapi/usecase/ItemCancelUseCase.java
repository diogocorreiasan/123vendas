package com.umdoistresvendas.salesapi.usecase;

import com.umdoistresvendas.salesapi.domain.Order;


public interface ItemCancelUseCase {
    void execute(final Order order);
}
