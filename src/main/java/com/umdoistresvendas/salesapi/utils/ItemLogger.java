package com.umdoistresvendas.salesapi.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.umdoistresvendas.salesapi.domain.Item;
import com.umdoistresvendas.salesapi.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemLogger {

    private static final Logger log = LoggerFactory.getLogger(ItemLogger.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void logItem(Item item) {
        try {
            String json = mapper.writeValueAsString(item);
            log.info("Item received: {}", json);
        } catch (JsonProcessingException e) {
            log.error("Error serializing item", e);
        }
    }
}
