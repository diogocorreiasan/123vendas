package com.umdoistresvendas.salesapi.producer;

import com.umdoistresvendas.salesapi.domain.Item;

public interface ItemCancelProducer {
    void send(final Item item);
}
