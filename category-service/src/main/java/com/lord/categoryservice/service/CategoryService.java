package com.lord.categoryservice.service;

import java.util.List;
import java.util.UUID;

import com.lord.categoryservice.model.Category;

public interface CategoryService {
	
	public Category findById(Long id);
	
	public void save(Category category);
	
	public List<Category> findAll();

}
