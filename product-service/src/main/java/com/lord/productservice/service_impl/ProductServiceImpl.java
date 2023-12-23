package com.lord.productservice.service_impl;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.lord.productservice.dto.ProductDto;
import com.lord.productservice.dto.StockDto;
import com.lord.productservice.mapper.ProductMapper;
import com.lord.productservice.model.Product;
import com.lord.productservice.repository.ProductRepository;
import com.lord.productservice.service.ProductService;

import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private final ProductRepository productRepository;
	
	private final WebClient webClient;

	public ProductServiceImpl(ProductRepository productRepository,WebClient.Builder webClientBuilder) {
		this.productRepository = productRepository;
		this.webClient = webClientBuilder.baseUrl("http://localhost:8090").build();

	}

	@Override
	public ProductDto findByIdCode(String productIdCode) {
		int quantity = findStock(productIdCode);
		Product product = productRepository.findByProductIdCode(UUID.fromString(productIdCode))
				.orElseThrow(() -> new RuntimeException("Product Not found"));
		ProductDto productDto = ProductMapper.INSTANCE.toProductDto(product);
		productDto.setQuantity(quantity);
		return productDto;
	}

	@Override
	public Product findByCategoryId(Long categoryId) {
		return productRepository.findByCategoryId(categoryId)
				.orElseThrow(() -> new RuntimeException("Product Not found"));
	}

	@Override
	public String save(ProductDto productDto, Long catedoryId) {
		Product product = new Product();
		product.setName(productDto.getName());
		product.setProductIdCode(UUID.randomUUID());
		product.setCategoryId(catedoryId);
		
		return productRepository.save(product).getProductIdCode().toString();

	}

	@Override
	public List<Product> findAll() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public boolean isAvailable(String productIdCode) {
		return productRepository.findByProductIdCode(UUID.fromString(productIdCode)).get().isAvailable();

	}

	@Override
	public int findStock(String productIdCode) {
	
		return this.webClient.get().uri("api/stock/by-product-id-code/{productIdCode}", productIdCode)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(StockDto.class).block().getQuantity();
	

	}

}
