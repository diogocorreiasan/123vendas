package com.umdoistresvendas.salesapi.repository.model;

import com.umdoistresvendas.salesapi.domain.Status;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productExternalId;
    private String productExternalName;
    private Status status;
    private Integer amount;
    private BigDecimal price;
    private BigDecimal discounts;
    private BigDecimal totalValue;
    private BigDecimal totalPriceWithDiscount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderModel order;
}
