package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.helper.HelperTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class TenPercentDiscountUseCaseImplTest {

    @InjectMocks
    private TenPercentDiscountUseCaseImpl tenPercentDiscountUseCase;

    @Test
    void testTenPercentDiscount() {

        final var item = HelperTest.getItem();
        item.setAmount(8);
        item.setTotalValue(new BigDecimal("84.16"));

        final var response = tenPercentDiscountUseCase.execute(item);
        final var amount = tenPercentDiscountUseCase.accepted(item.getAmount());

        assertEquals(new BigDecimal("0.10"), response.getDiscounts());
        assertEquals(new BigDecimal("75.7440"), response.getTotalPriceWithDiscount());
        assertTrue(amount);
    }
}