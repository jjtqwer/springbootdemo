package com.example.springbootdemo.service;

import com.example.springbootdemo.entity.Order;
import com.example.springbootdemo.entity.OrderDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    private static final Map<Long, Order> order_map = new HashMap<Long, Order>();

    static {
        List<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
        for (Long i = 1L; i <= 10L; i++) {
            orderDetailsList.add(new OrderDetails(i, i, GoodsService.goods_map.get(i), 5));
            order_map.put(i, new Order(i, i, new Date(), new Date(), new ArrayList<OrderDetails>(orderDetailsList)));
            System.out.println(order_map);
        }
    }

    public Order findOrderById(Long id) {
        return order_map.get(id);
    }
}
