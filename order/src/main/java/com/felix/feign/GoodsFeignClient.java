package com.felix.feign;

import com.felix.entity.Goods;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "goods-service")
public interface GoodsFeignClient {

    @RequestMapping(value = "goods/{id}")
    public Goods findGoodsById(@PathVariable("id") Long id);
}
