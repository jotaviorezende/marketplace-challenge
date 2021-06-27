package com.marketplace.marketplacechallenge.repository;

import com.marketplace.marketplacechallenge.model.Product;
import com.marketplace.marketplacechallenge.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositório para {}
 */
@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    String COUNT_SALE_BY_PRODUCT = "SELECT Count(*)\n" +
            "FROM   sale\n" +
            "WHERE  product_id = :productId";

    @Query(value = COUNT_SALE_BY_PRODUCT, nativeQuery = true)
    Integer countSaleByProduct(@Param("productId") Long productId);

}
