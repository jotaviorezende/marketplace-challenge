package com.marketplace.marketplacechallenge.service;

import com.marketplace.marketplacechallenge.converter.ProductConverter;
import com.marketplace.marketplacechallenge.model.Category;
import com.marketplace.marketplacechallenge.model.Product;
import com.marketplace.marketplacechallenge.repository.CategoryRepository;
import com.marketplace.marketplacechallenge.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductConverter converter;

	public void createProduct(Product product) {
		Optional<Category> categoryOptinal = categoryRepository.findByName(product.getCategory().getName());
		Category category = categoryOptinal.orElseThrow(() -> {
			String message = String.format("A categoria %s não foi encontrada.", product.getCategory().getName());
			return new RuntimeException(message);
		});
		product.setCategory(categoryOptinal.get());

		productRepository.save(product);
	}

	public List<Product> getAll() {
		return productRepository.findAll();
	}

}
