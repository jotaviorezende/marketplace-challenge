package com.marketplace.marketplacechallenge.repository;

import com.marketplace.marketplacechallenge.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para a entidade {@link Product}.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
