package com.marketplace.marketplacechallenge.converter;

import org.springframework.stereotype.Component;

import com.marketplace.marketplacechallenge.model.Category;
import com.marketplace.marketplacechallenge.model.Product;
import com.marketplace.marketplacechallenge.model.dto.CategoryDto;
import com.marketplace.marketplacechallenge.model.dto.ProductDto;

/**
 * Converter de {@link ProductDto} para {@link Product}.
 */
@Component
public class ProductConverter implements ConverterInterface<Product, ProductDto> {

    @Override
    public Product toEntity(ProductDto productDto) {
        Category category = new Category(productDto.getCategoryDto().getId(), productDto.getCategoryDto().getName());

        return new Product(productDto.getName(), productDto.getDescription(), productDto.getCreationDate(), category);
    }

    @Override
    public ProductDto toDto(Product product) {
        CategoryDto categoryDto = new CategoryDto(product.getCategory().getId(), product.getCategory().getName());

        return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getCreationDate(), categoryDto);
    }
}
