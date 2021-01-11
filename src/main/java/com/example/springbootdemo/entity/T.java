package com.example.springbootdemo.entity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import lombok.Data;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.http.Cookie;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Data
public class T {
    private int age;
    private String name;
    private int id;

    public T() {

    }

    public T(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        T t = (T) o;
        return Objects.equals(name, t.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public static void main(String[] args) {
        T t1 = new T();
        t1.setName("name1");
        T t2 = new T();
        t2.setName("name1");
        System.out.println(t1.equals(t2));
        System.out.println(t2.equals(t1));
        System.out.println(t1.hashCode());
        System.out.println(t2.hashCode());
//        Integer i1 = 33;
//        Integer i2 = 33;
//        System.out.println(i1 == i2);// 输出 true  默认创建了数值[-128，127] 的相应类型的缓存数据
//        Integer i11 = 333;
//        Integer i22 = 333;
//        System.out.println(i11 == i22);// 输出 false
//        Double i3 = 1.2;
//        Double i4 = 1.2;
//        System.out.println(i3 == i4);// 输出 false  Float,Double 并没有实现常量池技术

        Integer i1 = 40;
        Integer i2 = 40;
        Integer i3 = 0;
        Integer i4 = new Integer(40);
        Integer i5 = new Integer(40);
        Integer i6 = new Integer(0);

        System.out.println("i1=i2   " + (i1 == i2));  //默认创建了数值[-128，127] 的相应类型的缓存数据
        System.out.println("i1=i2+i3   " + (i1 == i2 + i3));
        //语句 i4 == i5 + i6，因为+这个操作符不适用于 Integer 对象，首先 i5 和 i6 进行自动拆箱操作，进行数值相加，即 i4 == 40。然后 Integer 对象无法与数值进行直接比较，
        // 所以 i4 自动拆箱转为 int 值 40，最终这条语句转为 40 == 40 进行数值比较。
        System.out.println("i1=i4   " + (i1 == i4));
        System.out.println("i4=i5   " + (i4 == i5));
        System.out.println("i4=i5+i6   " + (i4 == i5 + i6));
        System.out.println("40=i5+i6   " + (40 == i5 + i6));

    }
}

class Test {
    public static void main(String[] args) throws Exception {
        //获取键盘输入
//        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
//        String s = input.readLine();
//        System.out.println("s = " + s);
//        input.close();
        int num1 = 10;
        int num2 = 20;

        swap(num1, num2);

        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        //在 swap 方法中，a、b 的值进行交换，并不会影响到 num1、num2。因为，a、b 中的值，只是从 num1、num2 的复制过来的。也就是说，a、b 相当于 num1、num2 的副本，
        // 副本的内容无论怎么修改，都不会影响到原件本身。

        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(arr[0]);
        change(arr);
        System.out.println(arr[0]);
        //array 被初始化 arr 的拷贝也就是一个对象的引用，也就是说 array 和 arr 指向的是同一个数组对象。 因此，外部对引用对象的改变会反映到所对应的对象上。T

        T s1 = new T("小张");
        T s2 = new T("小李");
        Test.swap(s1, s2);
        System.out.println("s1:" + s1.getName());
        System.out.println("s2:" + s2.getName());
        //方法并没有改变存储在变量 s1 和 s2 中的对象引用。swap 方法的参数 x 和 y 被初始化为两个对象引用的拷贝，这个方法交换的是这两个拷贝

    }

    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

    public static void swap(T x, T y) {
        T temp = x;
        x = y;
        y = temp;
        System.out.println("x:" + x.getName());
        System.out.println("y:" + y.getName());
    }

    public static void change(int[] array) {
        // 将数组的第一个元素变为0
        array[0] = 0;
    }

}

class Test2 {

    public static void main(String[] args) {
//        Double i1 = 100.0;
//        Double i2 = 100.0;
//        Double i3 = 200.0;
//        Double i4 = 200.0;
//
//        System.out.println(i1==i2);
//        System.out.println(i3==i4);

        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        Long h = 2L;
        // t t t f t f t
        System.out.println(c == d);
        System.out.println(e == f);//
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));//易错
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));
        System.out.println(g.equals(a + h));
        //当 "=="运算符的两个操作数都是 包装器类型的引用，则是比较指向的是否是同一个对象，而如果其中有一个操作数是表达式（即包含算术运算）则比较的是数值（即会触发自动拆箱的过程）。
        // 另外，对于包装器类型，equals方法并不会进行类型转换。
        //第一个和第二个输出结果没有什么疑问。第三句由于  a+b包含了算术运算，因此会触发自动拆箱过程（会调用intValue方法），因此它们比较的是数值是否相等。
        // 而对于c.equals(a+b)会先触发自动拆箱过程，再触发自动装箱过程，也就是说a+b，会先各自调用intValue方法，得到了加法运算后的数值之后，便调用Integer.valueOf方法，
        // 再进行equals比较。同理对于后面的也是这样，不过要注意倒数第二个和最后一个输出的结果（如果数值是int类型的，装箱过程调用的是Integer.valueOf；如果是long类型的，
        // 装箱调用的Long.valueOf方法）。

        int i = 1;
        long l = 23;
        long l2 = i + l;
        System.out.println(l2);
    }
}

class Test3 {
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<String>();
        list1.add("abc");

        ArrayList<Integer> list2 = new ArrayList<Integer>();
        list2.add(123);

        System.out.println(list1.getClass() == list2.getClass());
        //我们定义了两个ArrayList数组，不过一个是ArrayList<String>泛型类型的，只能存储字符串；一个是ArrayList<Integer>泛型类型的，只能存储整数，最后，
        // 我们通过list1对象和list2对象的getClass()方法获取他们的类的信息，最后发现结果为true。说明泛型类型String和Integer都被擦除掉了，只剩下原始类型。
    }
}

