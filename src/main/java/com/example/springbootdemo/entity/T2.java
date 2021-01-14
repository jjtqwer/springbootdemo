package com.example.springbootdemo.entity;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class T2 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("peter", "anan", "mike", "xenia");
        System.out.println("list = " + list);
//        Collections.sort(list, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o2.compareTo(o1);
//            }
//        });
//        System.out.println("之前排序后 list = " + list);
        //java8排序
//        Collections.sort(list,(String a,String b) ->{
//            return b.compareTo(a);
//        });
//        System.out.println("java8排序 list = " + list);
        //对于函数体只有一行代码 可以省略大括号和return 关键字
//        Collections.sort(list,(String a,String b) -> b.compareTo(a));
//        System.out.println("java8短的写法 排序 list = " + list);

        list.sort((a,b) ->b.compareTo(a));
        System.out.println("java8更短写法 排序 list = " + list);
    }
}
@FunctionalInterface
interface Converter2<F,T>{
    T convert(F from);

    public static void main(String[] args) {
        //Converter2<String,Integer> converter=(from) ->Integer.valueOf(from);
        //静态方法引用
        Converter2<String,Integer> converter=Integer::valueOf;
        Integer convert = converter.convert("123");
        System.out.println("convert.getClass() = " + convert.getClass());
        //我们可以直接在 lambda 表达式中访问外部的局部变量：但是和匿名对象不同的是，这里的变量num可以不用声明为final，该代码同样正确：
        //不过这里的 num 必须不可被后面的代码修改
        //final int num=1;
        int num=1;
        Converter2<Integer,String> stringConverter2=(from) -> String.valueOf(from+num);
        System.out.println(stringConverter2.convert(2));
    }
}

class Lambda4{
    static int outerStaticNum;
    int outerNum;

    public void test(){
        Converter2<Integer,String> stringConverter2=(from) ->{
            outerNum=23;
            return String.valueOf(from);
        };
        stringConverter2=(from) ->{
            outerStaticNum=55;
            return String.valueOf(from);
        };
    }
    //lambda表达式不能访问默认接口方法
}

class Something2{
    String startsWith(String s){
        return String.valueOf(s.charAt(0));
    }

    public static void main(String[] args) {
        Something2 something2=new Something2();
        Converter2<String,String> converter2=something2::startsWith;
        String convert = converter2.convert("Java");
        System.out.println("convert = " + convert);
    }
}

class Person3{
    String firstName;
    String lastName;

    public Person3() {
    }

