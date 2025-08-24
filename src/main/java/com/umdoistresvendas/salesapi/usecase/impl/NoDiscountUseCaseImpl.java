package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.domain.Item;
import com.umdoistresvendas.salesapi.usecase.DiscountsUseCase;
import com.umdoistresvendas.salesapi.utils.ItemLogger;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
@AllArgsConstructor
public class NoDiscountUseCaseImpl implements DiscountsUseCase {

    private static final int FOUR = 4;

    @Override
    public boolean accepted(final int amount) {
        return amount < FOUR;
    }

    @Override
    public Item execute(final Item item) {
        log.info("[NO DISCOUNT]");
        ItemLogger.logItem(item);

        return item.toBuilder()
                .discounts(BigDecimal.ZERO)
                .totalPriceWithDiscount(BigDecimal.ZERO)
                .build();
    }
}