class Person {
    private Integer age;

    public Person(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }


    public static void main(String[] args) {
        TreeMap<Person, String> treeMap = new TreeMap<>(new Comparator<Person>() {
            @Override
            public int compare(Person person1, Person person2) {
                int num = person1.getAge() - person2.getAge();
                return Integer.compare(num, 0);
            }
        });
        treeMap.put(new Person(3), "person1");
        treeMap.put(new Person(18), "person2");
        treeMap.put(new Person(35), "person3");
        treeMap.put(new Person(16), "person4");
        treeMap.entrySet().stream().forEach(personStringEntry -> {
            System.out.println(personStringEntry.getValue());
        });
    }

    static Map<Integer, String> map = new HashMap() {{
        // 添加数据
        for (int i = 0; i < 10; i++) {
            put(i, "val:" + i);
        }
    }};
}

class Test4 {
    public static void main(String[] args) {
        String[] str = new String[]{"you", "wu"};
        List<String> list = Arrays.asList(str);
        //        list.add("jjt");//运行时报错：UnsupportedOperationException
//        list.remove("you");//运行时报错：UnsupportedOperationException
//        list.clear();//运行时报错：UnsupportedOperationException
//        str[0]="you123";
//        System.out.println("list = " + list);
        //正确的将数组转换为ArrayList
        //1.List list2=new ArrayList(Arrays.asList(str));
        //2.Integer [] myArray = { 1, 2, 3 };
        //List myList = Arrays.stream(myArray).collect(Collectors.toList());
        ////基本类型也可以实现转换（依赖boxed的装箱操作）
        //int [] myArray2 = { 1, 2, 3 };
        //List myList = Arrays.stream(myArray2).boxed().collect(Collectors.toList());
//        List list2=new ArrayList(Arrays.asList(str));
        int[] myArray2 = {1, 2, 3};
        List myList2 = Arrays.stream(myArray2).boxed().collect(Collectors.toList());
        System.out.println("myList2 = " + myList2);
        List<String> list2 = Arrays.stream(str).collect(Collectors.toList());
        list2.add("jjt");
        list2.remove("you");
        list2.clear();
        System.out.println("list2 = " + list2);
//       3. 对于不可变集合，你可以使用ImmutableList类及其of()与copyOf()工厂方法：
        List<String> il = ImmutableList.of("string", "elements");
        System.out.println("il = " + il);
        List<String> il2 = ImmutableList.copyOf(str);
        System.out.println("il2 = " + il2);
        //4.对于可变集合，你可以使用Lists类及其newArrayList()工厂方法：
        ArrayList<String> list1 = Lists.newArrayList(list);
        list1.add("1");
        System.out.println("list1 = " + list1);
        ArrayList<String> list3 = Lists.newArrayList(str);
        list3.add("3");
        System.out.println("list3 = " + list3);
        ArrayList<String> list4 = Lists.newArrayList("or", "string", "elements");
        list4.add("4");
        System.out.println("list4 = " + list4);
        // 5.使用 Apache Commons Collections
        List<String> list5 = new ArrayList<String>();
        Collections.addAll(list5, str);
        list5.add("5");
        System.out.println("list5 = " + list5);
        //使用 Java9 的 List.of()方法
//        Integer[] array = {1, 2, 3};
//        List<Integer> list = List.of(array);
//        System.out.println(list); /* [1, 2, 3] */
        /* 不支持基本数据类型 */
//        当传入一个原生数据类型数组时，Arrays.asList() 的真正得到的参数就不是数组中的元素，而是数组对象本身！此时 List 的唯一元素就是这个数组，这也就解释了下面的代码。
//        int[] myArray = { 1, 2, 3 };
//        List myList = Arrays.asList(myArray);
//        System.out.println(myList.size());//1
//        System.out.println(myList.get(0));//数组地址值
//        //System.out.println(myList.get(1));//报错：ArrayIndexOutOfBoundsException
//        int [] array=(int[]) myList.get(0);
//        System.out.println(array[2]);//3
//        Arrays.asList() 方法返回的并不是 java.util.ArrayList ，而是 java.util.Arrays 的一个内部类,这个内部类并没有实现集合的修改方法或者说并没有重写这些方法。
        List myList = Arrays.asList(1, 2, 3);
        System.out.println(myList.getClass());//class java.util.Arrays$ArrayList
        String[] strs = new String[10];
        for (int i = 0; i < 5; i++) {
            strs[i] = i + "";
        }
        System.out.println("strs = " + Arrays.asList(strs));
        String[] strings = Arrays.copyOf(strs, 5);
        System.out.println("strings = " + Arrays.asList(strings));
        ListIterator<String> stringListIterator = list5.listIterator(1);
        System.out.println("stringListIterator = " + stringListIterator.next());

    }
}

