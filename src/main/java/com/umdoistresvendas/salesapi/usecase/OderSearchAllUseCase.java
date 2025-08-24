package com.umdoistresvendas.salesapi.usecase;

import com.umdoistresvendas.salesapi.domain.Order;
import org.springframework.data.domain.Page;

public interface OderSearchAllUseCase {
    Page<Order> execute(final int page, final int size);
}
