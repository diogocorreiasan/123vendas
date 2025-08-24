package com.umdoistresvendas.salesapi.usecase;

import com.umdoistresvendas.salesapi.domain.Item;

public interface DiscountsUseCase {
    boolean accepted(final int amount);
    Item execute(final Item item);
}
