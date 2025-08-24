package com.umdoistresvendas.salesapi.usecase.impl;


import com.umdoistresvendas.salesapi.domain.Item;
import com.umdoistresvendas.salesapi.domain.Order;
import com.umdoistresvendas.salesapi.usecase.GenerationHashOrderUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Comparator;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class GenerationHashOrderUseCaseImpl implements GenerationHashOrderUseCase {

    private static final String SHA_256 = "SHA-256";
    private static final String BAR = "|";

    @Override
    public Order execute(final Order order) {
        log.info("[GENERATING THE HASH]");
        try {
            final var digest = MessageDigest.getInstance(SHA_256);

            final var raw = order.getItems().stream()
                    .sorted(Comparator.comparing(Item::getProductExternalName))
                    .map(item -> item.getProductExternalName() + item.getPrice() + item.getAmount())
                    .collect(Collectors.joining(BAR));

            byte[] hashBytes = digest.digest(raw.getBytes(StandardCharsets.UTF_8));
            order.setHash(Base64.getEncoder().encodeToString(hashBytes));
            return order;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating order hash", e);
        }
    }
}
