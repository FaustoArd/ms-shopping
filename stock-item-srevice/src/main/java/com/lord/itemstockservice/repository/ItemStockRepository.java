package com.lord.itemstockservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.lord.itemstockservice.model.ItemStock;

public interface ItemStockRepository extends JpaRepository<ItemStock, Long> {

	public Optional<ItemStock> findByItemId(String itemId);
}


