package com.felix.controller;

import com.felix.entity.Order;
import com.felix.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
