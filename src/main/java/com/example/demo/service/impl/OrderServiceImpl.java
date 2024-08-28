package com.example.demo.service.impl;

import com.example.demo.dao.OrdersDAO;
import com.example.demo.model.Order;
import com.example.demo.model.dto.OrderDTO;
import com.example.demo.model.dto.OrderProductDTO;
import com.example.demo.service.OrderProductService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

@Service
@EnableAsync
public class OrderServiceImpl implements OrderService {
    private final OrdersDAO orderDAO;


    private final OrderProductService orderProductService;


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
    public List<OrderProductDTO> save(OrderDTO order) {
        Order order1 = orderDAO.createOrder(order);
        AtomicReference<BigDecimal> orderValue = new AtomicReference<>(BigDecimal.ZERO);
        order.getProducts().entrySet().stream()
                .map(entry -> orderProductService.createOrderProduct(order1, entry.getKey(), entry.getValue()))
                .forEach(future -> orderValue.updateAndGet(v -> {
                    try {
                        return v.add(future.get().getProduct().getPrice()
                                .multiply(BigDecimal.valueOf(future.get().getNumberOfProducts())));
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }));
        System.out.println(orderValue.get());

        return orderDAO.findByOrderNo(order.getOrderNumber());
    }

    @Override
    public void deleteById(int id) {
        orderDAO.deleteById(id);
    }





}