class Test5 {
    public static void main(String[] args) {
        //Collection.toArray()方法使用的坑&如何反转数组
        //该方法是一个泛型方法：<T> T[] toArray(T[] a); 如果toArray方法中没有传递任何参数的话返回的是Object类型数组。
        String[] s = new String[]{
                "dog", "lazy", "a", "over", "jumps", "fox", "brown", "quick", "A"
        };
        List<String> list = Arrays.asList(s);
        Collections.reverse(list);
        s = list.toArray(new String[0]);//没有指定类型的话会报错
        System.out.println(list);
        System.out.println("s = " + Arrays.asList(s));
        //由于JVM优化，new String[0]作为Collection.toArray()方法的参数现在使用更好，new String[0]就是起一个模板的作用，指定了返回数组的类型，0是为了节省空间，
        // 因为它只是为了说明返回的类型。详见：https://shipilev.net/blog/2016/arrays-wisdom-ancients/

        //可以使用Collection#removeIf()方法删除满足特定条件的元素,如
        //java.util包下面的所有的集合类都是fail-fast的，而java.util.concurrent包下面的所有的类都是fail-safe的。
        List<Integer> list2 = new ArrayList<>();
        for (int i = 1; i <= 10; ++i) {
            list2.add(i);
        }
        list2.removeIf(filter -> filter % 2 == 0); /* 删除list中的所有偶数 */
        System.out.println(list2); /* [1, 3, 5, 7, 9] */
    }
}

class Person2 {
    static {
        System.out.println("父类静态代码块");
    }

    {
        System.out.println("父类非静态代码块");
    }

    static {
        i = 3;
        //System.out.println("i = " + i);
    }

    //    静态代码块对于定义在它之后的静态变量，可以赋值，但是不能访问.编译错误
    private static int i;

    public Person2() {
        System.out.println("父类构造方法");
    }
}

class Male extends Person2 {
    static {
        System.out.println("子类静态代码块");
    }

    {
        System.out.println("子类非静态代码块");
    }

    public Male() {
        System.out.println("子类构造方法");
    }

    public static void main(String[] args) {
        Person2 p = new Male();
        Person2 p2 = new Male();
        //该类不管创建多少对象，静态代码块只执行一次.非静态代码创建对象一次就执行一次
    }
}

class LinkedListTest {
    public static void main(String[] args) {
        //创建存放int类型的linkedList
        LinkedList<Integer> linkedList = new LinkedList<>();
        /************************** linkedList的基本操作 ************************/
        linkedList.addFirst(0); // 添加元素到列表开头
        linkedList.add(1); // 在列表结尾添加元素
        linkedList.add(2, 2); // 在指定位置添加元素
        linkedList.addLast(3); // 添加元素到列表结尾

        System.out.println("LinkedList（直接输出的）: " + linkedList);

        System.out.println("getFirst()获得第一个元素: " + linkedList.getFirst()); // 返回此列表的第一个元素
        System.out.println("getLast()获得第最后一个元素: " + linkedList.getLast()); // 返回此列表的最后一个元素
        System.out.println("removeFirst()删除第一个元素并返回: " + linkedList.removeFirst()); // 移除并返回此列表的第一个元素
        System.out.println("removeLast()删除最后一个元素并返回: " + linkedList.removeLast()); // 移除并返回此列表的最后一个元素
        System.out.println("After remove:" + linkedList);
        System.out.println("contains()方法判断列表是否包含1这个元素:" + linkedList.contains(1)); // 判断此列表包含指定元素，如果是，则返回true
        System.out.println("该linkedList的大小 : " + linkedList.size()); // 返回此列表的元素个数

        /************************** 位置访问操作 ************************/
        System.out.println("-----------------------------------------");
        linkedList.set(1, 3); // 将此列表中指定位置的元素替换为指定的元素
        System.out.println("After set(1, 3):" + linkedList);
        System.out.println("get(1)获得指定位置（这里为1）的元素: " + linkedList.get(1)); // 返回此列表中指定位置处的元素

        /************************** Search操作 ************************/
        System.out.println("-----------------------------------------");
        linkedList.add(3);
        System.out.println("indexOf(3): " + linkedList.indexOf(3)); // 返回此列表中首次出现的指定元素的索引
        System.out.println("lastIndexOf(3): " + linkedList.lastIndexOf(3));// 返回此列表中最后出现的指定元素的索引

        /************************** Queue操作 ************************/
        System.out.println("-----------------------------------------");
        System.out.println("peek(): " + linkedList.peek()); // 获取但不移除此列表的头
        System.out.println("element(): " + linkedList.element()); // 获取但不移除此列表的头
        linkedList.poll(); // 获取并移除此列表的头
        System.out.println("After poll():" + linkedList);
        linkedList.remove();
        System.out.println("After remove():" + linkedList); // 获取并移除此列表的头
        linkedList.offer(4);
        System.out.println("After offer(4):" + linkedList); // 将指定元素添加到此列表的末尾

        /************************** Deque操作 ************************/
        System.out.println("-----------------------------------------");
        linkedList.offerFirst(2); // 在此列表的开头插入指定的元素
        System.out.println("After offerFirst(2):" + linkedList);
        linkedList.offerLast(5); // 在此列表末尾插入指定的元素
        System.out.println("After offerLast(5):" + linkedList);
        System.out.println("peekFirst(): " + linkedList.peekFirst()); // 获取但不移除此列表的第一个元素
        System.out.println("peekLast(): " + linkedList.peekLast()); // 获取但不移除此列表的第一个元素
        linkedList.pollFirst(); // 获取并移除此列表的第一个元素
        System.out.println("After pollFirst():" + linkedList);
        linkedList.pollLast(); // 获取并移除此列表的最后一个元素
        System.out.println("After pollLast():" + linkedList);
        linkedList.push(2); // 将元素推入此列表所表示的堆栈（插入到列表的头）
        System.out.println("After push(2):" + linkedList);
        linkedList.pop(); // 从此列表所表示的堆栈处弹出一个元素（获取并移除列表第一个元素）
        System.out.println("After pop():" + linkedList);
        linkedList.add(3);
        linkedList.removeFirstOccurrence(3); // 从此列表中移除第一次出现的指定元素（从头部到尾部遍历列表）
        System.out.println("After removeFirstOccurrence(3):" + linkedList);
        linkedList.removeLastOccurrence(3); // 从此列表中移除最后一次出现的指定元素（从尾部到头部遍历列表）
        System.out.println("After removeFirstOccurrence(3):" + linkedList);

        /************************** 遍历操作 ************************/
        System.out.println("-----------------------------------------");
        linkedList.clear();
        for (int i = 0; i < 100000; i++) {
            linkedList.add(i);
        }
        // 迭代器遍历
        long start = System.currentTimeMillis();
        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        long end = System.currentTimeMillis();
        System.out.println("Iterator：" + (end - start) + " ms");

        // 顺序遍历(随机遍历)
        start = System.currentTimeMillis();
        for (int i = 0; i < linkedList.size(); i++) {
            linkedList.get(i);
        }
        end = System.currentTimeMillis();
        System.out.println("for：" + (end - start) + " ms");

        // 另一种for循环遍历
        start = System.currentTimeMillis();
        for (Integer i : linkedList)
            ;
        end = System.currentTimeMillis();
        System.out.println("for2：" + (end - start) + " ms");

        // 通过pollFirst()或pollLast()来遍历LinkedList
        LinkedList<Integer> temp1 = new LinkedList<>();
        temp1.addAll(linkedList);
        start = System.currentTimeMillis();
        while (temp1.size() != 0) {
            temp1.pollFirst();
        }
        end = System.currentTimeMillis();
        System.out.println("pollFirst()或pollLast()：" + (end - start) + " ms");

        // 通过removeFirst()或removeLast()来遍历LinkedList
        LinkedList<Integer> temp2 = new LinkedList<>();
        temp2.addAll(linkedList);
        start = System.currentTimeMillis();
        while (temp2.size() != 0) {
            temp2.removeFirst();
        }
        end = System.currentTimeMillis();
        System.out.println("removeFirst()或removeLast()：" + (end - start) + " ms");

    }
}

