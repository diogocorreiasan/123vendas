package com.umdoistresvendas.salesapi.adapter.converter;

import com.umdoistresvendas.salesapi.domain.Order;
import com.umdoistresvendas.salesapi.repository.model.OrderModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderToOrderModelConverter {

    private final ModelMapper modelMapper;

    public OrderModel convert(final Order order) {
        final var model = modelMapper.map(order, OrderModel.class);
        model.setId(null);
        model.setItems(null);
        return model;
    }
}
