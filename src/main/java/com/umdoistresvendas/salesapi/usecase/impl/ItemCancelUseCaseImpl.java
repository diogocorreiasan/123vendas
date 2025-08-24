package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.adapter.ItemAdapter;
import com.umdoistresvendas.salesapi.adapter.OrderAdapter;
import com.umdoistresvendas.salesapi.domain.Item;
import com.umdoistresvendas.salesapi.domain.Order;
import com.umdoistresvendas.salesapi.domain.Status;
import com.umdoistresvendas.salesapi.producer.ItemCancelProducer;
import com.umdoistresvendas.salesapi.usecase.CompanyRulesUseCase;
import com.umdoistresvendas.salesapi.usecase.ItemCancelUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemCancelUseCaseImpl implements ItemCancelUseCase {

    private final OrderAdapter orderAdapter;
    private final ItemAdapter itemAdapter;
    private final ItemCancelProducer itemCancelProducer;
    private final CompanyRulesUseCase companyRulesUseCase;

    @Override
    public void execute(final Order order) {
        final var ids = order.getItems()
                .stream()
                .map(Item::getId)
                .toList();

        final var response = orderAdapter.getOrder(order.getOrderNumber());

        final var items = response
                .getItems()
                .stream()
                .map(item -> setStatus(item, ids))
                .map(item -> itemAdapter.updateOrSave(item, response.getId()))
                .toList();

        final var newOrder = companyRulesUseCase.execute(response.withItems(items));

        final var orderSaved = orderAdapter.updateOrCreate(newOrder);
        newOrder.getItems()
                .stream()
                .map(item -> itemAdapter.updateOrSave(item, orderSaved.getId()))
                .forEach(itemCancelProducer::send);
    }

    private Item setStatus(final Item item, final List<Long> ids) {
        if (ids.contains(item.getId())){
            return item.withStatus(Status.CANCELLED);
        }
        return item;
    }
}
