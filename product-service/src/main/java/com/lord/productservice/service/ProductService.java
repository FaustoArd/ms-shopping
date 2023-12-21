package com.lord.productservice.service;

import java.util.List;
import java.util.UUID;

import com.lord.productservice.model.Product;

public interface ProductService {
	
	public Product findByIdCode(String productIdCode);
	
	public Product findByCategoryId(Long categoryId);
	
	public void save(Product product, Long categoryId);
	
	public List<Product> findAll();
	
	public boolean isAvailable(String productIdCode);
	
	

}
