package com.umdoistresvendas.salesapi.producer.impl;

import com.umdoistresvendas.salesapi.domain.Item;
import com.umdoistresvendas.salesapi.producer.ItemCancelProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ItemCancelProducerImpl implements ItemCancelProducer {

    private final ApplicationEventPublisher publisher;

    @Override
    public void send(final Item item) {
        log.info("[ItemCancelProducerImpl][Canceled Item]");
        publisher.publishEvent(item);
    }
}
