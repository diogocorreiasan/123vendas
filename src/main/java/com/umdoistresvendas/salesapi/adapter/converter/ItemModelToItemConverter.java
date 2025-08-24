package com.umdoistresvendas.salesapi.adapter.converter;

import com.umdoistresvendas.salesapi.domain.Item;
import com.umdoistresvendas.salesapi.repository.model.ItemModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ItemModelToItemConverter {

    private final ModelMapper modelMapper;

    public Item convert(final ItemModel item) {
        return modelMapper.map(item, Item.class);
    }
}
