package com.lord.productservice.service_impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

import com.lord.productservice.model.Product;
import com.lord.productservice.repository.ProductRepository;
import com.lord.productservice.service.ProductService;

import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private final ProductRepository productRepository;
	
	@Autowired
	private final WebClient.Builder webClientBuilder;
	
	
	
	public ProductServiceImpl(ProductRepository productRepository,WebClient.Builder webClientBuilder) {
		this.productRepository = productRepository;
		this.webClientBuilder = webClientBuilder;
		
	}

	@Override
	public Product findByIdCode(String productIdCode) {
		return productRepository.findByProductIdCode(UUID.fromString(productIdCode)).orElseThrow(()-> new RuntimeException("Product Not found"));
	}

	@Override
	public Product findByCategoryId(Long categoryId) {
		return productRepository.findByCategoryId(categoryId).orElseThrow(()-> new RuntimeException("Product Not found"));
	}

	@Override
	public void save(Product product,Long catedoryId) {
		product.setProductIdCode(UUID.randomUUID());
		product.setCategoryIdC(catedoryId);
		productRepository.save(product);
		
	}

	@Override
	public List<Product> findAll() {
		return (List<Product>)productRepository.findAll();
	}

	@LoadBalanced
	@Override
	public boolean isAvailable(String productIdCode) {
		return   webClientBuilder.build().get().uri("http://stock-service/api/stock/is_available", uriBuilder ->
		uriBuilder.queryParam("productIdCode", productIdCode).build()).retrieve().bodyToMono(boolean.class).doOnError(error -> error.getMessage()).block();
				
			
			
				
		
	}
}
