package com.marketplace.marketplacechallenge.controller;

import com.marketplace.marketplacechallenge.converter.ProductConverter;
import com.marketplace.marketplacechallenge.model.Product;
import com.marketplace.marketplacechallenge.model.dto.ProductDto;
import com.marketplace.marketplacechallenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Disponibiliza as APIs de produto para consumo.
 */
@RestController
@RequestMapping(path = "/product")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductConverter converter;

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto) {
        Product createdProduct = productService.createProduct(converter.toEntity(productDto));

        return new ResponseEntity<ProductDto>(converter.toDto(createdProduct), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") long id) {
        productService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        Product updatedProduct = productService.update(converter.toEntity(productDto));

        return new ResponseEntity<ProductDto>(converter.toDto(updatedProduct), HttpStatus.OK);
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDto>> getAll() {
        List<ProductDto> productDtos = convertToProductDto(productService.getAll());

        if (productDtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<List<ProductDto>>(productDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/ordered-products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDto>> getOrderedProducts(@RequestParam(defaultValue = "0")
                   int page, @RequestParam(defaultValue = "10") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Product> pageProducts = productService.getOrderedProducts(paging);
        List<Product> products = pageProducts.getContent();

        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<List<ProductDto>>(convertToProductDto(products), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> getById(@PathVariable("id") Long id) {
        Product product = productService.getById(id);

        return new ResponseEntity<ProductDto>(converter.toDto(product), HttpStatus.OK);
    }

    private List<ProductDto> convertToProductDto(List<Product> products) {
        return products.stream().map(converter::toDto).collect(Collectors.toList());
    }
}
