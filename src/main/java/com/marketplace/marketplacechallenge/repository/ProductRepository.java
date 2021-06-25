package com.marketplace.marketplacechallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marketplace.marketplacechallenge.model.Product;

/**
 * Repositório para a entidade {@link Product}.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
