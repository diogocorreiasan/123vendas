package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.domain.Item;
import com.umdoistresvendas.salesapi.domain.Order;
import com.umdoistresvendas.salesapi.domain.Status;
import com.umdoistresvendas.salesapi.exception.QuantityItemsExceededException;
import com.umdoistresvendas.salesapi.usecase.*;
import com.umdoistresvendas.salesapi.utils.ItemLogger;
import com.umdoistresvendas.salesapi.utils.OrderLogger;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.umdoistresvendas.salesapi.utils.OrderLogger.logOrder;

@Slf4j
@Service
@AllArgsConstructor
public class CompanyRulesUseCaseImpl implements CompanyRulesUseCase {

    private final JoinItemsUseCase joinItemsUseCase;
    private final DiscountSelectorUseCase discountSelectorUseCase;
    private final CalculateItemTotalUseCase calculateItemTotalUseCase;
    private final CalculateOrderTotalUseCase calculateOrderTotalUseCase;

    @Override
    public Order execute(final Order order) {
        final var orderUpdated = joinItemsUseCase.execute(order);
        logOrder(orderUpdated);
        final var items = getList(orderUpdated);
        final var updatedOrder = orderUpdated.withItems(items);
        logOrder(updatedOrder);
        return calculateOrderTotalUseCase.execute(updatedOrder);
    }

    private List<Item> getList(final Order order) {
        return order.getItems()
                .stream()
                .filter(item -> !Status.CANCELLED.equals(item.getStatus()))
                .filter(this::discountCanBeApplied)
                .map(calculateItemTotalUseCase::execute)
                .map(this::getItemCalculateDiscount)
                .toList();
    }

    private Item getItemCalculateDiscount(final Item item) {
        log.info("[GET ITEM CALCULATE DISCOUNT]");
        ItemLogger.logItem(item);

        return Optional.of(discountSelectorUseCase.execute(item))
                .map(discount -> discount.execute(item))
                .orElse(item.toBuilder().status(Status.ERROR).build());
    }

    private boolean discountCanBeApplied(final Item item) {
        if (item.getAmount() > 20){
            throw new QuantityItemsExceededException("Quantity of items exceeded");
        }
        return Boolean.TRUE;
    }
}
