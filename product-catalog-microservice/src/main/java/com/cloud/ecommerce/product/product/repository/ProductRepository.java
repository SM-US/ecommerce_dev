package com.cloud.ecommerce.product.product.repository;

import com.cloud.ecommerce.product.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    Product findByName(String name);
    List<Product> findByPriceLessThan(float price);
    List<Product> findByPriceGreaterThan(float price);

}
