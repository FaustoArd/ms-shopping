package com.lord.productservice.service_impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
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
	public Long save(ProductDto productDto, Long catedoryId) {
		Product product = new Product();
		product.setName(productDto.getName());
		product.setCategoryId(catedoryId);
		
		return productRepository.save(product).getId();

	}

	@Override
	public List<Product> findAll() {
		return (List<Product>) productRepository.findAll();
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
	
		return this.webClient.get().uri("api/stock/by-product-id-code/{productIdCode}", productId)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(StockDto.class).block().getQuantity();
	

	}

	

}
