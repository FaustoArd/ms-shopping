package com.lord.productservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lord.productservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	public Optional<Product> findByCategoryId(Long  categoryId);
	
	

}
