# 学习笔记

[TOC]

## 收获

### 观念上的改变

本周最大的收获就是改变了之前对算法学习畏惧心理以及做算法题的错误的方式和方法。

从事软件开发许多年以来，学习了java、JavaScript、c等语言和各种应用框架，也做了不少大、中型的软件项目，但总觉得在算法与数据结构方面（特别是算法）还存在很大的不足，对个人的职业发展也产生了一定的影响。

由于是非计算机专业半路转行，在算法与数据结构方面的基础是比较薄弱的，因此，一直以来，也尝试通过看书、看相关的教学课程等很多方法，但总是收效胜微，自信心受到了很大的打击，因此对算法与数据结构也产生了畏惧的心理。

直到报了极客大学算法训练营的课程，听了谭超老师的讲解，学到了秘密武器“五毒神掌”，感觉相见恨晚。都说好的开始就是成功的一半，我真的希望这一次我能够在算法与数据结构方面有一个大的提升，实现个人职业生涯的一次跃迁。

在此先谢谢谭超老师和极客大学的班主任及各位辅导老师！！！

## 本周学习感受

总的来讲本周做的不好，没有能够按照老师所说每日都刷题，主要是由于本人之前报了架构师训练营，在报名算法训练营的时候没有考虑到时间上限制问题，再加上日常的工作，因此有点手忙脚乱。争取从下周开始严格按照老师的要求去执行。

## 本周学习的知识点

- 数组
- 链表
- 跳表

> 只能用于元素有序的情况。
> 所以，跳表（skip list）对标的是平衡树（AVL Tree）和二分查找， 是一种 插入/删除/搜索 都是 O(log n) 的数据结构。1989 年出现。
> 它最大的优势是原理简单、容易实现、方便扩展、效率更高。因此 在一些热门的项目里用来替代平衡树，如 Redis、LevelDB 等。
> 时间复杂度：
> n/2、n/4、n/8、第 k 级索引结点的个数就是 n/(2^k)
> 假设索引有 h 级，最高级的索引有 2 个结点。n/(2^h) = 2，从而求得 h = log2(n)-1

- 栈
- 队列
- 优先队列
- 双端队列

> 简单理解：两端可以进出的 Queue Deque - double ended queue
> 插入和删除都是 O(1) 操作

## 课堂作业

### 1. 用 add first 或 add last 这套新的 API 改写 Deque 的代码

```java
package cn.com.kevin.algorithm;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 自定义的双端队列
 */
public class MyDeque {

    public static void main(String[] args) {
        // 因为LinkedList 实现了Deque接口，因此使用面向接口编程的方式定义双端队列
        Deque<String> deque = new LinkedList<String>();
//        deque.push("a");
//        deque.push("b");
//        deque.push("c");
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");
        System.out.println("添加完元素：" + deque);

//        String str = deque.peek();
//        System.out.println("peek获得的字符：" + str);
//        System.out.println("peek后：" + deque);

        String str = deque.peekFirst();
        System.out.println("peekFirst获得的字符：" + str);
        System.out.println("peekFirst：" + deque);


        while (deque.size() > 0){
            // System.out.println("pop:" + deque.pop());
            System.out.println("removeFirst:" + deque.removeFirst());
        }

        //System.out.println("pop后:" + deque);
        System.out.println("removeFirst后:" + deque);

    }
}

```

### 2. 分析 Queue 和 Priority Queue 的源码

#### Queue

在java语言中 Queue 是一个接口，其继承了 java.util.Collection 接口

##### 子接口

Set, List, Map, SortedSet, SortedMap

##### 实现类

HashSet, TreeSet, ArrayList, LinkedList, Vector, Collections, Arrays, AbstractCollection

##### 定义的方法

###### 会进行容量检查的

- add(E e):添加一个元素
- remove():删除一个元素
- element() ：查看最上一个元素

###### 不会进行容量检查的

- offer(E e):添加一个元素
- poll(E e):删除一个元素
- peek():查看最上一个元素

#### PriorityQueue

##### 定义

Queue（队列）是拥有先进先出（FIFO）特性的数据结构，PriorityQueue（优先级队列）是它的子类之一，不同于先进先出，它可以通过比较器控制元素的输出顺序（优先级）。

##### 继承关系

PriorityQueue类实现了Queue接口，其继承关系如下：

```java
public class PriorityQueue<E> extends AbstractQueue<E> implements java.io.Serializable
```

不是直接实现Queue，而是继承了AbstractQueue类。AbstractQueue应该也是模板抽象类（和AbstractMap和AbstractList类似）。

```java
public abstract class AbstractQueue<E> extends AbstractCollection<E> implements Queue<E>
```

AbstractQueue 继承了AbstractCollection类和实现了Queue接口。既然是模板类那肯定有模板方法。AbstractQueue源码中只实现了add、remove和elemet方法。

```java
public boolean add(E e) {
    if (offer(e)) // 调用offer
    ...
}

public E remove() {
    E x = poll(); // 调用poll
    ...
}

public E element() {
    E x = peek(); // 调用peek
    ...
}
```

可以看到，它们分别调用了offer、poll和peek。也就是说，如果要通过AbstractQueue实现队列，则必须实现offer、poll和peek方法。

##### 源码分析

PriorityQueue是通过“极大优先级堆”实现的，即堆顶元素是优先级最大的元素。算是集成了大根堆和小根堆的功能。

###### 重要属性

根据堆的特性，存储结构肯定是数组了；既然支持不同优先级，肯定有比较器，也就是说支持自定义排序和顺序排序。

```java
// 默认容量11
private static final int DEFAULT_INITIAL_CAPACITY = 11;
// 堆的存储结构，存储元素
transient Object[] queue; // 不可序列化
// 当前存储的元素数量
int size;
// 比较器，确定优先级高低
private final Comparator<? super E> comparator;
// 避免OOM，数组可以分配的最大容量
private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

```

