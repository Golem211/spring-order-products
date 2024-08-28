package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.model.dto.OrderDTO;
import com.example.demo.model.dto.OrderProductDTO;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order findById(int id);
    List<OrderProductDTO> save(OrderDTO order);
    void deleteById(int id);
   // void createOrderProduct(Order order, String productName, int noOfProducts);

}
