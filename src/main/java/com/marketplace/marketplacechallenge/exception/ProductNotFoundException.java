package com.marketplace.marketplacechallenge.exception;

import org.springframework.dao.DataIntegrityViolationException;

/**
 * Exceção lançada quando o produto não foi encontrado no banco de dados.
 */
public class ProductNotFoundException extends DataIntegrityViolationException {

	public ProductNotFoundException(String message) {
		super(message);
	}
}
