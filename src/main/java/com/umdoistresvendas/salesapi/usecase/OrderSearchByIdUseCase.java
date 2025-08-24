package com.umdoistresvendas.salesapi.usecase;

import com.umdoistresvendas.salesapi.domain.Order;

public interface OrderSearchByIdUseCase {
    Order execute(final Long orderNumber);
}
