package com.example.demo.service.impl;

import com.example.demo.dao.OrdersDAO;
import com.example.demo.model.Order;
import com.example.demo.model.dto.OrderDTO;
import com.example.demo.service.OrderProductService;
import com.example.demo.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableAsync
public class OrderServiceImpl implements OrderService {
    private OrdersDAO orderDAO;


    private OrderProductService orderProductService;


    @Autowired
    public OrderServiceImpl(OrdersDAO orderDAO, OrderProductService orderProductService) {
        this.orderDAO = orderDAO;
        this.orderProductService = orderProductService;
    }

    @Override
    public List<Order> findAll() {
        return orderDAO.findAll();
    }

    @Override
    public Order findById(int id) {
        return orderDAO.findById(id);
    }

    @Override
    @Transactional
    public Order save(OrderDTO order) {
        Order order1 = orderDAO.createOrder(order);
        order.getProducts().forEach((key, value) -> orderProductService.createOrderProduct(order1, key, value));
        Order order2 = orderDAO.findByOrderNo(order.getOrderNumber());
        System.out.println(order2.getOrderProductSet());
        return order2;
    }

    @Override
    public void deleteById(int id) {
        orderDAO.deleteById(id);
    }





}
