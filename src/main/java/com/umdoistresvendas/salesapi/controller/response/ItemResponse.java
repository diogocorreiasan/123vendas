package com.umdoistresvendas.salesapi.controller.response;

import com.umdoistresvendas.salesapi.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {
    private Long id;
    private String productExternalId;
    private String productExternalName;
    private Status status;
    private Integer amount;
    private BigDecimal price;
    private BigDecimal discounts;
    private BigDecimal totalValue;
    private BigDecimal totalPriceWithDiscount;
}
