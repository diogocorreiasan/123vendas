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
class TwentyPercentDiscountUseCaseImplTest {

    @InjectMocks
    private TwentyPercentDiscountUseCaseImpl twentyPercentDiscountUseCase;

    @Test
    void testTwentyPercentDiscount() {

        final var item = HelperTest.getItem();
        item.setAmount(10);
        item.setTotalValue(new BigDecimal("105.20"));

        final var response = twentyPercentDiscountUseCase.execute(item);
        final var amount = twentyPercentDiscountUseCase.accepted(item.getAmount());

        assertEquals(new BigDecimal("0.20"), response.getDiscounts());
        assertEquals(new BigDecimal("84.1600"), response.getTotalPriceWithDiscount());
        assertTrue(amount);
    }
}