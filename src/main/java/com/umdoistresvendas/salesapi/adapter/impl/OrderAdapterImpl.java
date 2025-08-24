package com.umdoistresvendas.salesapi.adapter.impl;

import com.umdoistresvendas.salesapi.adapter.OrderAdapter;
import com.umdoistresvendas.salesapi.adapter.converter.OrderMergeOrderModelConverter;
import com.umdoistresvendas.salesapi.adapter.converter.OrderModelToOrderConverter;
import com.umdoistresvendas.salesapi.adapter.converter.OrderToOrderModelConverter;
import com.umdoistresvendas.salesapi.domain.Order;
import com.umdoistresvendas.salesapi.repository.OrderRepository;
import com.umdoistresvendas.salesapi.utils.OrderLogger;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class OrderAdapterImpl implements OrderAdapter {

    private final OrderRepository orderRepository;
    private final OrderToOrderModelConverter orderToOrderModelConverter;
    private final OrderModelToOrderConverter orderModelToOrderConverter;
    private final OrderMergeOrderModelConverter orderMergeOrderModelConverter;

    public Order create(final Order order) {
        log.info("[SAVE ORDER]");
        OrderLogger.logOrder(order);

        final var model = orderToOrderModelConverter.convert(order);
        final var orderSaved = orderRepository.save(model);
        final var orderDomain = orderModelToOrderConverter.convert(orderSaved);

        log.info("[SAVED ORDER]");
        OrderLogger.logOrder(orderDomain);

        return orderDomain;
    }

    @Override
    public Order updateOrCreate(final Order order) {
        return Optional.ofNullable(order.getId())
                .flatMap(aLong -> orderRepository.findById(order.getId()))
                .filter(Objects::nonNull)
                .map(orderModel -> orderMergeOrderModelConverter.convert(orderModel, order))
                .map(orderRepository::save)
                .map(orderModelToOrderConverter::convert)
                .orElseGet(() -> create(order));
    }

    @Override
    public Boolean getHash(final String hash) {
        return orderRepository.existsByHash(hash);
    }

    @Override
    public Order getOrder(final Long orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber)
                .map(orderModelToOrderConverter::convert)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public Page<Order> getAll(final int page, final int size) {
        return orderRepository.findAll(PageRequest.of(page, size))
                .map(orderModelToOrderConverter::convert);
    }

    @Override
    public void delete(final Order order) {
        orderRepository.deleteById(order.getId());
    }
}
