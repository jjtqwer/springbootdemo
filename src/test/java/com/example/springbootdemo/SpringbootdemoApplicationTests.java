package com.example.springbootdemo;

import com.example.springbootdemo.service.TbUserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootdemoApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired()
    TbUserService tbUserService;

    @Test
    public void test(){
        System.out.println(tbUserService.list().size());
        System.out.println(1);
    }

}
