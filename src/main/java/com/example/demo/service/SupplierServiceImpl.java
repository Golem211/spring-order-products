package com.example.demo.service;

import com.example.demo.dao.SupplierRepository;
import com.example.demo.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;


    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier findById(int id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);

        return supplier.orElse(null);
    }

    @Override
    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public void deleteById(int id) {
        supplierRepository.deleteById(id);

    }
}
