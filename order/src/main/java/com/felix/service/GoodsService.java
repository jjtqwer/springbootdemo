package com.felix.service;

import com.felix.entity.Goods;
import com.felix.feign.GoodsFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private GoodsFeignClient goodsFeignClient;



    //启用负载均衡后，有restTemplate自己去选择访问那个服务
    @HystrixCommand(fallbackMethod = "findGoodsByIdServiceOffline")
    public Goods findGoodsById(Long id) {
        //1 String url = "http://127.0.0.1:8000/goods/" + id;

//2        String service = "goods-service";
//        List<ServiceInstance> instances = discoveryClient.getInstances(service);
//        if (instances.isEmpty()){
//            return null;
//        }
//        String url = "http://" + instances.get(0).getHost()+":"+instances.get(0).getPort() + "/goods/" + id;

//3        String service = "goods-service";
//        String url = "http://" + service + "/goods/" + id;
        //1,2,3->  return restTemplate.getForObject(url, Goods.class);

        return goodsFeignClient.findGoodsById(id);
    }
    public Goods findGoodsByIdServiceOffline(Long id){
        return new Goods(id,"查询商品信息出错","","",0.0F);
    }
}
