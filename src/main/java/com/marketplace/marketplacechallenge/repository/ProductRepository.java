package com.marketplace.marketplacechallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.marketplace.marketplacechallenge.model.Product;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Repositório para a entidade {@link Product}.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    String MEDIA = "SELECT Sum(r.score) / Count(*)\n" +
            "FROM   product p\n" +
            "       JOIN rating r\n" +
            "         ON p.id = r.product_id\n" +
            "WHERE  p.id = :id\n" +
            "       AND r.creation_date >= :initialDate";

    @Query(value = MEDIA, nativeQuery = true)
    Optional<Float> getProductScoreRatingAverageById(@Param("id") Long id, @Param("initialDate") Date initialDAte);

    List<Product> getProductByCategoryId(Long categoryId);

}
