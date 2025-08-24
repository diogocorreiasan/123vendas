package com.umdoistresvendas.salesapi.controller.converter;

import com.umdoistresvendas.salesapi.controller.request.ItemCreateRequest;
import com.umdoistresvendas.salesapi.controller.request.OrderCreateRequest;
import com.umdoistresvendas.salesapi.domain.Item;
import com.umdoistresvendas.salesapi.domain.Order;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class OrderCreateRequestToOrderConverter {

    private final ModelMapper modelMapper;

    public Order convert(final OrderCreateRequest orderCreateRequest) {
        final var trace = UUID.randomUUID().toString().replace("-", "");
        return Order
                .builder()
                .clientExternalId(orderCreateRequest.getClientExternalId())
                .clientExternalName(orderCreateRequest.getClientExternalName())
                .branch(orderCreateRequest.getBranch())
                .traceId(trace)
                .items(getItems(orderCreateRequest.getItems()))
                .build();
    }

    private List<Item> getItems(@NonNull final List<ItemCreateRequest> items) {
        return items
                .stream()
                .map(this::getItem)
                .toList();
    }

    private Item getItem(final ItemCreateRequest itemCreateRequest) {
        return Item
                .builder()
                .price(itemCreateRequest.getPrice())
                .amount(itemCreateRequest.getAmount())
                .productExternalId(itemCreateRequest.getProductExternalId())
                .productExternalName(itemCreateRequest.getProductExternalName())
                .build();
    }
}
