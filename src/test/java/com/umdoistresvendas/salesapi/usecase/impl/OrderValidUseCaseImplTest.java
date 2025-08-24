package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.adapter.OrderAdapter;
import com.umdoistresvendas.salesapi.helper.HelperTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class OrderValidUseCaseImplTest {

    @InjectMocks
    private OrderValidUseCaseImpl orderValidUseCase;

    @Mock
    private OrderAdapter orderAdapter;

    @Test
    void testValidateDuplicateOrder(){

        final var order = HelperTest.getOrder();
        order.setHash("ggggggggg");

        when(orderAdapter.getHash(anyString())).thenReturn(Boolean.FALSE);

        final var response = orderValidUseCase.execute(order);

        assertNotNull(response);
    }

    @Test
    void testValidateDuplicateOrderException(){

        final var order = HelperTest.getOrder();
        order.setHash("ggggggggg");

        when(orderAdapter.getHash(anyString())).thenReturn(Boolean.TRUE);

        assertThrows(RuntimeException.class, () -> {
            orderValidUseCase.execute(order);
        });
    }
}