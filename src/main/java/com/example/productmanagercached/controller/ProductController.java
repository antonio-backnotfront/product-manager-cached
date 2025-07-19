package com.example.productmanagercached.controller;

import com.example.productmanagercached.dto.ProductDto;
import com.example.productmanagercached.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductDto productDto) {
        ProductDto createdProduct = productService.createProduct(productDto);
        return ResponseEntity.created(URI.create("/api/product/"+createdProduct.id())).body(createdProduct);
    }

    @GetMapping("{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@RequestBody @Valid ProductDto productDto) {
        return ResponseEntity.ok(productService.updateProduct(productDto));
    }

    @DeleteMapping("{productId}")
    public ResponseEntity deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
