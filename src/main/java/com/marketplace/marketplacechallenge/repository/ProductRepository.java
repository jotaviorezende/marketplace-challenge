package com.marketplace.marketplacechallenge.repository;

import com.marketplace.marketplacechallenge.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Repositório para a entidade {@link Product}.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    String AVERAGE = "SELECT Sum(r.score) / Count(*)\n" +
            "FROM   product p\n" +
            "       JOIN rating r\n" +
            "         ON p.id = r.product_id\n" +
            "WHERE  p.id = :id\n" +
            "       AND r.creation_date >= :initialDate";

    String GET_ALL = "SELECT *\n" +
            "FROM   product p\n" +
            "       INNER JOIN category c\n" +
            "               ON p.category_id = c.id\n" +
            "ORDER  BY p.score,\n" +
            "          p.NAME,\n" +
            "          c.NAME ";

    String COUNT = "SELECT Count(*)\n" +
            "FROM   product p\n" +
            "       INNER JOIN category c\n" +
            "               ON p.category_id = c.id ";

    @Query(value = AVERAGE, nativeQuery = true)
    Optional<Float> getProductScoreRatingAverageById(@Param("id") Long id, @Param("initialDate") Date initialDAte);

    List<Product> getProductByCategoryId(Long categoryId);

    @Query(value = GET_ALL, countQuery = COUNT, nativeQuery = true)
    Page<Product> getAllProducts(Pageable pageable);

}
