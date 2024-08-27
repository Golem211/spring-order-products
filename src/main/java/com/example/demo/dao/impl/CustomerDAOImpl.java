package com.example.demo.dao.impl;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    private final EntityManager entityManager;

    @Autowired
    public CustomerDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Customer> findAll() {

        TypedQuery<Customer> query = entityManager.createQuery("from Customer", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findById(int id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public Customer save(Customer customerToSave) {
        return entityManager.merge(customerToSave);
    }

    @Override
    public void deleteById(int id) {
        Customer customer = entityManager.find(Customer.class, id);
        entityManager.remove(customer);
    }
}
