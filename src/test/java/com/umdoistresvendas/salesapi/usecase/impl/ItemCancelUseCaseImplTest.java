package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.adapter.ItemAdapter;
import com.umdoistresvendas.salesapi.adapter.OrderAdapter;
import com.umdoistresvendas.salesapi.domain.Item;
import com.umdoistresvendas.salesapi.domain.Order;
import com.umdoistresvendas.salesapi.helper.HelperTest;
import com.umdoistresvendas.salesapi.producer.ItemCancelProducer;
import com.umdoistresvendas.salesapi.producer.OrderCancelProducer;
import com.umdoistresvendas.salesapi.producer.OrderUpdateProducer;
import com.umdoistresvendas.salesapi.usecase.CompanyRulesUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ItemCancelUseCaseImplTest {

    @InjectMocks
    private ItemCancelUseCaseImpl itemCancelUseCase;

    @Mock
    private ItemAdapter itemAdapter;
    @Mock
    private OrderAdapter orderAdapter;
    @Mock
    private ItemCancelProducer itemCancelProducer;
    @Mock
    private CompanyRulesUseCase companyRulesUseCase;

    @Test
    void testMustCancelApplyRulesPersistItemsPublishEvent() {

        final var order = HelperTest.getOrder();
        final var item = HelperTest.getItem();

        when(companyRulesUseCase.execute(any())).thenReturn(order.toBuilder().hash("41646sds").totalValue(new BigDecimal("1212.23")).build());
        when(orderAdapter.getOrder(anyLong())).thenReturn(order.toBuilder().hash("41646sds").id(1L).totalValue(new BigDecimal("1212.23")).build());
        when(orderAdapter.updateOrCreate(any())).thenReturn(order.toBuilder().hash("41646sds").id(1L).totalValue(new BigDecimal("1212.23")).build());
        when(itemAdapter.updateOrSave(any(), anyLong())).thenReturn(item);

        itemCancelUseCase.execute(order);
        verify(companyRulesUseCase, times(1)).execute(any());

    }

}