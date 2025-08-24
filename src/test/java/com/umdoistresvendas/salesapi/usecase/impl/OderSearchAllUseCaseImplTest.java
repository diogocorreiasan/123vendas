package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.adapter.OrderAdapter;
import com.umdoistresvendas.salesapi.helper.HelperTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class OderSearchAllUseCaseImplTest {

    @InjectMocks
    private OderSearchAllUseCaseImpl oderSearchAllUseCase;

    @Mock
    private OrderAdapter orderAdapter;

    @Test
    void testOderSearchAll() {
        final var order = HelperTest.getOrder();
        final var order2 = HelperTest.getOrder();
        final var order3 = HelperTest.getOrder();
        final var orders = List.of(order, order2, order3);

        when(orderAdapter.getAll(anyInt(), anyInt())).thenReturn(new PageImpl<>(orders));

        final var response = oderSearchAllUseCase.execute(0, 10);

        assertNotNull(response);
        Assertions.assertEquals(3L, response.getTotalElements());
    }
}