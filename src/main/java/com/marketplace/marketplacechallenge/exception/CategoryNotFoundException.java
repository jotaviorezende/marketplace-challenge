package com.marketplace.marketplacechallenge.exception;

/**
 * Exceção lançada quando a categoria não foi encontrado no banco de dados.
 */
public class CategoryNotFoundException extends RuntimeException{

	public CategoryNotFoundException(String message) {
		super(message);
	}
}
