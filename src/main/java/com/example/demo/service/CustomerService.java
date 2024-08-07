package com.example.demo.service;

import com.example.demo.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer findById(int id);

    Customer save(Customer customer);

    void deleteById(int id);
}
