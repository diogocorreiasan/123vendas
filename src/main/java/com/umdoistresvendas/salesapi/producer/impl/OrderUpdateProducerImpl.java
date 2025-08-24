package com.umdoistresvendas.salesapi.producer.impl;

import com.umdoistresvendas.salesapi.domain.Order;
import com.umdoistresvendas.salesapi.producer.OrderUpdateProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class OrderUpdateProducerImpl implements OrderUpdateProducer {

    private final ApplicationEventPublisher publisher;

    @Override
    public void send(final Order order) {
        log.info("[OrderUpdateProducerImpl][Updated Order]");
        publisher.publishEvent(order);
    }
}
