package com.lord.categoryservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.lord.categoryservice.dto.CategoryDto;
import com.lord.categoryservice.model.Category;

@Mapper
public interface CategoryMapper {

	public static CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
	
	//@Mapping(expression ="java(String.valueOf(category.getCategoryIdCode())",target = "categoryIdCode")
	public CategoryDto toCategoryDto(Category category);
	
	
	public Category toCategory(CategoryDto categoryDto);
	
	public List<CategoryDto> toCategoriesDto(List<Category> categories);
}
