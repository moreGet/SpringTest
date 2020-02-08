package com.ensat.services;

import java.util.Optional;

import com.ensat.entities.Product;


public interface ProductService {

    Iterable<Product> listAllProducts();

    Optional<Product> getProductById(Integer id);
    

    Product saveProduct(Product product);

    void deleteProduct(Integer id);

}
