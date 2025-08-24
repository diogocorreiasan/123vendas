package com.umdoistresvendas.salesapi.adapter;

import com.umdoistresvendas.salesapi.domain.Item;


public interface ItemAdapter {
    Item updateOrSave(final Item item, final Long id);
}
