package com.umdoistresvendas.salesapi.helper;

import com.umdoistresvendas.salesapi.domain.Item;
import com.umdoistresvendas.salesapi.domain.Order;
import com.umdoistresvendas.salesapi.domain.Status;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class HelperTest {

    public static Order getOrder() {

        final var item1 = Item
                .builder()
                .id(1L)
                .productExternalId("Produto-1")
                .productExternalName("Colher")
                .amount(4)
                .totalPriceWithDiscount(BigDecimal.ZERO)
                .price(new BigDecimal("10.52"))
                .totalValue(new BigDecimal("42.08"))
                .build();

        final var item2 = Item
                .builder()
                .id(12L)
                .productExternalId("Produto-2")
                .productExternalName("Faca")
                .amount(2)
                .price(new BigDecimal("11.60"))
                .totalPriceWithDiscount(BigDecimal.ZERO)
                .totalValue(new BigDecimal("23.20"))
                .build();

        final var items = List.of(item1, item2);

        return Order
                .builder()
                .id(13L)
                .totalValue(new BigDecimal("112.56"))
                .status(Status.CREATED)
                .orderNumber(1233L)
                .clientExternalName("José Da Silva")
                .items(items)
                .build();
    }

    public static Order getOrderItemDuplicate() {

        final var item1 = Item
                .builder()
                .id(1L)
                .productExternalId("Produto-1")
                .productExternalName("Colher")
                .amount(4)
                .totalPriceWithDiscount(BigDecimal.ZERO)
                .price(new BigDecimal("10.52"))
                .totalValue(new BigDecimal("42.08"))
                .build();

        final var item4 = Item
                .builder()
                .id(1L)
                .productExternalId("Produto-1")
                .productExternalName("Colher")
                .amount(4)
                .totalPriceWithDiscount(BigDecimal.ZERO)
                .price(new BigDecimal("10.52"))
                .totalValue(new BigDecimal("42.08"))
                .build();

        final var item2 = Item
                .builder()
                .id(12L)
                .productExternalId("Produto-2")
                .productExternalName("Faca")
                .amount(2)
                .price(new BigDecimal("11.60"))
                .totalPriceWithDiscount(BigDecimal.ZERO)
                .totalValue(new BigDecimal("23.20"))
                .build();

        final var items = List.of(item1, item2, item4);

        return Order
                .builder()
                .id(13L)
                .totalValue(new BigDecimal("112.56"))
                .status(Status.CREATED)
                .clientExternalName("José Da Silva")
                .items(items)
                .build();
    }

    public static Order getOrderValueNull() {

        final var item1 = Item
                .builder()
                .id(1L)
                .productExternalId("Produto-1")
                .productExternalName("Colher")
                .amount(4)
                .price(null)
                .build();

        final var item2 = Item
                .builder()
                .id(1L)
                .productExternalId("Produto-2")
                .productExternalName("Faca")
                .amount(null)
                .price(new BigDecimal("11.60"))
                .build();

        final var items = List.of(item1, item2);

        return Order
                .builder()
                .id(1L)
                .totalValue(new BigDecimal("112.56"))
                .status(Status.CREATED)
                .clientExternalName("José Da Silva")
                .items(items)
                .build();
    }

    public static Item getItem() {
        return Item
                .builder()
                .id(1L)
                .productExternalId("Produto-1")
                .productExternalName("Colher")
                .amount(4)
                .price(new BigDecimal("10.52"))
                .build();
    }

    public static Item getItemWithTenDiscount() {
        return Item
                .builder()
                .id(1L)
                .productExternalId("Produto-1")
                .productExternalName("Colher")
                .amount(9)
                .price(new BigDecimal("10.52"))
                .build();
    }

    public static Item getItemWithTwentyDiscount() {
        return Item
                .builder()
                .id(1L)
                .productExternalId("Produto-1")
                .productExternalName("Colher")
                .amount(18)
                .price(new BigDecimal("10.52"))
                .build();
    }
}