    public Person3(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

interface PersonFactory<P extends Person3>{
    P create(String firstName,String lastName);

    public static void main(String[] args) {
        PersonFactory<Person3> person3PersonFactory=Person3::new;
        Person3 person3 = person3PersonFactory.create("Peter", "Parker");
        System.out.println("person3 = " + person3);
    }
}

class TestPredicate{
    public static void main(String[] args) {
        //Predicate 接口是只有一个参数的返回布尔类型值的 断言型 接口。该接口包含多种默认方法来将 Predicate 组合成其他复杂的逻辑（比如：与，或，非）：
        Predicate<String> predicate=(s) -> s.length()>0;
        System.out.println(predicate.test("foo"));
        //negate 取反
        System.out.println(predicate.negate().test("foo"));

        Predicate<String> nonNull= Objects::nonNull;
        Predicate<String> isNull=Objects::isNull;
        String s1=null;
        System.out.println(nonNull.and(isNull).test(s1));
        Predicate<String> isEmpty=String::isEmpty;
        Predicate<String> isNotEmpty=isEmpty.negate();
        s1="";
        System.out.println(isEmpty.test(s1+"1"));
    }
}

class TestFunction{

    public static void main(String[] args) {
        // //将Function对象应用到输入的参数上，然后返回计算结果。
        Function<String,Integer> toInteger=Integer::valueOf;
        Function<String,String> backToString=toInteger.andThen(String::valueOf);

        System.out.println(backToString.apply("123").getClass());
        System.out.println(toInteger.apply("44").getClass());

        //Supplier 接口产生给定泛型类型的结果。 与 Function 接口不同，Supplier 接口不接受参数。
        Supplier<Person3> person3Supplier=Person3::new;
        System.out.println(person3Supplier.get());

        //Consumer 接口表示要对单个输入参数执行的操作。
        Consumer<Person3> greeter=(p) -> System.out.println("Hello"+p.firstName);
        greeter.accept(new Person3("Luke","Sky"));
    }
}

class TestComparator{

    public static void main(String[] args) {
        Comparator<Person3> comparator=(p1,p2)->p1.firstName.compareTo(p2.firstName);
        Person3 p1=new Person3("John","Doe");
        Person3 p2=new Person3("Alice","Wonderful");
        System.out.println(comparator.compare(p1,p2));//9
        System.out.println(comparator.reversed().compare(p1,p2));//-9
    }
}

class TestOptional{
    public static void main(String[] args) {
        //of（）：为非null的值创建一个Optional
        Optional<String> optional = Optional.of("bam");
        // isPresent（）： 如果值存在返回true，否则返回false
        System.out.println(optional.isPresent());
        //get()：如果Optional有值则将其返回，否则抛出NoSuchElementException
        System.out.println(optional.get());
        //orElse（）：如果有值则将其返回，否则返回指定的其它值
        System.out.println(optional.orElse("other"));
        //ifPresent（）：如果Optional实例有值则为其调用consumer，否则不做处理
        optional.ifPresent((s)-> System.out.println(s.charAt(0)));
    }
}

class TestStreams{

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("ddd2");
        stringList.add("aaa2");
        stringList.add("bbb1");
        stringList.add("aaa1");
        stringList.add("bbb3");
        stringList.add("ccc");
        stringList.add("bbb2");
        stringList.add("ddd1");
        //参数为Predicate需要boolean值
        //参数为Function 将参数引用到输入的参数上 然后返回结果
        //filter
        stringList.stream().filter(s -> s.startsWith("a")).forEach(System.out::println);
        //sorted 不指定使用默认排序
        stringList.stream().sorted((s1, s2) -> s2.compareTo(s1)).filter(s -> s.startsWith("b")).forEach(System.out::println);
        System.out.println(stringList);
        //map
        stringList.stream().map(String::toUpperCase).sorted((a, b) -> b.compareTo(a)).forEach(System.out::println);
        //match
        boolean anyStartsWithA = stringList.stream().anyMatch(s -> s.startsWith("a"));
        System.out.println("anyStartsWithA = " + anyStartsWithA);
        boolean allMatchStartWithA = stringList.stream().allMatch(s -> s.startsWith("a"));
        System.out.println("allMatchWithA = " + allMatchStartWithA);
        boolean noneMatchStartZ = stringList.stream().noneMatch(s -> s.startsWith("z"));
        System.out.println("noneMatchStartZ = " + noneMatchStartZ);
        //count
        long count = stringList.stream().filter(s -> s.startsWith("b")).count();
        System.out.println("count = " + count);
        //reduce规约 这是一个 最终操作 ，允许通过指定的函数来讲stream中的多个元素规约为一个元素，规约后的结果是通过Optional 接口表示的：
        //reduce方法 有起始值返回运算后的结果 无起始值返回Optional对象
        Optional<String> reduce = stringList.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);
        reduce.ifPresent(System.out::println);
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        // 字符串连接，concat = "ABCD"
        System.out.println("concat = " + concat);
        Double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        // 求最小值，minValue = -3.0
        System.out.println("minValue = " + minValue);
        Integer sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        // 求和，sumValue = 10, 有起始值
        System.out.println("sumValue = " + sumValue);
        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println("sumValue = " + sumValue);
        concat = Stream.of("a", "B", "c", "D", "e", "F").filter(s -> s.compareTo("Z") > 0).reduce("", String::concat);
        // 过滤，字符串连接，concat = "ace"
        System.out.println("concat = " + concat);
    }
}

class TestParallelStreams{
    public static void main(String[] args) {
        int max=1000000;
        List<String> values=new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        long time = System.currentTimeMillis();
        long count = values.stream().sorted().count();
        System.out.println("串行排序 所需时间 "+(System.currentTimeMillis()-time));

        time=System.currentTimeMillis();
        count = values.parallelStream().sorted().count();
        System.out.println("并行排序 所需时间 "+(System.currentTimeMillis()-time));
    }
}

class TestMap{

    public static void main(String[] args) {
        Map<Integer,String> map=new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i,"val"+i);
        }
        //如果key不存在再put absent缺席
        map.putIfAbsent(5,"123");

        map.computeIfPresent(3,(num,val)->val+num);
        map.computeIfPresent(9,(num,val)->null);
        System.out.println(map.containsKey(9));
        map.computeIfAbsent(23,num->"val"+num);
        System.out.println(map.containsKey(23));
        map.computeIfAbsent(3,num->"bam");
        System.out.println(map.get(3));
        map.forEach((id,val)->System.out.println(id+" "+val));

        map.remove(3,"val3");
        System.out.println(map.get(3));
        map.remove(3,"val33");
        System.out.println(map.get(3));
        System.out.println(map.getOrDefault(43,"defaultValue"));
        map.merge(9,"val9",(value,newValue)->value.concat(newValue));
        System.out.println(map.get(9));
        map.merge(9,"concat",(value,newValue)->value.concat(newValue));
        System.out.println(map.get(9));
    }
}