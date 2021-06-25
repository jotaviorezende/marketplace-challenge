package com.marketplace.marketplacechallenge.repository;

import java.util.Optional;

import com.marketplace.marketplacechallenge.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório para a entidade {@link Category}.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

}
