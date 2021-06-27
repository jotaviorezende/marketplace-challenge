package com.marketplace.marketplacechallenge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.marketplace.marketplacechallenge.model.Category;
import com.marketplace.marketplacechallenge.model.Product;
import com.marketplace.marketplacechallenge.repository.CategoryRepository;
import com.marketplace.marketplacechallenge.repository.ProductRepository;
import com.marketplace.marketplacechallenge.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Classe que realiza o ranquiamento de produtos.
 */
@Service
public class RankProductService {

    private static final String NEWS_URL
            = "https://newsapi.org/v2/top-headlines?country=br&category=%s&apiKey=b67d5f6dfe6142ffb9f4bed99ec5a860";
    public static final String TOTAL_RESULTS_FIELD = "totalResults";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Calcula score para cada produto pelas seguintes informações abaixo:
     *
     * <ul>
     *     <li>X = Média de avaliação do produto nos últimos 12 meses;</li>
     *     <li>Y = Quantidade de vendas/dias que o produto existe;</li>
     *     <li>Z = Quantidade de notícias da categoria do produto no dia corrente.</li>
     * </ul>
     *
     */
    @GetMapping(path = "/")
    public void rankProductsByCategory() {
        List<Category> categoryList = categoryRepository.findAll();

        categoryList.forEach(category -> {
            Integer totalNewsByCategoryName = totalNewsByCategoryName(category.getName());
            calculateAndUpdateScoreProduct(category.getId(), totalNewsByCategoryName);
        });
    }

    private void calculateAndUpdateScoreProduct(Long categoryId, Integer totalNewsByCategoryName) {
        List<Product> productListByCategoryId = productRepository.getProductByCategoryId(categoryId);

        productListByCategoryId.forEach(product -> {
            float productRatingAverage
                    = productRepository.getProductScoreRatingAverageById(product.getId(), getInitialDate()).orElse(0f);
            float averageProductSaleByDay = calculateAverageProductSaleByDay(product);
            float productScore = (productRatingAverage + averageProductSaleByDay + totalNewsByCategoryName);
            product.setScore(productScore);
            productRepository.save(product);
        });
    }

    private float calculateAverageProductSaleByDay(Product product) {
        Integer productSaleCount = saleRepository.countSaleByProduct(product.getId());
        LocalDateTime productLocalDateTime
                = product.getCreationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        float countDayscreation = ChronoUnit.DAYS.between(productLocalDateTime, LocalDateTime.now());

        return (productSaleCount / countDayscreation);
    }

    private Date getInitialDate() {
        Calendar initialDate = Calendar.getInstance();
        initialDate.add(Calendar.MONTH, -12);

        return initialDate.getTime();
    }

    private Integer totalNewsByCategoryName(String categoryName) {
        String url = String.format(NEWS_URL, categoryName);
        String json = restTemplate.getForObject(url, String.class);

        try {
            ObjectNode jsonNodes = new ObjectMapper().readValue(json, ObjectNode.class);
            boolean hasTotalResults = jsonNodes.has(TOTAL_RESULTS_FIELD);

            return hasTotalResults ? jsonNodes.get(TOTAL_RESULTS_FIELD).asInt() : 0;
        } catch (JsonProcessingException e) {
            return 0;
        }
    }
}
