package com.umdoistresvendas.salesapi.producer;

import com.umdoistresvendas.salesapi.domain.Order;

public interface OrderCreateProducer {
    void send(final Order order);
}
