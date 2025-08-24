package com.umdoistresvendas.salesapi.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.umdoistresvendas.salesapi.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderLogger {
    private static final Logger log = LoggerFactory.getLogger(OrderLogger.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void logOrder(Order order) {
        try {
            String json = mapper.writeValueAsString(order);
            log.info("Order received: {}", json);
        } catch (JsonProcessingException e) {
            log.error("Error serializing order", e);
        }
    }
}
