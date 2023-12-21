package com.lord.categoryservice.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lord.categoryservice.exception.CategoryNotFoundException;
import com.lord.categoryservice.model.Category;
import com.lord.categoryservice.repository.CategoryRepository;
import com.lord.categoryservice.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private final CategoryRepository categoryRepository;
	
	
	
	public CategoryServiceImpl(CategoryRepository categoryRepository,UUID uuid) {
		this.categoryRepository = categoryRepository;
		
	}
	
	@Override
	public Category findById(Long id) {
		return categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException("Category not found"));
	}



	@Override
	public void save(Category category) {
		categoryRepository.save(category);
		
	}

	@Override
	public List<Category> findAll() {
		return (List<Category>)categoryRepository.findAll();
	}

}
