package com.umdoistresvendas.salesapi.controller.response;

import com.umdoistresvendas.salesapi.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private String clientExternalId;
    private String clientExternalName;
    private Long orderNumber;
    private String branch;
    private LocalDate orderDate;
    private List<ItemResponse> items;
    private Status status;
    private BigDecimal totalValue;
}