class Test6 {
    public static void main(String[] args) {
        // 获取 Java 线程管理 MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的 monitor 和 synchronizer 信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        // 遍历线程信息，仅打印线程 ID 和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }
    }
}

class DeadLockDemo {
    private static Object resource1 = new Object();//资源 1
    private static Object resource2 = new Object();//资源 2

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + "get resource1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + "get resource2");
                }
            }
        }, "线程 1").start();

        /*new Thread(() -> {
            synchronized (resource2) {
                System.out.println(Thread.currentThread() + "get resource2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource1");
                synchronized (resource1) {
                    System.out.println(Thread.currentThread() + "get resource1");
                }
            }
        }, "线程 2").start();*/

        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + "get resource1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + "get resource2");
                }
            }
        }, "线程 2").start();
    }
}

class Singleton {
    //双重校验锁实现对象单例（线程安全）
    //volatile 关键字 除了防止 JVM 的指令重排 ，还有一个重要的作用就是保证变量的可见性

    private volatile static Singleton uniqueInstance;

    private Singleton() {
    }

    public static Singleton getUniqueInstance() {
        //先判断对象是否已经实例过，没有实例化过才进入加锁代码
        if (uniqueInstance == null) {
            //类对象加锁
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
    //需要注意 uniqueInstance 采用 volatile 关键字修饰也是很有必要。
    //uniqueInstance 采用 volatile 关键字修饰也是很有必要的， uniqueInstance = new Singleton(); 这段代码其实是分为三步执行：
    //1.为 uniqueInstance 分配内存空间
    //2.初始化 uniqueInstance
    //3.将 uniqueInstance 指向分配的内存地址
    //但是由于 JVM 具有指令重排的特性，执行顺序有可能变成 1->3->2。指令重排在单线程环境下不会出现问题，但是在多线程环境下会导致一个线程获得还没有初始化的实例。例如，
    // 线程 T1 执行了 1 和 3，此时 T2 调用 getUniqueInstance() 后发现 uniqueInstance 不为空，因此返回 uniqueInstance，但此时 uniqueInstance 还未被初始化。
    //使用 volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行。
}

class ThreadLocalExample implements Runnable {

    // SimpleDateFormat 不是线程安全的，所以每个线程都要有自己独立的副本
    private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd HHmm"));

    @Override
    public void run() {
        System.out.println("Thread Name=" + Thread.currentThread().getName() + " default Formatter=" + formatter.get().toPattern());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        formatter.set(new SimpleDateFormat());
        System.out.println("Thread Name=" + Thread.currentThread().getName() + " formatter = " + formatter.get().toPattern());
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalExample obj = new ThreadLocalExample();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(obj, "" + i);
            Thread.sleep(new Random().nextInt(1000));
            t.start();
        }
    }
}

/**
 * 这是一个简单的Runnable类，需要大约5秒钟来执行其任务。
 */
class MyRunnable implements Runnable {

    private String command;

    public MyRunnable(String command) {
        this.command = command;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start Time =" + new Date());
        processCommand();
        System.out.println(Thread.currentThread().getName() + " End Time =" + new Date());
    }

    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.command;
    }
}

class ThreadPoolExecutorDemo {

