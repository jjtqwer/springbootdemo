package com.example.springbootdemo;

import com.example.springbootdemo.service.TbUserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
       // System.out.println(tbUserService.list().size());
        System.out.println(1);
    }

    @Test
    public void test2(){
        List<String> list = new ArrayList<>();
        list.add("武汉加油");
        list.add("中国加油");
        list.add("世界加油");
        list.add("世界加油");
        long count = list.stream().distinct().count();
        System.out.println(count);

        String[] arr=new String[]{"武汉加油","中国加油","世界加油"};
        Stream<String> stream = Arrays.stream(arr);
        stream=Stream.of(arr);

    }

    @Test
    public void test3(){
        List<String> list = new ArrayList<>();
        list.add("周杰伦");
        list.add("王力宏");
        list.add("陶喆");
        list.add("林俊杰");
        Stream<String> stream = list.stream().filter(e -> e.contains("杰"));
        stream.forEach(System.out::println);
    }

    @Test
    public void test4() {
        //映射:如果想通过某种操作把一个流中的元素转化成新的流中的元素，可以使用 map() 方法。
        List<String> list = new ArrayList<>();
        list.add("周杰伦");
        list.add("王力宏");
        list.add("陶喆");
        list.add("林俊杰");
        //map() 方法接收的是一个 Function（Java 8 新增的一个函数式接口，接受一个输入参数 T，返回一个结果 R）类型的参数，此时参数 为 String 类的 length 方法，
        // 也就是把 Stream<String> 的流转成一个 Stream<Integer> 的流。
        Stream<Integer> stream = list.stream().map(String::length);
        stream.forEach(System.out::println);
    }

    @Test
    public void test5() {
        //匹配
        //anyMatch()，只要有一个元素匹配传入的条件，就返回 true。
        //allMatch()，只有有一个元素不匹配传入的条件，就返回 false；如果全部匹配，则返回 true。
        //noneMatch()，只要有一个元素匹配传入的条件，就返回 false；如果全部匹配，则返回 true
        List<String> list = new ArrayList<>();
        list.add("周杰伦");
        list.add("王力宏");
        list.add("陶喆");
        list.add("林俊杰");
        boolean match = list.stream().anyMatch(e -> e.contains("王"));
        boolean match1 = list.stream().allMatch(e -> e.length() > 1);
        boolean match2 = list.stream().noneMatch(e -> e.endsWith("成"));
        System.out.println(match);
        System.out.println(match1);
        System.out.println(match2);

    }

    @Test
    public void test6() {
        //组合:
        Integer[] ints={0,1,2,3};
        List<Integer> list = Arrays.asList(ints);

        Optional<Integer> reduce = list.stream().reduce((a, b) -> a + b);
        Optional<Integer> reduce1 = list.stream().reduce(Integer::sum);
        System.out.println(reduce.orElse(0));
        System.out.println(reduce1.orElse(0));

        Integer reduce2 = list.stream().reduce(6, (a, b) -> a + b);
        System.out.println(reduce2);

        Integer reduce3 = list.stream().reduce(6, Integer::sum);
        System.out.println(reduce3);
    }

    @Test
    public void test7() {
        //转换流
        //通过 stream() 方法创建集合的流后，再通过 map(String:length) 将其映射为字符串长度的一个新流，最后通过 collect() 方法将其转换成新的集合。
        //
        //Collectors 是一个收集器的工具类，内置了一系列收集器实现，比如说 toList() 方法将元素收集到一个新的 java.util.List 中；比如说 toCollection()
        // 方法将元素收集到一个新的 java.util.ArrayList 中；比如说 joining() 方法将元素收集到一个可以用分隔符指定的字符串中。
        List<String> list = new ArrayList<>();
        list.add("周杰伦");
        list.add("王力宏");
        list.add("陶喆");
        list.add("林俊杰");

        String[] array = list.stream().toArray(String[]::new);
        System.out.println(Arrays.toString(array));

        List<Integer> collect = list.stream().map(String::length).collect(Collectors.toList());
        ArrayList<String> collect1 = list.stream().collect(Collectors.toCollection(ArrayList::new));
        System.out.println(collect);
        System.out.println(collect1);

        String collect2 = list.stream().collect(Collectors.joining(","));
        System.out.println(collect2);
    }

    @Test
    public void test8(){
        //不可变类
        Book book = new Book();
        book.setName("Web全栈开发进阶之路");
        book.setPrice(79);

        Writer writer = new Writer("tt",18, book);
        System.out.println("定价：" + writer.getBook());
        writer.getBook().setPrice(59);
        System.out.println("促销价：" + writer.getBook());
    }

    @Test
    public void test9(){
        List<Object> objects = Collections.emptyList();
        System.out.println(objects);

        int[] arr={1,2,3};
        System.out.println(arr.toString());
    }
}

final class Writer {
    private final String name;
    private final int age;
    private final Book book;

    public Writer(String name, int age,Book book) {
        this.name = name;
        this.age = age;
        this.book=book;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Book getBook(){
        //如果一个不可变类中包含了可变类的对象，那么就需要确保返回的是可变对象的副本。也就是说，Writer 类中的 getBook()
        Book clone=new Book();
        clone.setPrice(this.book.getPrice());
        clone.setName(this.book.getName());
        return clone;
    }
}

class Book {
    private String name;
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
