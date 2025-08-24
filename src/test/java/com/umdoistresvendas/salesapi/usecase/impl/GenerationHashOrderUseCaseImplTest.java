package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.helper.HelperTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class GenerationHashOrderUseCaseImplTest {

    @InjectMocks
    private GenerationHashOrderUseCaseImpl generationHashOrderUseCase;

    @Test
    void testToGetCode(){
        final var order = HelperTest.getOrder();
        final var response = generationHashOrderUseCase.execute(order);
        Assertions.assertNotNull(response);
        Assertions.assertEquals("5OwP/UBgbsW+Chn/5BR76mytHUapkoA9KlisgAZexEw=", response.getHash());
    }
}