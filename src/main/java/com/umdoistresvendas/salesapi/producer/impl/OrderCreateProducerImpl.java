package com.umdoistresvendas.salesapi.producer.impl;

import com.umdoistresvendas.salesapi.domain.Order;
import com.umdoistresvendas.salesapi.producer.OrderCreateProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class OrderCreateProducerImpl implements OrderCreateProducer {

    private final ApplicationEventPublisher publisher;

    @Override
    public void send(final Order order) {
        log.info("[OrderCreateProducerImpl][Created Order]");
        publisher.publishEvent(order);
    }
}
