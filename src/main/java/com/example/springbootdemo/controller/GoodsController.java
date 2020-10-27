package com.example.springbootdemo.controller;

import com.example.springbootdemo.entity.Goods;
import com.example.springbootdemo.service.GoodsService;
import org.apache.commons.collections4.map.LRUMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;


    @RequestMapping(value = "/{id}")
    public Goods findGoodsById(@PathVariable("id") Long id) {
        return goodsService.findGoodsById(id);
    }

    /**
     * 防止重复提交
     * 基础版：HashMap
     * 因为HashMap是无限增长的，因此它会占用越来越多的内存，并且随着HashMap数量的增加，查找的速度也会降低，
     * 所以我们需要实现一个可以自动清除过期数据的实现方案
     * @param id
     * @return
     */
    /**
     * 缓存ID集合
     */
    private Map<String,Integer> reqCache=new HashMap<>();
    @RequestMapping("add")
    public String addUser(String id){
        synchronized (this.getClass()){
            if (reqCache.containsKey(id)){
                System.out.println("请勿重复提交:"+id);
                return "执行失败";
            }
            reqCache.put(id,1);
        }
        System.out.println("添加用户:"+id);
        return "执行成功";
    }

    /**
     *防止重复提交
     * 优化版-固定大小的数组
     * 解决了HashMap无限增长的问题，它使用数组加下标计数器的方式，实现了固定数组的循环存储
     * 当数组存储到最后一位时，将数组的存储下表设置0，再从头开始存储数据
     * @param id
     * @return
     */
    /**
     * 请求ID存储集合
     */
    private static String[] reqCache2=new String[100];
    private static Integer reqCacheCounter=0;
    @RequestMapping("add2")
    public String addUser2(String id){
        synchronized (getClass()){
            //重复请求判断
            if (Arrays.asList(reqCache2).contains(id)){
                System.out.println("请勿重复提交:"+id);
                return "执行失败";
            }
            if (reqCacheCounter >= reqCache2.length) {
                reqCacheCounter=0;
            }
            reqCache2[reqCacheCounter]=id;
            reqCacheCounter++;
        }
        System.out.println("添加用户ID:"+id);
        return "执行成功";
    }

    /**
     * 防止重复提交
     * 扩展版-双重检测锁(DCL)
     * 上一种实现方法将判断和添加业务synchronizid种进行加锁操作，性能不是很高，使用DCL来优化代码的执行效率
     * DCL适用于重复提交频繁比较高的业务场景，对于相反的业务场景下DCL并不使用
     * @param id
     * @return
     */
    @RequestMapping("add3")
    public String addUser3(String id){
        //重复请求判断
        if (Arrays.asList(reqCache2).contains(id)){
            System.out.println("请勿重复提交:"+id);
            return "执行失败";
        }
        synchronized (getClass()){
            //重复请求判断
            if (Arrays.asList(reqCache2).contains(id)){
                System.out.println("请勿重复提交:"+id);
                return "执行失败";
            }
            if (reqCacheCounter >= reqCache2.length) {
                reqCacheCounter=0;
            }
            reqCache2[reqCacheCounter]=id;
            reqCacheCounter++;
        }
        System.out.println("添加用户ID:"+id);
        return "执行成功";
    }

    /**
     * 防止重复提交
     * 完善版-LRUMap
     * LRU:Least Recently Used 最近最少使用  选择最近最久未使用的数据予以淘汰
     * LRUMap:可以保存指定数量的固定的数据，并且它会按照LRU算法，帮你清除最不常用的数据
     * @param id
     * @return
     */
    /**
     * 最大容量100个，根据LRU算法淘汰数据的Map集合
     */
    private LRUMap<String,Integer> lruMap=new LRUMap<>(100);
    @RequestMapping("add4")
    public String addUser4(String id){
        synchronized (getClass()){
            if (lruMap.containsKey(id)){
                System.out.println("请勿重复提交："+id);
                return "执行失败";
            }
            lruMap.put(id,1);
        }
        System.out.println("添加用户ID："+id);
        return "执行成功";
    }
}
