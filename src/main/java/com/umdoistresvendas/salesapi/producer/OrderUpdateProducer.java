package com.umdoistresvendas.salesapi.producer;

import com.umdoistresvendas.salesapi.domain.Order;

public interface OrderUpdateProducer {
    void send(final Order order);
}
