package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.domain.Item;
import com.umdoistresvendas.salesapi.domain.Order;
import com.umdoistresvendas.salesapi.usecase.JoinItemsUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JoinItemsUseCaseImpl implements JoinItemsUseCase {

    @Override
    public Order execute(final Order order) {
        final var items = new ArrayList<>(order.getItems().stream()
                .collect(Collectors.toMap(
                        Item::getProductExternalId,
                        item -> item,
                        (item1, item2) -> {
                            item1.setAmount(item1.getAmount() + item2.getAmount());
                            return item1;
                        }
                ))
                .values());

        return order.withItems(items);
    }
}
