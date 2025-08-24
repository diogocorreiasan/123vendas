package com.umdoistresvendas.salesapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private Long id;
    private String productExternalId;
    private String productExternalName;
    @With
    private Status status;
    private Integer amount;
    private BigDecimal price;
    private BigDecimal discounts;
    private BigDecimal totalValue;
    private BigDecimal totalPriceWithDiscount;
}
