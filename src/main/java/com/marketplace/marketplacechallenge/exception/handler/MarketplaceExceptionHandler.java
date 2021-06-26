package com.marketplace.marketplacechallenge.exception.handler;

import com.google.common.collect.Lists;
import com.marketplace.marketplacechallenge.exception.CategoryNotFoundException;
import com.marketplace.marketplacechallenge.exception.ProductNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;

/**
 * Handler para captura de exceção.
 */
@RestControllerAdvice
public class MarketplaceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ CategoryNotFoundException.class, ProductNotFoundException.class })
    public ResponseEntity<Object> handleConstraintViolationException(DataIntegrityViolationException
                ex, WebRequest request) {
        ArrayList<String> errors = Lists.newArrayList(ex.getMessage());

        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
