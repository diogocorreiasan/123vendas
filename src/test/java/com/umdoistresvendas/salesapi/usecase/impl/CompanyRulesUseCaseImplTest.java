package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.helper.HelperTest;
import com.umdoistresvendas.salesapi.usecase.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CompanyRulesUseCaseImplTest {

    @InjectMocks
    private CompanyRulesUseCaseImpl companyRulesUseCase;
    @Mock
    private JoinItemsUseCase joinItemsUseCase;
    @Mock
    private DiscountSelectorUseCase discountSelectorUseCase;
    @Mock
    private CalculateItemTotalUseCase calculateItemTotalUseCase;
    @Mock
    private CalculateOrderTotalUseCase calculateOrderTotalUseCase;

    @Test
    void testCompanyRules() {

        final var order = HelperTest.getOrder();
        order.setTotalValue(new BigDecimal("500"));
        final var item = HelperTest.getItem();
        item.setAmount(8);
        item.setTotalValue(new BigDecimal("42.08"));

        when(joinItemsUseCase.execute(any())).thenReturn(order);
        when(discountSelectorUseCase.execute(any())).thenReturn(new TenPercentDiscountUseCaseImpl());
        when(calculateItemTotalUseCase.execute(any())).thenReturn(item);
        when(calculateOrderTotalUseCase.execute(any())).thenReturn(order);

        final var response = companyRulesUseCase.execute(order);

        Assertions.assertNotNull(response);
    }
}