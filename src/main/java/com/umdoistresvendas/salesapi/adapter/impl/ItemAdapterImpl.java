package com.umdoistresvendas.salesapi.adapter.impl;

import com.umdoistresvendas.salesapi.adapter.ItemAdapter;
import com.umdoistresvendas.salesapi.adapter.converter.ItemModelToItemConverter;
import com.umdoistresvendas.salesapi.adapter.converter.ItemToItemModelConverter;
import com.umdoistresvendas.salesapi.domain.Item;
import com.umdoistresvendas.salesapi.repository.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class ItemAdapterImpl implements ItemAdapter {

    private final ItemRepository itemRepository;
    private final ItemToItemModelConverter itemToItemModelConverter;
    private final ItemModelToItemConverter itemModelToItemConverter;

    @Override
    public Item updateOrSave(final Item item, final Long id) {
        return Optional.ofNullable(item.getId())
                .filter(itemId -> itemRepository.findById(itemId).isPresent())
                .map(itemModel -> itemToItemModelConverter.convert(item, id))
                .map(itemRepository::save)
                .map(itemModelToItemConverter::convert)
                .orElseGet(() -> save(item, id));
    }

    private Item save(final Item item, final Long id) {
        final var model = itemToItemModelConverter.convert(item, id);
        final var itemSaved = itemRepository.save(model);
        return itemModelToItemConverter.convert(itemSaved);
    }
}
