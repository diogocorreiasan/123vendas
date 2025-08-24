package com.umdoistresvendas.salesapi.repository;


import com.umdoistresvendas.salesapi.repository.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {

    boolean existsByHash(final String hash);
    Optional<OrderModel> findByOrderNumber(Long numberOrder);
}
