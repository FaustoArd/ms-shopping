package com.lord.productservice.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.lord.productservice.dto.ProductDto;
import com.lord.productservice.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	private static final Gson gson = new Gson();
	
	@Autowired
	private final ProductService productService;
	
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/all")
	ResponseEntity<List<ProductDto>> findAll(){
		List<ProductDto> productsDto = productService.findAll();
		return ResponseEntity.ok(productsDto);
	}
	
	@GetMapping("/by_id/{productId}")
	ResponseEntity<ProductDto> findById(@PathVariable("productId")Long productId){
		ProductDto productDto = productService.findById(productId);
		return new ResponseEntity<ProductDto>(productDto,HttpStatus.OK);
	}

	@PostMapping("/")
	ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto, @RequestParam("categoryId") Long categoryId){
		ProductDto savedProductDto = productService.save(productDto,categoryId);
		return new ResponseEntity<ProductDto>(savedProductDto, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	ResponseEntity<Boolean> setProductAvailable(@PathVariable("id")Long id,@RequestParam("available") boolean available){
		boolean result = productService.setProductAvailable(id, available);
		return new ResponseEntity<Boolean>(result,HttpStatus.OK);
	}
	
	@GetMapping("/is_available")
	ResponseEntity<Boolean> isAvailable(@RequestParam("productId") Long productId){
		return new ResponseEntity<Boolean>(productService.isAvailable(productId),HttpStatus.OK);
	}
	
	

}
