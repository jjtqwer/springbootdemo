package com.example.springbootdemo.controller;

import com.example.springbootdemo.entity.Order;
import com.example.springbootdemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/{id}")
    public Order findOrderById(@PathVariable("id") Long id) {
        return orderService.findOrderById(id);
    }
}
