package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.model.OrderProduct;

import java.util.concurrent.Future;

public interface OrderProductService {
    Future<OrderProduct> createOrderProduct(Order order, String productName, int noOfProducts);
}
