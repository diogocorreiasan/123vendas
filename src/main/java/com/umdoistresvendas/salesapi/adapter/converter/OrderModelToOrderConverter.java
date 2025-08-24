package com.umdoistresvendas.salesapi.adapter.converter;

import com.umdoistresvendas.salesapi.domain.Order;
import com.umdoistresvendas.salesapi.repository.model.OrderModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderModelToOrderConverter {

    private final ModelMapper modelMapper;

    public Order convert(final OrderModel orderModel) {
        return modelMapper.map(orderModel, Order.class);
    }
}
