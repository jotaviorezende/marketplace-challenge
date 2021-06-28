package com.marketplace.marketplacechallenge.service;

import com.marketplace.marketplacechallenge.exception.CategoryNotFoundException;
import com.marketplace.marketplacechallenge.exception.ProductNotFoundException;
import com.marketplace.marketplacechallenge.model.Category;
import com.marketplace.marketplacechallenge.model.Product;
import com.marketplace.marketplacechallenge.repository.CategoryRepository;
import com.marketplace.marketplacechallenge.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service public class ProductService {

	public static final String CATEGORY_NOT_FOUND_MESSAGE = "A categoria %s não foi encontrada.";
	public static final String PRODUCT_NOT_FOUND_MESSAGE = "Produto não encontrado.";

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * Realiza a criação de um {@link Product}.
	 *
	 * @param product Produto a ser criado.
	 * @return produto criado.
	 */
	public Product createProduct(Product product) {
		Optional<Category> categoryOptinal = categoryRepository.findById(product.getCategory().getId());
		categoryOptinal.orElseThrow(() -> {
			String message = String.format(CATEGORY_NOT_FOUND_MESSAGE, product.getCategory().getName());
			return new CategoryNotFoundException(message);
		});

		return productRepository.save(product);
	}

	/**
	 * Retorna todos os {@link Product} cadastrados.
	 *
	 * @return List de {@link Product}.
	 */
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	/**
	 * Retorna os produtos ordenados por score, nome e categoria.
	 * @param paging Página contendo a quantidade e tamanho.
	 * @return resultado da busca dos produtos.
	 */
	public Page<Product> getOrderedProducts(Pageable paging) {
		return productRepository.getAllProducts(paging);
	}

	/**
	 * Busca o {@link Product} pelo id.
	 *
	 * @param id Id do produto.
	 * @return Produto encontrado.
	 */
	public Product getById(Long id) {
		Optional<Product> productOptional = productRepository.findById(id);

		Product product = productOptional.orElseThrow(() -> {
			return new ProductNotFoundException(PRODUCT_NOT_FOUND_MESSAGE);
		});

		return product;
	}

	/**
	 * Atualiza o {@link Product}.
	 *
	 * @param product Produto a ser atualizado.
	 * @return Produto atualizado.
	 */
	public Product update(Product product) {
		Optional<Product> productOptional = productRepository.findById(product.getId());

		productOptional.orElseThrow(() -> {
			return new ProductNotFoundException(PRODUCT_NOT_FOUND_MESSAGE);
		});

		return createProduct(product);
	}

	/**
	 * Realizada a remoção de um {@link Product}.
	 *
	 * @param id Id do produto a ser removido.
	 */
	public void delete(Long id) {
		Optional<Product> productOptional = productRepository.findById(id);

		productOptional.orElseThrow(() -> {
			return new ProductNotFoundException(PRODUCT_NOT_FOUND_MESSAGE);
		});
		productRepository.deleteById(id);
	}
}
