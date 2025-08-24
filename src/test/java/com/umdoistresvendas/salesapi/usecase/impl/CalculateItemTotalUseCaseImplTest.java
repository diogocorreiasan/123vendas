package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.helper.HelperTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
class CalculateItemTotalUseCaseImplTest {

    @InjectMocks
    private CalculateItemTotalUseCaseImpl calculateItemTotalUseCase;

    @Test
    void testCalculateItem(){

        final var item = HelperTest.getItem();

        final var response = calculateItemTotalUseCase.execute(item);

        Assertions.assertEquals(new BigDecimal("42.08"), response.getTotalValue());
        Assertions.assertNull(response.getTotalPriceWithDiscount());
        Assertions.assertNull(response.getDiscounts());
    }
}