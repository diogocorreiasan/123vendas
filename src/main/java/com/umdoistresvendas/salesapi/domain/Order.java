package com.umdoistresvendas.salesapi.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private String traceId;
    private String clientExternalId;
    private String clientExternalName;
    @With
    private Long orderNumber;
    private String hash;
    private String branch;
    @With
    private LocalDate orderDate;
    @With
    private List<Item> items;
    @With
    private Status status;
    private BigDecimal totalValue;
}
