package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.adapter.ItemAdapter;
import com.umdoistresvendas.salesapi.adapter.OrderAdapter;
import com.umdoistresvendas.salesapi.helper.HelperTest;
import com.umdoistresvendas.salesapi.producer.OrderCreateProducer;
import com.umdoistresvendas.salesapi.usecase.CompanyRulesUseCase;
import com.umdoistresvendas.salesapi.usecase.GenerationHashOrderUseCase;
import com.umdoistresvendas.salesapi.usecase.OrderValidUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class OrderCreateUseCaseImplTest {

    @InjectMocks
    private OrderCreateUseCaseImpl orderCreateUseCase;
    @Mock
    private ItemAdapter itemAdapter;
    @Mock
    private OrderAdapter orderAdapter;
    @Mock
    private OrderCreateProducer orderCreateProducer;
    @Mock
    private OrderValidUseCase orderValidUseCase;
    @Mock
    private CompanyRulesUseCase companyRulesUseCase;
    @Mock
    private GenerationHashOrderUseCase generationHashOrderUseCase;

    @Test
    void testMustCreateApplyRulesPersistItemsPublishEvent() {

        final var order = HelperTest.getOrder();
        final var item = HelperTest.getItem();

        when(generationHashOrderUseCase.execute(any())).thenReturn(order.toBuilder().hash("41646sds").build());
        when(orderValidUseCase.execute(any())).thenReturn(order.toBuilder().hash("41646sds").build());
        when(companyRulesUseCase.execute(any())).thenReturn(order.toBuilder().hash("41646sds").totalValue(new BigDecimal("1212.23")).build());
        when(orderAdapter.updateOrCreate(any())).thenReturn(order.toBuilder().hash("41646sds").id(1L).totalValue(new BigDecimal("1212.23")).build());
        when(itemAdapter.updateOrSave(any(), anyLong())).thenReturn(item);

        final var response = orderCreateUseCase.execute(order);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(2L, response.getItems().size());
    }

}