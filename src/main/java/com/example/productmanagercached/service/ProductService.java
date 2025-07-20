package com.example.productmanagercached.service;


import com.example.productmanagercached.dto.ProductDto;
import com.example.productmanagercached.exception.NotFoundException;
import com.example.productmanagercached.model.Product;
import com.example.productmanagercached.repository.ProductRepository;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    CacheManager cacheManager;
    public static final String PRODUCT_CACHE = "products";

    public ProductService(ProductRepository productRepository, CacheManager cacheManager) {
        this.productRepository = productRepository;
        this.cacheManager = cacheManager;
    }

    @CachePut(value = PRODUCT_CACHE, key = "#result.id()")
    public ProductDto createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.name());
        product.setPrice(productDto.price());

        Product savedProduct = productRepository.save(product);

        ProductDto dto = new ProductDto(savedProduct.getId(), savedProduct.getName(), savedProduct.getPrice());

//        Cache cache = cacheManager.getCache(PRODUCT_CACHE);
//        cache.put(savedProduct.getId(), dto);

        return dto;
    }

    @Cacheable(value = PRODUCT_CACHE, key = "#productId")
    public ProductDto getProduct(Long productId) {
//        Cache cache = cacheManager.getCache(PRODUCT_CACHE);
//        Cache.ValueWrapper value = cache.get(productId);
//        if (value == null) {
//            Product product = productRepository.findById(productId)
//                    .orElseThrow(() -> new NotFoundException("Cannot find product with id " + productId));
//            ProductDto dto = new ProductDto(product.getId(), product.getName(),
//                    product.getPrice());
//            cache.put(productId, dto);
//            return dto;
//        }
//        return (ProductDto) value.get();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Cannot find product with id " + productId));
        return new ProductDto(product.getId(), product.getName(), product.getPrice());
    }

    @CachePut(value = PRODUCT_CACHE, key = "#result.id()")
    public ProductDto updateProduct(ProductDto productDto) {
        Long productId = productDto.id();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Cannot find product with id " + productId));

        product.setName(productDto.name());
        product.setPrice(productDto.price());

        Product updatedProduct = productRepository.save(product);

//        Cache cache = cacheManager.getCache(PRODUCT_CACHE);
//        cache.put(updatedProduct.getId(), productDto);

        return new ProductDto(updatedProduct.getId(), updatedProduct.getName(),
                updatedProduct.getPrice());
    }

    @CacheEvict(value = PRODUCT_CACHE, key = "#productId")
    public void deleteProduct(Long productId) {
//        Cache cache = cacheManager.getCache(PRODUCT_CACHE);
//        cache.evict(productId);
        productRepository.deleteById(productId);
    }
}