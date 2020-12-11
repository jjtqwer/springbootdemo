package com.example.springbootdemo.entity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import lombok.Data;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
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

        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
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