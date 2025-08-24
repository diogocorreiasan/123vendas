package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.adapter.OrderAdapter;
import com.umdoistresvendas.salesapi.helper.HelperTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class OrderDeleteUseCaseImplTest {

    @InjectMocks
    private OrderDeleteUseCaseImpl orderDeleteUseCase;

    @Mock
    private OrderAdapter orderAdapter;

    @Test
    void testUpdateOrder() {

        final var order = HelperTest.getOrder();

        when(orderAdapter.getOrder(1233L)).thenReturn(order);

        doNothing().when(orderAdapter).delete(any());

        orderDeleteUseCase.execute(1233L);

        verify(orderAdapter).getOrder(1233L);
        verify(orderAdapter).delete(order);
        verifyNoMoreInteractions(orderAdapter);
    }
}