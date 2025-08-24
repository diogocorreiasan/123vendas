package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.helper.HelperTest;
import com.umdoistresvendas.salesapi.usecase.DiscountsUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@ExtendWith(SpringExtension.class)
class DiscountSelectorUseCaseImplTest {

    @InjectMocks
    private DiscountSelectorUseCaseImpl discountSelectorUseCase;

    @Mock
    private List<DiscountsUseCase> discountsUseCases;

    @Test
    void testDiscountSelector() {
        final var item = HelperTest.getItem();
        item.setAmount(10);
        item.setTotalValue(new BigDecimal("105.52"));

        final var response = discountSelectorUseCase.execute(item);
        final var discount = response.execute(item);
        assertNotNull(discount);
        assertEquals(new BigDecimal("84.4160"), discount.getTotalPriceWithDiscount());
    }
}