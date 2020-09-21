package com.felix.service;

import com.felix.entity.Goods;
import com.felix.entity.Order;
import com.felix.entity.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    @Autowired
    private GoodsService goodsService;
    private static final Map<Long, Order> order_map = new HashMap<Long, Order>();

    static {
        List<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
        for (Long i = 1L; i <= 10L; i++) {
            Goods goods = new Goods();
            goods.setGoodsid(i);
            orderDetailsList.add(new OrderDetails(i, i, goods, 5));
            order_map.put(i, new Order(i, i, new Date(), new Date(), new ArrayList<OrderDetails>(orderDetailsList)));
            System.out.println(order_map);
        }
    }

    public Order findOrderById(Long id) {
        Order order = order_map.get(id);
        for (OrderDetails orderDetails : order.getOrderDetailsList()) {
            try {
                Goods goods = goodsService.findGoodsById(orderDetails.getGoods().getGoodsid());
                orderDetails.setGoods(goods);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return order;
    }
}
