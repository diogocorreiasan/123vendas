package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.helper.HelperTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
class CalculateOrderTotalUseCaseImplTest {

    @InjectMocks
    private CalculateOrderTotalUseCaseImpl calculateOrderTotalUseCase;

    @Test
    void testCalculateOrderTotal() {
        final var order = HelperTest.getOrder();

        final var response = calculateOrderTotalUseCase.execute(order);

        Assertions.assertEquals(new BigDecimal("65.28"), response.getTotalValue());
    }
}