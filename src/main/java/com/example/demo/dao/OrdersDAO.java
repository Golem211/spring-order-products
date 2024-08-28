package com.example.demo.dao;

import com.example.demo.model.Order;
import com.example.demo.model.OrderProduct;
import com.example.demo.model.Product;
import com.example.demo.model.dto.OrderDTO;
import com.example.demo.model.dto.OrderProductDTO;

import java.util.List;

public interface OrdersDAO {
    List<Order> findAll();
    Order findById(int id);
    List<OrderProductDTO> findByOrderNo(int orderNo);
    Order createOrder(OrderDTO order);
    void deleteById(int id);
    OrderProduct createOrderProduct(Product product, Order order, int noOfProducts);
}
