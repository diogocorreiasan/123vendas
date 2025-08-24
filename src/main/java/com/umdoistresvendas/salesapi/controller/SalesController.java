package com.umdoistresvendas.salesapi.controller;

import com.umdoistresvendas.salesapi.controller.converter.OrderCancelItemRequestToOrderConverter;
import com.umdoistresvendas.salesapi.controller.converter.OrderCreateRequestToOrderConverter;
import com.umdoistresvendas.salesapi.controller.converter.OrderUpdateRequestToOrderConverter;
import com.umdoistresvendas.salesapi.controller.converter.OrderToOrderResponseConverter;
import com.umdoistresvendas.salesapi.controller.request.OrderCancelItemRequest;
import com.umdoistresvendas.salesapi.controller.request.OrderCreateRequest;
import com.umdoistresvendas.salesapi.controller.request.OrderUpdateRequest;
import com.umdoistresvendas.salesapi.controller.response.OrderResponse;
import com.umdoistresvendas.salesapi.usecase.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.umdoistresvendas.salesapi.utils.OrderLogger.logOrder;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/v1/api/sales")
public class SalesController {

    private final ItemCancelUseCase itemCancelUseCase;
    private final OrderCancelUseCase orderCancelUseCase;
    private final OrderCreateUseCase orderCreateUseCase;
    private final OrderUpdateUseCase orderUpdateUseCase;
    private final OrderDeleteUseCase orderDeleteUseCase;
    private final OrderSearchByIdUseCase orderSearchByIdUseCase;
    private final OderSearchAllUseCase oderSearchAllUseCase;
    private final OrderUpdateRequestToOrderConverter orderUpdateRequestToOrderConverter;
    private final OrderCreateRequestToOrderConverter orderCreateRequestToOrderConverter;
    private final OrderCancelItemRequestToOrderConverter orderCancelItemRequestToOrderConverter;
    private final OrderToOrderResponseConverter orderToOrderResponseConverter;

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody @Valid OrderCreateRequest request)
    {
        log.info("[CREATE-ORDER][REQUEST]");
        final var order = orderCreateRequestToOrderConverter.convert(request);
        logOrder(order);
        final var response = orderCreateUseCase.execute(order);
        log.info("[CREATE-ORDER][RESPONSE]");
        logOrder(order);
        final var orderResponse = orderToOrderResponseConverter.converter(response);
        return ResponseEntity.ok(orderResponse);
    }

    @PutMapping
    public ResponseEntity<OrderResponse> update(@RequestBody @Valid OrderUpdateRequest request)
    {
        log.info("[UPDATE-ORDER][REQUEST]");

        final var order = orderUpdateRequestToOrderConverter.convert(request);
        logOrder(order);

        final var response = orderUpdateUseCase.execute(order);

        log.info("[UPDATE-ORDER][RESPONSE]");
        logOrder(order);

        final var orderResponse = orderToOrderResponseConverter.converter(response);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/{orderNumber}")
    public ResponseEntity<OrderResponse> search(@PathVariable @Valid Long orderNumber)
    {
        log.info("[SEARCH-ORDER][REQUEST]");
        final var order = orderSearchByIdUseCase.execute(orderNumber);
        logOrder(order);
        final var orderResponse = orderToOrderResponseConverter.converter(order);
        log.info("[SEARCH-ORDER][RESPONSE]");
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping
    public ResponseEntity<Page<OrderResponse>> searchAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size)
    {
        final var orders = oderSearchAllUseCase.execute(page, size)
                .map(orderToOrderResponseConverter::converter);
        return ResponseEntity.ok(orders);
    }

    @DeleteMapping("/{orderNumber}/cancel")
    public void cancelOrder(@PathVariable @Valid Long orderNumber)
    {
        log.info("[CANCEL-ORDER][REQUEST]");
        orderCancelUseCase.execute(orderNumber);
    }

    @PostMapping("/cancel-item")
    public void cancelItem(@RequestBody @Valid OrderCancelItemRequest request)
    {
        log.info("[CANCEL-ITEM][REQUEST]");
        final var order = orderCancelItemRequestToOrderConverter.convert(request);
        itemCancelUseCase.execute(order);
    }

    @DeleteMapping("/{orderNumber}")
    public void delete(@PathVariable @Valid Long orderNumber)
    {
        log.info("[DELETE-ORDER][REQUEST]");
        orderDeleteUseCase.execute(orderNumber);
    }

}
