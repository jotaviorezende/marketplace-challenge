package com.marketplace.marketplacechallenge.exception;

import org.springframework.dao.DataIntegrityViolationException;

/**
 * Exceção lançada quando a categoria não foi encontrado no banco de dados.
 */
public class CategoryNotFoundException extends DataIntegrityViolationException {

	public CategoryNotFoundException(String message) {
		super(message);
	}
}
