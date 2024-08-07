package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.model.Supplier;

import java.util.List;

public interface SupplierService {
    List<Supplier> findAll();

    Supplier findById(int id);

    Supplier save(Supplier supplier);

    void deleteById(int id);

}
