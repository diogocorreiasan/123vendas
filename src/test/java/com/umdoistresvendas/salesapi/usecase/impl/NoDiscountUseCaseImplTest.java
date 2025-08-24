package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.helper.HelperTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class NoDiscountUseCaseImplTest {

    @InjectMocks
    private NoDiscountUseCaseImpl noDiscountUseCase;

    @Test
    void testNoDiscount() {
        final var item = HelperTest.getItem();
        item.setAmount(2);
        item.setTotalValue(new BigDecimal("84.16"));

        final var response = noDiscountUseCase.execute(item);
        final var amount = noDiscountUseCase.accepted(item.getAmount());

        assertEquals(BigDecimal.ZERO, response.getDiscounts());
        assertEquals(BigDecimal.ZERO, response.getTotalPriceWithDiscount());
        assertTrue(amount);
    }
}