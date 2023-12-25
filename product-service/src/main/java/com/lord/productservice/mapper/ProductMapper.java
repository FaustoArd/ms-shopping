package com.lord.productservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.lord.productservice.dto.ProductDto;
import com.lord.productservice.dto.StockDto;
import com.lord.productservice.model.Product;


@Mapper
public interface ProductMapper {
	
	public static ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
	
	@Mapping(target = "quantity", ignore=true)
	public ProductDto toProductDto(Product product);
	
	public Product toProduct(ProductDto productDto);
	
	public List<ProductDto> toProductsDto(List<Product> products);
	
	@Mapping(target="productId", source = "id")
	public StockDto toStockDto(ProductDto productDto);

}
