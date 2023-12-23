package com.lord.productservice.service;

import java.util.List;
import java.util.UUID;

import com.lord.productservice.dto.ProductDto;
import com.lord.productservice.model.Product;

public interface ProductService {
	
	public ProductDto findByIdCode(String productIdCode);
	
	public Product findByCategoryId(Long categoryId);
	
	public String save(ProductDto productDto, Long categoryId);
	
	public List<Product> findAll();
	
	public boolean isAvailable(String productIdCode);
	
	public int findStock(String productIdCode);

}