    public static final int CORE_POOL_SIZE = 5;
    public static final int MAX_POOL_SIZE = 10;
    public static final int QUEUE_CAPACITY = 100;
    public static final Long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) {
        //使用阿里巴巴推荐的创建线程池的方式
        //通过ThreadPoolExecutor构造函数自定义参数创建
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        for (int i = 0; i < 10; i++) {
            //创建MyRunnable对象（MyRunnable类实现了Runnable 接口）
            Runnable worker = new MyRunnable("" + i);
            //执行Runnable
            executor.execute(worker);
        }
        //终止线程池
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finshed all threads");
        //corePoolSize: 核心线程数为 5。
        //maximumPoolSize ：最大线程数 10
        //keepAliveTime : 等待时间为 1L。
        //unit: 等待时间的单位为 TimeUnit.SECONDS。
        //workQueue：任务队列为 ArrayBlockingQueue，并且容量为 100;  等待队列容量
        //handler:饱和策略为 CallerRunsPolicy。

        //我们在代码中模拟了 10 个任务，我们配置的核心线程数为 5 、等待队列容量为 100 ，所以每次只可能存在 5 个任务同时执行，剩下的 5 个任务会被放到等待队列中去。
        // 当前的 5 个任务之行完成后，才会之行剩下的 5 个任务。
        //ThreadPoolExecutor 饱和策略定义:
        //如果当前同时运行的线程数量达到最大线程数量并且队列也已经被放满了任时，ThreadPoolTaskExecutor 定义一些策略:
        //**ThreadPoolExecutor.AbortPolicy**：抛出 RejectedExecutionException来拒绝新任务的处理。
        //**ThreadPoolExecutor.CallerRunsPolicy**：调用执行自己的线程运行任务，也就是直接在调用execute方法的线程中运行(run)被拒绝的任务，如果执行程序已关闭，则会丢弃该任务。因此这种策略会降低对于新任务提交速度，影响程序的整体性能。如果您的应用程序可以承受此延迟并且你要求任何一个任务请求都要被执行的话，你可以选择这个策略。
        //ThreadPoolExecutor.DiscardPolicy： 不处理新任务，直接丢弃掉。
        //ThreadPoolExecutor.DiscardOldestPolicy： 此策略将丢弃最早的未处理的任务请求。
    }
}

class MyCallable implements Callable<String>{

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        //返回执行当前 Callable 的线程名字
        return Thread.currentThread().getName();
    }
}

class CallableDemo{
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) {
        //使用阿里巴巴推荐的创建线程池的方式
        //通过ThreadPoolExecutor构造函数自定义参数创建
        ThreadPoolExecutor executor=new ThreadPoolExecutor(
          CORE_POOL_SIZE,
          MAX_POOL_SIZE,
          KEEP_ALIVE_TIME,
          TimeUnit.SECONDS,
          new ArrayBlockingQueue<>(QUEUE_CAPACITY),
          new ThreadPoolExecutor.CallerRunsPolicy()
        );

        List<Future<String>> futureList=new ArrayList<>();
        Callable<String> callable=new MyCallable();
        for (int i = 0; i < 10; i++) {
            //提交任务到线程池
            Future<String> future = executor.submit(callable);
            //将返回值 future 添加到 list，我们可以通过 future 获得 执行 Callable 得到的返回值
            futureList.add(future);
        }

        for (Future<String> future: futureList){
            try {
                System.out.println(new Date()+"::"+future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }
}

class AtomicIntegerTest {
    private AtomicInteger count = new AtomicInteger();

    //使用AtomicInteger之后，不需要对该方法加锁，也可以实现线程安全。
    public void increment() {
        System.out.println("increment ->" + count.incrementAndGet()); //先自增，然后返回当前的值
    }

    public int getCount() {
        return count.get();
    }

    public void getAndIncrement() {
        System.out.println("getAndIncrement ->" + count.getAndIncrement()); //返回当前的值，并自增
    }

    public static void main(String[] args) {
        AtomicIntegerTest at = new AtomicIntegerTest();
        at.getAndIncrement();
        System.out.println(at.getCount());
    }

    //AtomicInteger 类常用方法
    //public final int get() //获取当前的值
    //public final int getAndSet(int newValue)//获取当前的值，并设置新的值
    //public final int getAndIncrement()//获取当前的值，并自增
    //public final int getAndDecrement() //获取当前的值，并自减
    //public final int getAndAdd(int delta) //获取当前的值，并加上预期的值
    //boolean compareAndSet(int expect, int update) //如果输入的数值等于预期值，则以原子方式将该值设置为输入值（update）
    //public final void lazySet(int newValue)//最终设置为newValue,使用 lazySet 设置之后可能导致其他线程在之后的一小段时间内还是可以读到旧的值。
}

class CountDownLatchExample1{

    //处理文件的数量
    public static final int THREAR_COUNT=6;

    public static void main(String[] args) throws InterruptedException {
        // 创建一个具有固定线程数量的线程池对象（推荐使用构造方法创建）
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        final CountDownLatch countDownLatch=new CountDownLatch(THREAR_COUNT);
        for (int i = 0; i < THREAR_COUNT; i++) {
            final int threadNum=i;
            threadPool.execute(() ->{
                try {
                    //处理文件的业务操作
                    System.out.println("处理文件的业务操作");
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    //表示一个文件已经被完成
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        threadPool.shutdown();
        System.out.println("finish");
    }
}

interface Formula{

    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }

}

class Main {

    public static void main(String[] args) {
        // 通过匿名内部类方式访问接口
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };

        System.out.println(formula.calculate(100));     // 100.0
        System.out.println(formula.sqrt(16));           // 4.0

        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
        System.out.println(names);

    }

}

@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}

class Test1{
    public static void main(String[] args) {
//        // 将数字字符串转换为整数类型
//        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
//        Integer converted = converter.convert("123");
//        System.out.println(converted.getClass()); //class java.lang.Integer

//        Converter<String, Integer> converter = Integer::valueOf;
//        Integer converted = converter.convert("123");
//        System.out.println(converted.getClass());   //class java.lang.Integer

        Something something = new Something();
        Converter<String, String> converter = something::startsWith;
        String converted = converter.convert("Java");
        System.out.println(converted);    // "J"
    }

}

class Something {
    String startsWith(String s) {
        return String.valueOf(s.charAt(0));
    }
}

class MapDemo{

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        map.forEach((id, val) -> System.out.println(val));

        map.computeIfPresent(3, (num, val) -> val + num);
        System.out.println(map.get(3));

        map.computeIfPresent(9, (num,val) ->null);
        System.out.println(map.containsKey(9));

        map.computeIfAbsent(23,num->"val"+num);
        System.out.println(map.containsKey(23));

        map.computeIfAbsent(3,num->"bam");
        System.out.println(map.get(3));

        System.out.println(map.getOrDefault(42,"not found"));

        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9));             // val9
        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9));             // val9concat
    }
}

