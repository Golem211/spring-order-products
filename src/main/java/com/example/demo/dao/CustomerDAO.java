package com.example.demo.dao;

import com.example.demo.model.Customer;

import java.util.List;

public interface CustomerDAO {
    List<Customer> findAll();

    Customer findById(int id);

    Customer save(Customer customer);

    void deleteById(int id);
}
