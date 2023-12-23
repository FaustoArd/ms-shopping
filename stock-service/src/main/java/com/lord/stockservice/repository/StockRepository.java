package com.lord.stockservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lord.stockservice.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {

	public Optional<Stock> findByProductId(Long productId);
}
