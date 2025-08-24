package com.umdoistresvendas.salesapi.usecase;

import com.umdoistresvendas.salesapi.domain.Order;

public interface CompanyRulesUseCase {
    Order execute(final Order order);
}
