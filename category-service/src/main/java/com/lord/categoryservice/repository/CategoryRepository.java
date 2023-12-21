package com.lord.categoryservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lord.categoryservice.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	

}
