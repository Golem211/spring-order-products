package com.example.demo.service;

import com.example.demo.model.Order;

public interface OrderProductService {
    void createOrderProduct(Order order, String productName, int noOfProducts);
}
