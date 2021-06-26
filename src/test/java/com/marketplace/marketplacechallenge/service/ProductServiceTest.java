package com.marketplace.marketplacechallenge.service;

import com.marketplace.marketplacechallenge.exception.CategoryNotFoundException;
import com.marketplace.marketplacechallenge.exception.ProductNotFoundException;
import com.marketplace.marketplacechallenge.model.Category;
import com.marketplace.marketplacechallenge.model.Product;
import com.marketplace.marketplacechallenge.repository.CategoryRepository;
import com.marketplace.marketplacechallenge.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Testes unitários paa {@link ProductService}.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    public static final long ID = 1l;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testCreateProduct() {
        // given
        Product product = mock(Product.class);
        Category category = mock(Category.class);

        given(category.getId()).willReturn(ID);
        given(product.getCategory()).willReturn(category);
        given(categoryRepository.findById(ID)).willReturn(Optional.of(category));

        // when
        productService.createProduct(product);

        //
        verify(productRepository).save(product);
    }

    @Test
    public void testCreateProductWhenThrowCategoryNotFoundException() {
        // given
        Product product = mock(Product.class);
        Category category = mock(Category.class);

        given(category.getId()).willReturn(ID);
        given(product.getCategory()).willReturn(category);
        given(categoryRepository.findById(ID)).willReturn(Optional.empty());

        // when
        catchException(() -> productService.createProduct(product));

        //
        assert caughtException() instanceof CategoryNotFoundException;
    }

    @Test
    public void testGelAllProducts() {
        // given
        List<Product> expectedProducts = mock(List.class);
        given(productRepository.findAll()).willReturn(expectedProducts);

        // when
        List<Product> result = productService.getAll();

        // then
        assertEquals(expectedProducts, result);
    }

    @Test
    public void testGetById() {
        // given
        Product expected = mock(Product.class);
        given(productRepository.findById(ID)).willReturn(Optional.of(expected));

        // when
        Product result = productService.getById(ID);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void testGetByIdWhenThrowProductNotFoundException() {
        // given
        given(productRepository.findById(ID)).willReturn(Optional.empty());

        // when
        catchException(() -> productService.getById(ID));

        // then
        assert caughtException() instanceof ProductNotFoundException;
    }

    @Test
    public void testUpdate() {
        // given
        Product product = mock(Product.class);
        Category category = mock(Category.class);

        given(product.getId()).willReturn(ID);
        given(category.getId()).willReturn(ID);
        given(product.getCategory()).willReturn(category);
        given(productRepository.findById(ID)).willReturn(Optional.of(product));
        given(categoryRepository.findById(ID)).willReturn(Optional.of(category));

        // when
        productService.update(product);

        // then
        verify(productRepository).save(product);
    }

    @Test
    public void testUpdateWhenThrowProductNotFoundException() {
        // given
        Product product = mock(Product.class);

        given(product.getId()).willReturn(ID);
        given(productRepository.findById(ID)).willReturn(Optional.empty());

        // when
        catchException(() -> productService.update(product));

        // then
        assert caughtException() instanceof ProductNotFoundException;
    }

    @Test
    public void testUpdateWhenCategoryNotFoundException() {
        // given
        Product product = mock(Product.class);
        Category category = mock(Category.class);

        given(product.getId()).willReturn(ID);
        given(category.getId()).willReturn(ID);
        given(product.getCategory()).willReturn(category);
        given(productRepository.findById(ID)).willReturn(Optional.of(product));
        given(categoryRepository.findById(ID)).willReturn(Optional.empty());

        // when
        catchException(() -> productService.update(product));

        // then
        assert caughtException() instanceof CategoryNotFoundException;
    }

    @Test
    public void testDelete() {
        // given
        Product product = mock(Product.class);
        given(productRepository.findById(ID)).willReturn(Optional.of(product));

        // when
        productService.delete(ID);

        // then
        verify(productRepository).deleteById(ID);
    }

    @Test
    public void testDeleteWhenThrowProductNotFoundException() {
        // given
        given(productRepository.findById(ID)).willReturn(Optional.empty());

        // when
        catchException(() -> productService.delete(ID));

        // then
        assert caughtException() instanceof ProductNotFoundException;
    }
}
