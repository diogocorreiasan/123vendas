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
public class TenPercentDiscountUseCaseImpl implements DiscountsUseCase {

    private static final String DISCOUNT = "0.10";
    private static final int TEN = 10;
    private static final int FOUR = 4;

    @Override
    public boolean accepted(final int amount) {
        return amount > FOUR && amount < TEN;
    }

    @Override
    public Item execute(final Item item) {
        log.info("[TEN PERCENT DISCOUNT]");
        ItemLogger.logItem(item);

        final var discount = item.getTotalValue().multiply(new BigDecimal(DISCOUNT));
        final var value = item.getTotalValue().subtract(discount);
        return item.toBuilder()
                .totalPriceWithDiscount(value)
                .discounts(new BigDecimal(DISCOUNT))
                .build();
    }
}
