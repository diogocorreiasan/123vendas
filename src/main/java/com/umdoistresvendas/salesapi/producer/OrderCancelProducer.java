package com.umdoistresvendas.salesapi.producer;

import com.umdoistresvendas.salesapi.domain.Order;

public interface OrderCancelProducer {
    void send(final Order order);
}
