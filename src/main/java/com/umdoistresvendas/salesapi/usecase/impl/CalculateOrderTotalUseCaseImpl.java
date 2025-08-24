package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.domain.Item;
import com.umdoistresvendas.salesapi.domain.Order;
import com.umdoistresvendas.salesapi.usecase.CalculateOrderTotalUseCase;
import com.umdoistresvendas.salesapi.utils.ItemLogger;
import com.umdoistresvendas.salesapi.utils.OrderLogger;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Slf4j
@Component
@AllArgsConstructor
public class CalculateOrderTotalUseCaseImpl implements CalculateOrderTotalUseCase {

    @Override
    public Order execute(final Order order) {
        log.info("[CALCULATE ORDER TOTAL]");
        OrderLogger.logOrder(order);

        final var price = order.getItems()
                .stream()
                .map(this::calculate)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_DOWN);

        return order.toBuilder().totalValue(price).build();
    }

    private BigDecimal calculate(final Item item) {
        if (Objects.nonNull(item.getTotalPriceWithDiscount()) &&
                BigDecimal.ZERO.compareTo(item.getTotalPriceWithDiscount()) > 0) {
            return item.getTotalPriceWithDiscount();
        }
        return item.getTotalValue();
    }
}
