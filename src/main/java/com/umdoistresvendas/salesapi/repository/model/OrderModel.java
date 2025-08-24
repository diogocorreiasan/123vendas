package com.umdoistresvendas.salesapi.repository.model;

import com.umdoistresvendas.salesapi.domain.Item;
import com.umdoistresvendas.salesapi.domain.Status;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientExternalId;
    private String clientExternalName;
    private Long orderNumber;
    @Column(unique = true)
    private String hash;
    private String branch;
    private LocalDate orderDate;

    @With
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<ItemModel> items;

    @With
    private Status status;
    private BigDecimal totalValue;

}
