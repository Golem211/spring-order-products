package com.example.demo.service.impl;

import com.example.demo.dao.ProductDAO;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }


    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Product findById(int id) {
        return productDAO.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productDAO.save(product);
    }

    @Override
    public void deleteById(int id) {
        productDAO.deleteById(id);
    }
}
