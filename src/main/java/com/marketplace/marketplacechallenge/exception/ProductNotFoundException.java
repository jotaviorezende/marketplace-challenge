package com.marketplace.marketplacechallenge.exception;

/**
 * Exceção lançada quando o produto não foi encontrado no banco de dados.
 */
public class ProductNotFoundException extends RuntimeException {

	public ProductNotFoundException(String message) {
		super(message);
	}
}
