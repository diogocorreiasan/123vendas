package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.domain.Item;
import com.umdoistresvendas.salesapi.usecase.DiscountsUseCase;
import com.umdoistresvendas.salesapi.usecase.DiscountSelectorUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DiscountSelectorUseCaseImpl implements DiscountSelectorUseCase {

    private final List<DiscountsUseCase> discountsUseCases = List.of(
            new TenPercentDiscountUseCaseImpl(),
            new TwentyPercentDiscountUseCaseImpl(),
            new NoDiscountUseCaseImpl()
    );

    @Override
    public DiscountsUseCase execute(final Item item) {
        return discountsUseCases.stream()
                .filter(discountsUseCase -> discountsUseCase.accepted(item.getAmount()))
                .findFirst()
                .orElse(new NoDiscountUseCaseImpl());
    }
}
