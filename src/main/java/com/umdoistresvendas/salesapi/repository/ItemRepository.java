package com.umdoistresvendas.salesapi.repository;

import com.umdoistresvendas.salesapi.repository.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemModel, Long> {
}
