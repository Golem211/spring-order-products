package com.example.demo.dao.impl;

import com.example.demo.dao.OrdersDAO;
import com.example.demo.model.Order;
import com.example.demo.model.OrderProduct;
import com.example.demo.model.Product;
import com.example.demo.model.dto.OrderDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAOImpl implements OrdersDAO {

    private final EntityManager entityManager;

    @Autowired
    public OrderDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Order> findAll() {
        TypedQuery<Order> customerQuery = entityManager.createQuery("from Orders", Order.class);
        return customerQuery.getResultList();
    }

    @Override
    public Order findById(int id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    public Order findByOrderNo(int orderNo) {
        TypedQuery<Order> customerQuery = entityManager.createQuery("from Order O " +
                "inner join fetch O.orderProductSet op " +
                "inner join fetch op.product" +
                " where O.orderNumber = : orderNumber", Order.class);
        customerQuery.setParameter("orderNumber", orderNo);
        return customerQuery.getSingleResult();
    }

    @Override
    public Order createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderNumber(orderDTO.getOrderNumber());

        return entityManager.merge(order);
    }

    @Override
    public void deleteById(int id) {
        Order order = entityManager.find(Order.class, id);
        entityManager.remove(order);
    }

    @Override
    public OrderProduct createOrderProduct(Product product,Order order, int noOfProducts) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(product);
        orderProduct.setOrder(order);
        orderProduct.setNumberOfProducts(noOfProducts);
        entityManager.persist(orderProduct);
        return orderProduct;
    }
}
