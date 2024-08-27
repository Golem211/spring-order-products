package com.example.demo.service.impl;

import com.example.demo.dao.SupplierRepository;
import com.example.demo.dao.impl.SupplierJDBCRepository;
import com.example.demo.model.Supplier;
import com.example.demo.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplierJDBCRepository supplierJDBCRepository;

    @Override
    public List<Supplier> findAll() {
        return supplierJDBCRepository.findAll();
    }

    @Override
    public Supplier findById(int id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);

        return supplier.orElse(null);
    }

    @Override
    public Supplier save(Supplier supplier) {
        supplierJDBCRepository.save(supplier);
        return supplier;
    }

    @Override
    public void deleteById(int id) {
        supplierRepository.deleteById(id);

    }

}
