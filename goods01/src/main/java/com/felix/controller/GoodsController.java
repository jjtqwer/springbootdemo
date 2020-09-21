package com.felix.controller;


import com.felix.entity.Goods;
import com.felix.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/{id}")
    public Goods findGoodsById(@PathVariable("id") Long id) {
        System.out.println("goods01收到商品信息请求");
        return goodsService.findGoodsById(id);
    }
}
