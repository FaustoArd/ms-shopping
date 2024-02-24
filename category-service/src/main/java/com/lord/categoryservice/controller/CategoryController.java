package com.lord.categoryservice.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.lord.categoryservice.dto.CategoryDto;
import com.lord.categoryservice.mapper.CategoryMapper;
import com.lord.categoryservice.model.Category;
import com.lord.categoryservice.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	private static final Gson gson = new Gson();
	
	@Autowired
	private final CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@GetMapping("/all")
	ResponseEntity<List<CategoryDto>> findAll(){
		List<Category> categories = categoryService.findAll();
		List<CategoryDto> categoriesDto = CategoryMapper.INSTANCE.toCategoriesDto(categories);
		return new ResponseEntity<List<CategoryDto>>(categoriesDto,HttpStatus.OK);
	}
	
	@GetMapping("/")
	ResponseEntity<CategoryDto> findById(@RequestParam("categoryId")Long categoryId){
		Category category = categoryService.findById(categoryId);
		CategoryDto categoryDto = CategoryMapper.INSTANCE.toCategoryDto(category);
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	
	@PostMapping("/")
	ResponseEntity<String> save(@RequestBody CategoryDto categoryDto){
		Category category = CategoryMapper.INSTANCE.toCategory(categoryDto);
		categoryService.save(category);
		return new ResponseEntity<String>(gson.toJson("Category saved!"), HttpStatus.CREATED);
	}

}
