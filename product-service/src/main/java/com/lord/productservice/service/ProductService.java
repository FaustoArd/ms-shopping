package com.lord.productservice.service;

import java.util.List;

import com.lord.productservice.dto.ProductDto;
import com.lord.productservice.model.Product;

public interface ProductService {
	
	public Product findByCategoryId(Long categoryId);
	
	public ProductDto findById(Long productId);
	
	public ProductDto save(ProductDto productDto, Long categoryId);
	
	public List<ProductDto> findAll();
	
	public boolean setProductAvailable(Long productId, boolean available);
	
	public boolean isAvailable(Long productId);
	
	public int findStock(Long productId);
	
	public int getProductTotalStock(Long productId);

}
