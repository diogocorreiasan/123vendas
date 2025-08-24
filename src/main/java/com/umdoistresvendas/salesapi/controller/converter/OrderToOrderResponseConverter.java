package com.umdoistresvendas.salesapi.controller.converter;


import com.umdoistresvendas.salesapi.controller.response.OrderResponse;
import com.umdoistresvendas.salesapi.domain.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class OrderToOrderResponseConverter {

    private final ModelMapper modelMapper;

    public OrderResponse converter(final Order response) {
        return modelMapper.map(response, OrderResponse.class);
    }
}
