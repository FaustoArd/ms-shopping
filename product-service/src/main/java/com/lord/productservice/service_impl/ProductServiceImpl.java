package com.lord.productservice.service_impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import com.lord.productservice.dto.ProductDto;
import com.lord.productservice.dto.StockDto;
import com.lord.productservice.mapper.ProductMapper;
import com.lord.productservice.model.Product;
import com.lord.productservice.repository.ProductRepository;
import com.lord.productservice.service.ProductService;

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
	public ProductDto findById(Long id) {
		int quantity = findStock(id);
		Product product = productRepository.findById(id)
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
	public ProductDto save(ProductDto productDto, Long categoryId) {
		Product product = ProductMapper.INSTANCE.toProduct(productDto);
		product.setCategoryId(categoryId);
		return ProductMapper.INSTANCE.toProductDto( productRepository.save(product) );

	}

	@Override
	public List<ProductDto> findAll() {
		List<Product> products = productRepository.findAll();
		return ProductMapper.INSTANCE.toProductsDto(products);
	}
	
	@Override
	public boolean setProductAvailable(Long productId, boolean available) {
		return productRepository.findById(productId).map(p -> {
			p.setAvailable(available);
			return productRepository.save(p);
		}).get().isAvailable();
	}

	@Override
	public boolean isAvailable(Long productId) {
		return productRepository.findById(productId).get().isAvailable();

	}

	@Override
	public int findStock(Long productId) {
		try {
		return this.webClient.get().uri("api/stock/by-product-id-code/{productIdCode}", productId)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(StockDto.class).block().getQuantity();
		}catch(WebClientException ex) {
			throw new RuntimeException(ex.getMessage());
		}

	}

	@Override
	public int getProductTotalStock(Long productId) {
		try {
			List<String> itemsId = webClient.get().uri("/api/item/{productId}", productId).accept(MediaType.APPLICATION_JSON)
					.retrieve().bodyToMono(new ParameterizedTypeReference<List<String>>() {
					}).block().stream().map(id -> id).collect(Collectors.toList());
			
			return  webClient.get().uri("/api/item/total-quantity",uriBuilder -> uriBuilder.queryParam("itemsId", itemsId).build())
					.retrieve().bodyToMono(Integer.class).block();
		}catch(WebClientException ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	

}
