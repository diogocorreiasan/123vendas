package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.domain.Item;
import com.umdoistresvendas.salesapi.domain.Status;
import com.umdoistresvendas.salesapi.usecase.CalculateItemTotalUseCase;
import com.umdoistresvendas.salesapi.utils.ItemLogger;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.util.Objects.nonNull;
import static java.util.Optional.of;

@Slf4j
@Component
@AllArgsConstructor
public class CalculateItemTotalUseCaseImpl implements CalculateItemTotalUseCase {

    @Override
    public Item execute(final Item item) {

        log.info("[CALCULATE ITEM]");
        ItemLogger.logItem(item);

        final var price = of(item)
                .stream()
                .filter(this::isValid)
                .map(CalculateItemTotalUseCaseImpl::getMultiply)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return item.toBuilder().totalValue(price).status(Status.SUCCESS).build();
    }

    private static BigDecimal getMultiply(final Item request) {
        return request.getPrice().multiply(BigDecimal.valueOf(request.getAmount()));
    }

    private boolean isValid(final Item item) {
        return nonNull(item.getPrice()) && nonNull(item.getAmount());
    }
}
