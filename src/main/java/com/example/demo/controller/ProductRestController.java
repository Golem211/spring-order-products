package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductRestController {
    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable int id) {
        return productService.findById(id);
    }

    @PostMapping
    @Transactional
    public Product save(@Valid @RequestBody Product productToSave) {

        return productService.save(productToSave);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteById(@PathVariable int id) {
        productService.deleteById(id);
    }
}