###### 构造函数

PriorityQueue的构造函数有很多，主要参数是容量和比较器：

```java
public PriorityQueue() {
    this(DEFAULT_INITIAL_CAPACITY, null); // 默认自然序
}

public PriorityQueue(int initialCapacity) {
    this(initialCapacity, null); // 自定义初始容量
}

public PriorityQueue(Comparator<? super E> comparator) {
    this(DEFAULT_INITIAL_CAPACITY, comparator); // 自定义比较器
}

public PriorityQueue(int initialCapacity, Comparator<? super E> comparator) {
    ... // 对应赋值
}

public PriorityQueue(Collection<? extends E> c) {
    // 将c转成堆
    if (c instanceof SortedSet<?>) {
        ... // SortedSet自带比较器
    }
    else if (c instanceof PriorityQueue<?>) {
        ... // PriorityQueue自带比较器
    }
    else {
        ...
    }
}

public PriorityQueue(PriorityQueue<? extends E> c) {
    ... // 从PriorityQueue获取比较器，将c转成堆
}

public PriorityQueue(SortedSet<? extends E> c) {
    ...// 从SortedSet获取比较器，将c转成堆
}

```

###### 重要方法

- 扩容方法

```java
// minCapacity表示需要的最小容量
private void grow(int minCapacity) {
    int oldCapacity = queue.length; // 获取当前容量
    // Double size if small; else grow by 50%
    // 如果旧容量小于64，则增加旧容量+2的大小
    // 如果旧容量大于等于64，则增加旧容量的一半大小
    int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                                        (oldCapacity + 2) :
                                        (oldCapacity >> 1));
    // overflow-conscious code
    if (newCapacity - MAX_ARRAY_SIZE > 0) // 这里处理大容量
        newCapacity = hugeCapacity(minCapacity);
    queue = Arrays.copyOf(queue, newCapacity); // 复制已存储的数据
}
```

- offer方法(添加元素)

```java
// 每次增加元素，都要保证堆序。
public boolean offer(E e) {
    if (e == null)
        throw new NullPointerException();
    modCount++;
    int i = size;
    if (i >= queue.length)
        grow(i + 1); // 如果超出当前容量，则进行扩容
    siftUp(i, e); // 新元素都是增加在数组尾部，然后进行上移操作，即构建堆
    size = i + 1; // 当前大小加1
    return true;
}

// 上移就是不断和父结点比较
private static <T> void siftUpUsingComparator(
    int k, T x, Object[] es, Comparator<? super T> cmp) {
    while (k > 0) {
        int parent = (k - 1) >>> 1; // 父结点下标
        Object e = es[parent];
        if (cmp.compare(x, (T) e) >= 0) // 优先级高则继续上移比较
            break;
        es[k] = e;
        k = parent;
    }
    es[k] = x;
}

```

- poll方法(删除一个元素)

```java
// poll方法会返回队首元素（堆顶），并将元素从堆中删除。删除过程，是将第一个元素与最后一个元素进行替换，
// 然后再进行下移调整操作。
public E poll() {
    final Object[] es;
    final E result;
    // 返回堆顶元素
    if ((result = (E) ((es = queue)[0])) != null) {
        modCount++;
        final int n;
        final E x = (E) es[(n = --size)]; // 把尾部元素换到第一个
        es[n] = null;
        if (n > 0) {
            final Comparator<? super E> cmp;
            if ((cmp = comparator) == null) // 自然序时，下移调整
                siftDownComparable(0, x, es, n);
            else // 自定义序下移调整
                siftDownUsingComparator(0, x, es, n, cmp);
        }
    }
    return result;
}

private static <T> void siftDownUsingComparator(
    int k, T x, Object[] es, int n, Comparator<? super T> cmp) {
    // assert n > 0;
    int half = n >>> 1; // 最后一个非叶子结点下标（因为size已经减1了）
    while (k < half) {
        int child = (k << 1) + 1; // 左孩子
        Object c = es[child];
        int right = child + 1; // 右孩子
        if (right < n && cmp.compare((T) c, (T) es[right]) > 0)
            c = es[child = right]; // 从左右孩子中挑选优先级高的
        if (cmp.compare(x, (T) c) <= 0)
            break;
        es[k] = c; // 将目标元素下移
        k = child;
    }
    es[k] = x;
}
```

- remove方法

poll方法可以看出是remove方法的特例，即固定删除第一个元素。

```java
public boolean remove(Object o) {
    int i = indexOf(o); // 找到待删除元素位置
    if (i == -1)
        return false;
    else {
        removeAt(i); // 删除指定位置元素
        return true;
    }
}

E removeAt(int i) {
        // assert i >= 0 && i < size;
    final Object[] es = queue;
    modCount++;
    int s = --size; // size已经减1
    if (s == i) // removed last element
        es[i] = null; // 已经删除到最后一个元素
    else {
        E moved = (E) es[s]; // 尾元素
        es[s] = null;
        siftDown(i, moved); // 指定元素换尾元素，然后调整
        if (es[i] == moved) {
            siftUp(i, moved); // 如果指定位置换成了尾元素（没有发生下移）则进行上移操作
            if (es[i] != moved)
                return moved;
        }
    }
    return null; // 正常删除时返回null
}
```

###### 总结

- PriorityQueue是基于最大优先级堆实现的，根据比较器的情况可以是大根堆或者小根堆；
- PriorityQueue不支持null；
- PriorityQueue不是线程安全的，多线程环境下可以使用java.util.concurrent.PriorityBlockingQueue；
- 使用iterator()遍历时，不保证输出的序列是有序的，其实遍历的是存储数组。
