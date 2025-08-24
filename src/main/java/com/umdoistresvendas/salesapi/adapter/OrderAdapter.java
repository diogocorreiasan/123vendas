package com.umdoistresvendas.salesapi.adapter;

import com.umdoistresvendas.salesapi.domain.Order;
import org.springframework.data.domain.Page;


public interface OrderAdapter {
    Order updateOrCreate(final Order order);
    Boolean getHash(final String hash);
    Order getOrder(final Long orderNumber);
    Page<Order> getAll(final int page, final int size);
    void delete(final Order order);
}
