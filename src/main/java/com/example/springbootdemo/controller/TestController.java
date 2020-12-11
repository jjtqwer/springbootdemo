package com.example.springbootdemo.controller;

import com.example.springbootdemo.entity.T;
import com.example.springbootdemo.exception.BizException;
import com.example.springbootdemo.exception.CommonEnum;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class TestController {

    @PostMapping("/user")
    public boolean insert(@RequestBody T user) {
        System.out.println("开始新增...");
        //如果姓名为空就手动抛出一个自定义的异常！
        if (user.getName() == null) {
            throw new BizException(CommonEnum.INTER_SERVER_ERROR);
            //throw  new BizException("-1","用户姓名不能为空！");
        }
        return true;
    }

    @PutMapping("/user")
    public boolean update(@RequestBody T user) {
        System.out.println("开始更新...");
        //这里故意造成一个空指针的异常，并且不进行处理
        String str = null;
        str.equals("111");
        return true;
    }

    @DeleteMapping("/user")
    public boolean delete(@RequestBody T user) {
        System.out.println("开始删除...");
        //这里故意造成一个异常，并且不进行处理
        Integer.parseInt("abc123");
        return true;
    }

    @GetMapping("/user")
    public List<T> findByT() {
        System.out.println("开始查询...");
        List<T> userList = new ArrayList<>();
        T user2 = new T();
        user2.setId(1);
        user2.setName("xuwujing");
        user2.setAge(18);
        userList.add(user2);
        return userList;
    }

    List<T> list = new ArrayList<>();

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        StringBuffer sb = new StringBuffer("123,345,234,5342,123");
        String s = new String(sb);
        String[] split = s.split(",");
//        List<Integer> list = new ArrayList<>();
//
//        list.add(12);
////这里直接添加会报错
//        //list.add("a");
//        Class<? extends List> clazz = list.getClass();
//        Method add = clazz.getDeclaredMethod("add", Object.class);
////但是通过反射添加，是可以的
//        add.invoke(list, "kl");
//
//        System.out.println(list);
//        Integer[] intArray = { 1, 2, 3 };
//        String[] stringArray = { "Hello", "World" };
//        printArray( intArray  );
//        printArray( stringArray  );
    }

    public static <E> void printArray(E[] inputArray) {
        for (E element : inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }


}
