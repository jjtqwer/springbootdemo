package com.example.springbootdemo.controller;

import com.example.springbootdemo.entity.Order;
import com.example.springbootdemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/{id}")
    public Order findOrderById(@PathVariable("id") Long id) {
        return orderService.findOrderById(id);
    }

    public static void main(String[] args) {
//        int age=18;
//        modify(age);
//        System.out.println(age);
        //System.out.println((int)(Math.random()*11));
        int a=2;
        int b=11;
//        for (int i = 0; i <10 ; i++) {
//            System.out.println(a+(int) (Math.random() * 10));
//        }
//        String str="Result";
//        String str2="isResult";
//        System.out.println(str2.equals("is"+str));
        String[] strings={"a","b","c","d"};
        List list=Arrays.asList(strings);
        System.out.println(list);
        String[] str=new String[1];
        System.out.println(Arrays.toString(list.toArray(str)));
    }

    private static void modify(int age1) {
        age1 = 30;
    }
}
