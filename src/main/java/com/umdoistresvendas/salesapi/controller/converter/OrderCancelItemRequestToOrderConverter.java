package com.umdoistresvendas.salesapi.controller.converter;

import com.umdoistresvendas.salesapi.controller.request.OrderCancelItemRequest;
import com.umdoistresvendas.salesapi.domain.Order;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class OrderCancelItemRequestToOrderConverter {

    private final ModelMapper modelMapper;

    public Order convert(final OrderCancelItemRequest orderCancelItemRequest) {
        final var trace = UUID.randomUUID().toString().replace("-", "");
        final var order = modelMapper.map(orderCancelItemRequest, Order.class);
        order.setTraceId(trace);
        return order;
    }
}
