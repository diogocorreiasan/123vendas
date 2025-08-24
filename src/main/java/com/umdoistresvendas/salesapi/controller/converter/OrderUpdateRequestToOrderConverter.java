package com.umdoistresvendas.salesapi.controller.converter;

import com.umdoistresvendas.salesapi.controller.request.OrderUpdateRequest;
import com.umdoistresvendas.salesapi.domain.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class OrderUpdateRequestToOrderConverter {

    private final ModelMapper modelMapper;

    public Order convert(final OrderUpdateRequest orderUpdateRequest) {
        final var trace = UUID.randomUUID().toString().replace("-", "");
        final var order = modelMapper.map(orderUpdateRequest, Order.class);
        order.setTraceId(trace);
        return order;
    }
}