class ClockDemo{
    public static void main(String[] args) {

        //Clock 类提供了访问当前日期和时间的方法，Clock 是时区敏感的，可以用来取代 System.currentTimeMillis() 来获取当前的微秒数。
        // 某一个特定的时间点也可以使用 Instant 类来表示，Instant 类也可以用来创建旧版本的java.util.Date 对象。
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        System.out.println("millis = " + millis);

        Instant instant = clock.instant();
        System.out.println("instant = " + instant);
        Date date = Date.from(instant);
        System.out.println("date = " + date);

        //Timezones(时区)
        //输出所有区域标识符
        System.out.println(ZoneId.getAvailableZoneIds());

        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        System.out.println(zone1.getRules());// ZoneRules[currentStandardOffset=+01:00]
        System.out.println(zone2.getRules());// ZoneRules[currentStandardOffset=-03:00]

        //LocalTime(本地时间)
        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);
        LocalTime now3=LocalTime.now();
        System.out.println("now1 = " + now1);
        System.out.println("now2 = " + now2);
        System.out.println("now3 = " + now3);
        System.out.println(now1.isBefore(now2));  // false

        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);

        System.out.println(hoursBetween);       // -3
        System.out.println(minutesBetween);

        //LocalDate
        LocalDate today = LocalDate.now();//获取现在的日期
        System.out.println("今天的日期: "+today);//2019-03-12
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        System.out.println("明天的日期: "+tomorrow);//2019-03-13
        LocalDate yesterday = tomorrow.minusDays(2);
        System.out.println("昨天的日期: "+yesterday);//2019-03-11
        LocalDate independenceDay = LocalDate.of(2020, Month.DECEMBER, 14);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        System.out.println("今天是周几:"+dayOfWeek);//TUESDAY

        String str1 = "2014==04==12 01时06分09秒";
        // 根据需要解析的日期、时间字符串定义解析所用的格式器
        DateTimeFormatter fomatter1 = DateTimeFormatter
                .ofPattern("yyyy==MM==dd HH时mm分ss秒");

        LocalDateTime dt1 = LocalDateTime.parse(str1, fomatter1);
        System.out.println(dt1); // 输出 2014-04-12T01:06:09

        String str2 = "2014$$$四月$$$13 20小时";
        DateTimeFormatter fomatter2 = DateTimeFormatter
                .ofPattern("yyyy$$$MMM$$$dd HH小时");
        LocalDateTime dt2 = LocalDateTime.parse(str2, fomatter2);
        System.out.println(dt2); // 输出 2014-04-13T20:00

        //使用 DateTimeFormatter 格式化日期的示例
        LocalDateTime rightNow=LocalDateTime.now();
        String date2=DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(rightNow);
        System.out.println(date2);//2019-03-12T16:26:48.29
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        System.out.println(formatter.format(rightNow));//2019-03-12 16:26:48


    }
}

class Test7{
    public static void main(String[] args) {
        BigDecimal num = new BigDecimal("2.225667623").setScale(6,BigDecimal.ROUND_HALF_UP);//一般都会这样写最好 四舍五入（若舍弃部分>=.5，就进位）

        int count = num.scale();

        System.out.println(count);//6 返回的是小数点后位数
        System.out.println(.5);
        System.out.println(num);

        Integer i=1,i2=2;
        System.out.println(i2.compareTo(i));

        System.out.println(false || true && true);
        System.out.println(Boolean.parseBoolean("TruE"));
    }
}
//静态代理
//静态代理中，我们对目标对象的每个方法的增强都是手动完成的（后面会具体演示代码_），非常不灵活（_比如接口一旦新增加方法，目标对象和代理对象都要进行修改_）
// 且麻烦(_需要对每个目标类都单独写一个代理类)。 实际应用场景非常非常少，日常开发几乎看不到使用静态代理的场景。
//上面我们是从实现和应用角度来说的静态代理，从 JVM 层面来说， 静态代理在编译时就将接口、实现类、代理类这些都变成了一个个实际的 class 文件。
//1.定义发送短信的接口
interface SmsService {
    String send(String message);
}
//2.实现发送短信的接口
class SmsServiceImpl implements SmsService {
    @Override
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}

//3.创建代理类并同样实现发送短信的接口
class SmsProxy implements SmsService {

    private final SmsService smsService;

    public SmsProxy(SmsService smsService) {
        this.smsService = smsService;
    }

    @Override
    public String send(String message) {
        //调用方法之前，我们可以添加自己的操作
        System.out.println("before method send()");
        smsService.send(message);
        //调用方法之后，我们同样可以添加自己的操作
        System.out.println("after method send()");
        return null;
    }
}
//4.实际使用
class Main2 {
    public static void main(String[] args) {
        SmsService smsService = new SmsServiceImpl();
        SmsProxy smsProxy = new SmsProxy(smsService);
        smsProxy.send("java");
    }
}

