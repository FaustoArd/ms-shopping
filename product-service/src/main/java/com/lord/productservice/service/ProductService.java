package com.lord.productservice.service;

import java.util.List;
import java.util.UUID;

import com.lord.productservice.dto.ProductDto;
import com.lord.productservice.model.Product;

public interface ProductService {
	
	public Product findByCategoryId(Long categoryId);
	
	public ProductDto findById(Long productId);
	
	public Long save(ProductDto productDto, Long categoryId);
	
	public List<Product> findAll();
	
	public boolean isAvailable(Long productId);
	
	public int findStock(Long productId);

}
