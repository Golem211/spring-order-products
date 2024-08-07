package com.example.demo.controller;

import com.example.demo.model.Supplier;
import com.example.demo.service.SupplierService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierRestController {


    private final SupplierService supplierService;

    @Autowired
    public SupplierRestController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<Supplier> getCustomers() {
        return supplierService.findAll();
    }

    @GetMapping("/{id}")
    public Supplier getById(@PathVariable int id) {
        return supplierService.findById(id);
    }

    @PostMapping
    @Transactional
    public Supplier save(@RequestBody Supplier supplierToSave) {
        return supplierService.save(supplierToSave);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteById(@PathVariable int id) {
        supplierService.deleteById(id);
    }


}
