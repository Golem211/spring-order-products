package com.example.demo.dao;

import com.example.demo.model.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> findAll();

    Product findById(int id);

    Product save(Product product);

    Product findByName(String productName);

    void deleteById(int id);
}
