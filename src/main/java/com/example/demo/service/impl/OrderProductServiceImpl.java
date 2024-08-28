package com.example.demo.service.impl;

import com.example.demo.dao.OrdersDAO;
import com.example.demo.dao.ProductDAO;
import com.example.demo.model.Order;
import com.example.demo.model.OrderProduct;
import com.example.demo.model.Product;
import com.example.demo.service.OrderProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@EnableAsync
@Service
public class OrderProductServiceImpl implements OrderProductService {

    private final OrdersDAO orderDAO;

    private final ProductDAO productDAO;


    @Autowired
    public OrderProductServiceImpl(OrdersDAO orderDAO, ProductDAO productDAO) {
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
    }

    @Override
    @Async
    @Transactional
    public Future<OrderProduct> createOrderProduct(Order order, String productName, int noOfProducts) {

        Product product = productDAO.findByName(productName);

        OrderProduct orderProduct = orderDAO.createOrderProduct(product,order,noOfProducts);

        product.decreaseStock(noOfProducts);
        productDAO.save(product);
        return new AsyncResult<>(orderProduct);
    }


}
