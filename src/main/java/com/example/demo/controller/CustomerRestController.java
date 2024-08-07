package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

    private final CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable int id) {
        return customerService.findById(id);
    }

    @PostMapping
    @Transactional
    public Customer save(@RequestBody Customer customerToSave) {
        return customerService.save(customerToSave);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteById(@PathVariable int id) {
        customerService.deleteById(id);
    }


}
