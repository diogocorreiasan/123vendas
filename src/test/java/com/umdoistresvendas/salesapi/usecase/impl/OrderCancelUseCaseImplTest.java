package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.adapter.OrderAdapter;
import com.umdoistresvendas.salesapi.helper.HelperTest;
import com.umdoistresvendas.salesapi.producer.OrderCancelProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class OrderCancelUseCaseImplTest {

    @InjectMocks
    private OrderCancelUseCaseImpl orderCancelUseCase;
    @Mock
    private OrderCancelProducer orderCancelProducer;
    @Mock
    private OrderAdapter orderAdapter;

    @Test
    void testOrderCancelUseCase() {

        final var order = HelperTest.getOrder();
        order.setOrderNumber(466L);

        when(orderAdapter.getOrder(466L)).thenReturn(order);
        when(orderAdapter.updateOrCreate(any())).thenReturn(order);

        orderCancelUseCase.execute(466L);
    }

}