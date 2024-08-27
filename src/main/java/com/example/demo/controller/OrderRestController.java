package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.model.dto.OrderDTO;
import com.example.demo.service.OrderService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderRestController {
    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.findAll();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Order> save(@Valid @RequestBody OrderDTO order) {

        return new ResponseEntity<>(orderService.save(order), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable int id) {
        orderService.deleteById(id);
    }

}
