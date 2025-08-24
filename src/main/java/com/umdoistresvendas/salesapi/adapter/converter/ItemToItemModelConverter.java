package com.umdoistresvendas.salesapi.adapter.converter;

import com.umdoistresvendas.salesapi.domain.Item;
import com.umdoistresvendas.salesapi.repository.model.ItemModel;
import com.umdoistresvendas.salesapi.repository.model.OrderModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ItemToItemModelConverter {

    private final ModelMapper modelMapper;

    public ItemModel convert(final Item item, final Long id) {
        final var model = modelMapper.map(item, ItemModel.class);
        model.setOrder(OrderModel.builder().id(id).build());
        return model;
    }
}