// JDK 动态代理类使用步骤
//1.定义一个接口及其实现类；
//2.自定义 InvocationHandler 并重写invoke方法，在 invoke 方法中我们会调用原生方法（被代理类的方法）并自定义一些处理逻辑；
//3.通过 Proxy.newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h) 方法创建代理对象；

//1.定义发送短信的接口
interface SmsService2 {
    String send(String message);
}

//2.实现发送短信的接口
class SmsServiceImpl2 implements SmsService2 {
    @Override
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}

//3.定义一个 JDK 动态代理类
class DebugInvocationHandler implements InvocationHandler{

    /**
     * 代理类中的真实对象
     *
     */
    private final Object target;

    public DebugInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("before method:"+ method.getName());
        Object result = method.invoke(target, args);
        System.out.println("after method: "+ method.getName());
        return result;
    }
}

//4.获取代理对象的工厂类
class JdkProxyFactory{
    public static Object getProxy(Object target){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),//目标类的类加载
                target.getClass().getInterfaces(),//代理类需要实现的接口，可指定多个
                new DebugInvocationHandler(target) //实现了 InvocationHandler 接口的对象；
                );
    }
}

//5.实际使用
class Main3{
    public static void main(String[] args) {
        SmsService2 smsService2= (SmsService2) JdkProxyFactory.getProxy(new SmsServiceImpl2());
        smsService2.send("java");
    }
}

//CGLIB 动态代理类使用步骤
//1定义一个类；
//2自定义 MethodInterceptor 并重写 intercept 方法，intercept 用于拦截增强被代理类的方法，和 JDK 动态代理中的 invoke 方法类似；
//3通过 Enhancer 类的 create()创建代理类；

//1.实现一个使用阿里云发送短信的类
class AliSmsService {
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}

