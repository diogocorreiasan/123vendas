package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.helper.HelperTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class JoinItemsUseCaseImplTest {

    @InjectMocks
    private JoinItemsUseCaseImpl joinItemsUseCase;

    @Test
    void testJoinItems() {
        final var order = HelperTest.getOrderItemDuplicate();

        final var response = joinItemsUseCase.execute(order);

        Assertions.assertEquals(2, response.getItems().size());
    }
}