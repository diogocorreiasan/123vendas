package com.umdoistresvendas.salesapi.usecase;

import com.umdoistresvendas.salesapi.domain.Item;

public interface CalculateItemTotalUseCase {
    Item execute(final Item item);
}