//2.自定义 MethodInterceptor（方法拦截器）
class DebugMethodInterceptor implements MethodInterceptor{
    /**
     * @param o           被代理的对象（需要增强的对象）
     * @param method      被拦截的方法（需要增强的方法）
     * @param args        方法入参
     * @param methodProxy 用于调用原始方法
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("before method "+method.getName());
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("after method " +method.getName());
        return object;
    }
}

//3.获取代理类
class CglibProxyFactory{

    public static Object getProxy(Class<?> clazz){
        // 创建动态代理增强类
        Enhancer enhancer = new Enhancer();
        // 设置类加载器
        enhancer.setClassLoader(clazz.getClassLoader());
        // 设置被代理类
        enhancer.setSuperclass(clazz);
        // 设置方法拦截器
        enhancer.setCallback(new DebugMethodInterceptor());
        //创建代理类
        return enhancer.create();
    }

    public static void main(String[] args) {
        String a="d撒旦NSA六年的撒罗尼了俺的撒罗尼";
        String[] split1 = a.split(";");
        System.out.println(Arrays.asList(split1));

        String s="的你们仨三！的就是哪怕;!第三轮!;的你扫你都扫地NSA六年！dnsalndanlk";
        List list=new ArrayList();
        String[] split = s.split(";");
        for (int i = 0; i <split.length ; i++) {
            String s1=split[i];
            if(s1.indexOf("!") ==0){
                s1=s1.substring(0,1)+s1.substring(1).replaceAll("！","!").replaceAll("!","");
                //
            }else{
                s1=s1.replaceAll("！","!").replaceAll("!","");
            }
            list.add(s1);
            if(i!=split.length-1){
                list.add(";");
            }
            System.out.println(s1);
        }
        System.out.println(list);

//        List<String> list1 = Arrays.asList(split);
//        System.out.println("list1 = " + list1);
//        Iterator<String> iterator = list1.iterator();
//        while (iterator.hasNext()){
//            String next = iterator.next();
//            if(next.indexOf("!") ==0){
//                next=next.substring(0,1)+next.substring(1).replaceAll("！","!").replaceAll("!","");
//                //
//            }else{
//                next=next.replaceAll("！","!").replaceAll("!","");
//            }
//            list.add(next);
//            list.add(";");
//        }
//        System.out.println(list);

    }
}

//4.实际使用
class CglibTest{
    public static void main(String[] args) {
        AliSmsService aliSmsService= (AliSmsService) CglibProxyFactory.getProxy(AliSmsService.class);
        aliSmsService.send("mysql");
        //JDK 动态代理和 CGLIB 动态代理对比
        //JDK 动态代理只能只能代理实现了接口的类，而 CGLIB 可以代理未实现任何接口的类。 另外， CGLIB 动态代理是通过生成一个被代理类的子类来拦截被代理类的方法调用，
        // 因此不能代理声明为 final 类型的类和方法。
        //就二者的效率来说，大部分情况都是 JDK 动态代理更优秀，随着 JDK 版本的升级，这个优势更加明显。
        //静态代理和动态代理的对比
        //灵活性 ：动态代理更加灵活，不需要必须实现接口，可以直接代理实现类，并且可以不需要针对每个目标类都创建一个代理类。另外
        // ，静态代理中，接口一旦新增加方法，目标对象和代理对象都要进行修改，这是非常麻烦的！
        //JVM 层面 ：静态代理在编译时就将接口、实现类、代理类这些都变成了一个个实际的 class 文件。而动态代理是在运行时动态生成类字节码，并加载到 JVM 中的。

        String s="促销额度-商业险保费比例/额度";
        System.out.println(s.contains("比例"));
        String s1="!您本次是通过";
        if(s1.contains("!")){
            String[] split = s1.split("\\!");
            System.out.println(split.length);
            for(String s2: split){
                System.out.println(s2);
            }
        }
        String s3="false";
        System.out.println(!!Boolean.valueOf(s3));

        Map map=new HashMap(16);
        map.put("ddd",20);
        BigDecimal ddd =new BigDecimal(map.get("ddd").toString());
        System.out.println(ddd.divide(new BigDecimal(100)));

        String ss="!保险车辆为贷款车辆，出险时第一受益人为贷款银行，保险期限内不得退保。";
        String ss2="！保险车辆为贷款车辆，出险时第一受益人为贷款银行，保险期限内不得退保。";

        List<Integer> list=Arrays.asList(6,6,2,3);
        System.out.println(list.get(4));
    }
}

class ThreadLocalTest{
    private List<String> messages=Lists.newArrayList();

    public static final ThreadLocal<ThreadLocalTest> holder=ThreadLocal.withInitial(ThreadLocalTest::new);

    public static void add(String message){
        holder.get().messages.add(message);
    }

    public static List<String> clear(){
        List<String> messages = holder.get().messages;
        holder.remove();
        System.out.println("size: "+holder.get().messages.size());
        return messages;
    }


    public static void main(String[] args) {
        //ThreadLocalTest.add("一枝花算不算浪漫");
//        System.out.println(holder.get().messages);
//        ThreadLocalTest.clear();
//        Arrays.copyOf(new int[5],60);
//        Cookie cookie=new Cookie("username","jjt");
//        cookie.setMaxAge(7*24*60*60);

//        Jedis jedis=new Jedis("localhost");
//        System.out.println(jedis.ping());
//        jedis.set("run","abc");
//        System.out.println(jedis.get("run"));
//        jedis.lpush("list","run");
//        jedis.lpush("list","goo");
//        jedis.lpush("list","tao");
//        List<String> list = jedis.lrange("list", 0, 2);
//        System.out.println(list);
//        Set<String> keys = jedis.keys("*");
//        Iterator<String> iterator = keys.iterator();
//        while (iterator.hasNext()){
//            String next = iterator.next();
//            System.out.println("next = " + next);
//        }
//        System.out.println("--------------------------");
//        jedis.select(1);
//        keys=jedis.keys("*");
//        iterator = keys.iterator();
//        while (iterator.hasNext()){
//            String next = iterator.next();
//            System.out.println("next = " + next);
//        }
        BigDecimal n=new BigDecimal(0.1d);
        System.out.println(n);
        BigDecimal n2=new BigDecimal("0.1");
        System.out.println(n2);
        BigDecimal n3=BigDecimal.valueOf(0.1d);
        System.out.println("n3 = " + n3);

        List<String> list=new ArrayList<>();
        list.add("1");
        list.add("2");
        for (String s: list){
            System.out.println(s);
            if("1".equals(s)){
                list.remove(s);
            }
        }
        System.out.println(isValid("([)]"));
        System.out.println(reverseString("qwe"));
    }

    //检查符号是否成对出现
    //给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断该字符串是否有效。
    //有效字符串需满足：
    //左括号必须用相同类型的右括号闭合。
    //左括号必须以正确的顺序闭合。
    //比如 "()"、"()[]{}"、"{[]}" 都是有效字符串，而 "(]" 、"([)]" 则不是。
    public static boolean isValid(String s){
        Map<Character,Character> mappings=new HashMap<>();
        mappings.put(')','(');
        mappings.put('}','{');
        mappings.put(']','[');

        Stack<Character> stack=new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(mappings.containsKey(chars[i])){
                char topElement = stack.empty() ? '#' : stack.pop();
                if(topElement != mappings.get(chars[i])){
                    return false;
                }
            }else{
                stack.push(chars[i]);
            }
        }
        return stack.isEmpty();
    }

    //反转字符串
    public static String reverseString(String s){
        char[] chars = s.toCharArray();
        Stack<Character> stack=new Stack<>();
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            stack.push(chars[i]);
        }
        for (int i = 0; i < chars.length; i++) {
            sb.append(stack.pop());
        }
        return sb.toString();

    }
}

class MyStack{
    private int[] storage;//存放栈中元素的数组
    private int capacity;//栈的容量
    private int count;//栈中元素数量
    private static final int GROW_FACTOR=2;

    public MyStack() {
        this.capacity=8;
        this.storage=new int[8];
        this.count=0;
    }

    public MyStack(int capacity) {
        if(capacity<1){
            throw new IllegalArgumentException("Capacity too small..");
        }
        this.capacity = capacity;
        this.storage=new int[capacity];
        this.count=0;
    }

    //入栈
    public void push (int value){
        if(count == capacity){
            ensureCapacity();
        }
        storage[count++]=value;
    }

    //确保容量大小
    private void ensureCapacity(){
        int newCapacity=capacity*GROW_FACTOR;
        storage=Arrays.copyOf(storage,newCapacity);
        capacity=newCapacity;
    }

    //返回栈顶元素并出栈
    private int pop(){
        if(count == 0){
            throw new IllegalArgumentException("Stack is empty");
        }
        return storage[--count];
    }

    //返回栈顶元素不出栈
    private int peek(){
        if(count == 0){
            throw new IllegalArgumentException("Stack is empty");
        }else{
            return storage[count-1];
        }
    }

    //判断栈是否为空
    private boolean isEmpty(){
        return count == 0;
    }

    //返回栈中元素的个数
    private int size(){
        return count;
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack(3);
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        myStack.push(6);
        myStack.push(7);
        myStack.push(8);
        System.out.println(myStack.peek());
        System.out.println(myStack.peek());
        for (int i = 0; i < 8; i++) {
            System.out.println(myStack.pop());
        }
        System.out.println(myStack.isEmpty());
        myStack.pop();

    }
}