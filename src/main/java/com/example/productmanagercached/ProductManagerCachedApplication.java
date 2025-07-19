package com.example.productmanagercached;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProductManagerCachedApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductManagerCachedApplication.class, args);
    }

}
