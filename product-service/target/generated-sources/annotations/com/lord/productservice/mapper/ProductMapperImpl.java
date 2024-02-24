package com.lord.productservice.mapper;

import com.lord.productservice.dto.ProductDto;
import com.lord.productservice.dto.StockDto;
import com.lord.productservice.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-24T20:40:09-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.34.0.v20230523-1233, environment: Java 17.0.7 (Oracle Corporation)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto toProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setAvailable( product.isAvailable() );
        productDto.setId( product.getId() );
        productDto.setName( product.getName() );
        productDto.setCategoryId( product.getCategoryId() );

        return productDto;
    }

    @Override
    public Product toProduct(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productDto.getId() );
        product.setName( productDto.getName() );
        product.setCategoryId( productDto.getCategoryId() );
        product.setAvailable( productDto.isAvailable() );

        return product;
    }

    @Override
    public List<ProductDto> toProductsDto(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductDto> list = new ArrayList<ProductDto>( products.size() );
        for ( Product product : products ) {
            list.add( toProductDto( product ) );
        }

        return list;
    }

    @Override
    public StockDto toStockDto(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        StockDto stockDto = new StockDto();

        stockDto.setProductId( productDto.getId() );
        stockDto.setId( productDto.getId() );
        stockDto.setQuantity( productDto.getQuantity() );

        return stockDto;
    }
}
