package com.umdoistresvendas.salesapi.usecase.impl;

import com.umdoistresvendas.salesapi.adapter.OrderAdapter;
import com.umdoistresvendas.salesapi.domain.Order;
import com.umdoistresvendas.salesapi.usecase.OderSearchAllUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OderSearchAllUseCaseImpl implements OderSearchAllUseCase {

    private final OrderAdapter orderAdapter;

    @Override
    public Page<Order> execute(final int page, final int size) {
        return orderAdapter.getAll(page, size);
    }
}
