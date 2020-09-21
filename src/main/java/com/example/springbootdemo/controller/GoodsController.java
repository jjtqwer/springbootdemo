package com.example.springbootdemo.controller;

import com.example.springbootdemo.entity.Goods;
import com.example.springbootdemo.service.GoodsService;
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
        return goodsService.findGoodsById(id);
    }
}
