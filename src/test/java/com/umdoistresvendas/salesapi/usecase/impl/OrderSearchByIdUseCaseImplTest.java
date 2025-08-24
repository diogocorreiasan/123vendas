package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.adapter.OrderAdapter;
import com.umdoistresvendas.salesapi.helper.HelperTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class OrderSearchByIdUseCaseImplTest {

    @InjectMocks
    private OrderSearchByIdUseCaseImpl orderSearchByIdUseCase;

    @Mock
    private OrderAdapter orderAdapter;

    @Test
    void testUpdateOrder() {

        final var order = HelperTest.getOrder();

        when(orderAdapter.getOrder(1233L)).thenReturn(order);

        final var response = orderSearchByIdUseCase.execute(1233L);

        assertNotNull(response);

        Assertions.assertEquals("José Da Silva", response.getClientExternalName());
    }
}