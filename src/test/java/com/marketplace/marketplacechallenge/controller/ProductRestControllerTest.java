package com.marketplace.marketplacechallenge.controller;

import com.google.common.collect.Lists;
import com.marketplace.marketplacechallenge.converter.ProductConverter;
import com.marketplace.marketplacechallenge.model.Category;
import com.marketplace.marketplacechallenge.model.Product;
import com.marketplace.marketplacechallenge.model.dto.CategoryDto;
import com.marketplace.marketplacechallenge.model.dto.ProductDto;
import com.marketplace.marketplacechallenge.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Testes unitários paa {@link ProductRestController}.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductRestControllerTest {

    public static final long ID = 1l;
    public static final String CATEGORY_NAME = "category";
    public static final String PRODUCT_NAME = "Product name";
    public static final String PRODUCT_DESCRIPTION = "Product description";
    @Mock
    private ProductService productService;

    @Mock
    private ProductConverter converter;

    @Mock
    private Date currentDate;

    @InjectMocks
    private ProductRestController productRestController;


    @Test
    public void testCreate() {
        // given
        Product product = mock(Product.class);
        ProductDto productDto = mock(ProductDto.class);
        ProductDto expected = mock(ProductDto.class);
        given(converter.toEntity(productDto)).willReturn(product);
        given(converter.toDto(product)).willReturn(expected);
        given(productService.createProduct(product)).willReturn(product);

        // when
        ResponseEntity<ProductDto> result = productRestController.create(productDto);

        // then
        verify(productService).createProduct(product);
        assertEquals(result.getBody(), expected);
    }

    @Test
    public void testDeleteProduct() {
        // when
        productService.delete(ID);

        // then
        verify(productService).delete(ID);
    }

    @Test
    public void testUpdateProduct() {
        // given
        Product product = mock(Product.class);
        ProductDto productDto = mock(ProductDto.class);
        ProductDto expected = mock(ProductDto.class);
        given(converter.toEntity(productDto)).willReturn(product);
        given(converter.toDto(product)).willReturn(expected);
        given(productService.update(product)).willReturn(product);

        // when
        ResponseEntity<ProductDto> result = productRestController.updateProduct(productDto);

        // then
        InOrder inOrder = Mockito.inOrder(productService, converter);
        inOrder.verify(converter).toEntity(productDto);
        inOrder.verify(productService).update(product);
        inOrder.verify(converter).toDto(product);
        assertEquals(result.getBody(), expected);
    }

    @Test
    public void testGetAll() {
        // given
        List<Product> products = buildProductList();
        List<ProductDto> productDtos = buildProductDtoList();
        given(productService.getAll()).willReturn(products);
        given(converter.toDto(products.get(0))).willReturn(productDtos.get(0));

        // when
        ResponseEntity<List<ProductDto>> result = productRestController.getAll();

        // then
        InOrder inOrder = Mockito.inOrder(productService, converter);
        inOrder.verify(productService).getAll();
        inOrder.verify(converter).toDto(products.get(0));
        assertEquals(productDtos, result.getBody());
    }

    @Test
    public void testGetAllWhenNoContentBody() {
        // given
        given(productService.getAll()).willReturn(Lists.newArrayList());

        // when
        ResponseEntity<List<ProductDto>> result = productRestController.getAll();

        // then
        verify(productService).getAll();
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    public void testGetById() {
        // given
        Product product = mock(Product.class);
        ProductDto expected = mock(ProductDto.class);
        given(productService.getById(ID)).willReturn(product);
        given(converter.toDto(product)).willReturn(expected);

        // when
        ResponseEntity<ProductDto> result = productRestController.getById(ID);

        // then
        InOrder inOrder = Mockito.inOrder(productService, converter);
        inOrder.verify(productService).getById(ID);
        inOrder.verify(converter).toDto(product);
        assertEquals(expected, result.getBody());
    }

    private List<Product> buildProductList() {
        Category category = new Category(ID, CATEGORY_NAME);
        Product product = new Product(PRODUCT_NAME, PRODUCT_DESCRIPTION, currentDate, category);
        product.setId(ID);

        return Lists.newArrayList(product);
    }

    private List<ProductDto> buildProductDtoList() {
        CategoryDto categoryDto = new CategoryDto(ID, CATEGORY_NAME);
        ProductDto productDto = new ProductDto(ID, PRODUCT_NAME, PRODUCT_DESCRIPTION, currentDate, categoryDto);

        return Lists.newArrayList(productDto);
    }
}