package com.umdoistresvendas.salesapi.usecase;

import com.umdoistresvendas.salesapi.domain.Item;

public interface DiscountSelectorUseCase {
    DiscountsUseCase execute(final Item item);
}
