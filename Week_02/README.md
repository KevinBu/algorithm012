<font size="20"><b>学习笔记</b></font>

[TOC]

# 本周学习的知识点

本周学习的知识点有：

- 哈希表、映射、集合
- 树、二叉树、二叉搜索树
- 堆和二叉堆
- 图

# 模板代码

## TreeNode

```java
/**
 * 树节点的Java实现
 */
public class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
    //..
}
```

## 二叉树的遍历

### 前序遍历（根-左-右）

#### 递归实现

```java
/**
 * 前序遍历（根-左-右）
 * 通过递归实现
 * @param node
 */
public void preOrderRecursion(TreeNode node){
    //如果结点为空则返回
    if(node==null) {
        return;
    }
    System.out.print(node.val+" ");
    preOrderRecursion(node.left);//访问左孩子
    preOrderRecursion(node.right);//访问右孩子
}
```

#### 非递归实现

```java
/**
 * 前序遍历（根-左-右）
 * 非递归实现(通过栈来实现)
 * @param root
 * @return
 */
public List<Integer> preOrderTraversal(TreeNode root) {
    List<Integer> resultList = new ArrayList<>();
    Stack<TreeNode> treeStack = new Stack<>();
    if(root==null) {
        //如果为空树则返回
        return resultList;
    }
    treeStack.push(root);
    while(!treeStack.isEmpty()){
        TreeNode tempNode=treeStack.pop(); 
        if(tempNode!=null){
            resultList.add(tempNode.val);//访问根节点
            treeStack.push(tempNode.right); //入栈右孩子
            treeStack.push(tempNode.left);//入栈左孩子
        }
    }
    return resultList;
}
```

### 中序遍历（左-根-右）

#### 递归实现

```java
/**
 * 中序遍历（左-根-右）
 * 递归实现
 * @param node
 */
public void inOrderRecur(TreeNode node) {
    if (node == null) {
        return;
    }
    inOrderRecur(node.left);
    System.out.print(node.val+" ");
    inOrderRecur(node.right);
}
```

#### 非递归实现

```java
/**
 * 中序遍历（左-根-右）
 * 非递归实现
 * @param root
 * @return
 */
public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<Integer>();

    Stack<TreeNode> stack = new Stack<TreeNode>();
    TreeNode cur = root;

    while(cur!=null || !stack.empty()){
        while(cur!=null){
            stack.add(cur);
            cur = cur.left;
        }
        cur = stack.pop();
        list.add(cur.val);
        cur = cur.right;
    }

    return list;
}
```

### 后续遍历（左-右-根）

#### 递归实现

```java
/**
 * 后序序遍历（左-右-根）
 * 递归实现
 * @param node
 */
public void postOrderRecursion(TreeNode node) {
    if (node == null) {
        return;
    }
    postOrderRecursion(node.left);
    postOrderRecursion(node.right);
    System.out.print(node.val+" ");
}
```

#### 非递归实现

```java
/**
 * 后序遍历（左-右-根）
 * 非递归实现
 * @param root
 * @return
 */
public List<Integer> postorderTraversal(TreeNode root) {
    Deque<TreeNode> stack = new LinkedList<>();
    stack.push(root);
    List<Integer> ret = new ArrayList<>();
    while (!stack.isEmpty()) {
        TreeNode node = stack.pop();
        if (node != null) {
            ret.add(node.val);
            stack.push(node.left);
            stack.push(node.right);
        }
    }
    Collections.reverse(ret);
    return ret;
}
```

# HashMap 实现

## 概述

HashMap 是基于哈希表的 Map 接口的非同步实现。此实现提供所有可选的映射操作，并允许使用 null 值和 null 键。此类不保证映射的顺序，特别是它不保证该顺序恒久不变。

此实现假定哈希函数将元素适当地分布在各桶之间，可为基本操作（get 和 put）提供稳定的性能。迭代 collection 视图所需的时间与 HashMap 实例的“容量”（桶的数量）及其大小（键-值映射关系数）成比例。所以，如果迭代性能很重要，则不要将初始容量设置得太高或将加载因子设置得太低。

需要注意的是：Hashmap 不是同步的，如果多个线程同时访问一个 HashMap，而其中至少一个线程从结构上（指添加或者删除一个或多个映射关系的任何操作）修改了，则必须保持外部同步，以防止对映射进行意外的非同步访问。

## 数据结构

在 Java 编程语言中，最基本的结构就是两种，一个是数组，另外一个是指针（引用），HashMap 就是通过这两个数据结构进行实现。**HashMap实际上是一个“链表散列”的数据结构，即数组和链表的结合体**。

HashMap 底层就是一个数组结构，数组中的每一项又是一个链表。当新建一个 HashMap 的时候，就会初始化一个数组。

通过 JDK 中的 HashMap 源码进行一些学习，首先看一下构造函数：

```java
// 构造一个带指定初始容量和加载因子的空 HashMap。
public HashMap(int initialCapacity, float loadFactor) {
    // 如果指定初始容量小于0，抛错
    if (initialCapacity < 0)
        throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
    // 如果初始容量大于系统默认最大容量，则初始容量为最大容量
    if (initialCapacity > MAXIMUM_CAPACITY)
        initialCapacity = MAXIMUM_CAPACITY;
    // 如果loadFactor小于0，或loadFactor是NaN，则抛错
    if (loadFactor <= 0 || Float.isNaN(loadFactor))
        throw new IllegalArgumentException("Illegal load factor: " + loadFactor);

    // 寻找一个2的k次幂capacity恰好大于initialCapacity
    int capacity = 1;
    while (capacity < initialCapacity)
        capacity <<= 1;

    // 设置加载因子
    this.loadFactor = loadFactor;
    // 设置阈值为capacity * loadFactor，实际上当HashMap当前size到达这个阈值时，HashMap就需要扩大一倍了。
    threshold = (int) (capacity * loadFactor);
    // 创建一个capacity长度的数组用于保存数据
    table = new Entry[capacity];
    // 开始初始化
    init();
}
```

从源码中可以看出，每次新建一个HashMap时，都会初始化一个table数组。table数组的元素为Entry节点。`table = new Entry[capacity];`。就是 Java 中数组的创建方式,也就是说在构造函数中，其创建了一个 Entry 的数组，其大小为 capacity（目前我们还不需要太了解该变量含义），那么 Entry 又是什么结构呢？看一下源码：

```java
// 内置class输入对象，也就是桶
static class Entry<K, V> implements Map.Entry<K, V> {
    final K key;
    V value;
    Entry<K, V> next;
    final int hash;

    // 构造函数
    Entry(int h, K k, V v, Entry<K, V> n) {
        value = v;
        next = n;
        key = k;
        hash = h;
    }

    // 返回key
    public final K getKey() {
        return key;
    }

    // 返回value
    public final V getValue() {
        return value;
    }

    // 设置value
    public final V setValue(V newValue) {
        V oldValue = value;
        value = newValue;
        return oldValue;
    }

    // 是否相同
    public final boolean equals(Object o) {
        // 如果o不是Map.Entry的实例，那么肯定不相同了
        if (!(o instanceof Map.Entry))
            return false;
        // 将o转成Map.Entry
        Map.Entry e = (Map.Entry) o;
        // 得到key和value对比是否相同，相同则为true
        Object k1 = getKey();
        Object k2 = e.getKey();
        if (k1 == k2 || (k1 != null && k1.equals(k2))) {
            Object v1 = getValue();
            Object v2 = e.getValue();
            if (v1 == v2 || (v1 != null && v1.equals(v2)))
                return true;
        }
        // 否则为false
        return false;
    }

    // hashCode
    public final int hashCode() {
        return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
    }

    // 返回String
    public final String toString() {
        return getKey() + "=" + getValue();
    }

    // 使用该方法证明该key已经在该map中
    void recordAccess(HashMap<K, V> m) {
    }

    // 该方法记录该key已经被移除了
    void recordRemoval(HashMap<K, V> m) {
    }
}
```

其中`Entry为HashMap`的内部类，它包含了键key、值value、下一个节点next，以及hash值，这是非常重要的，正是由于Entry才构成了table数组的项为链表。

**可以总结出：Entry 就是数组中的元素，每个 Entry 其实就是一个 key-value 对，它持有一个指向下一个元素的引用，这就构成了链表。**

## 核心方法解读

### 存储实现

HashMap中我们最长用的就是put(K, V)和get(K)。我们都知道，HashMap的K值是唯一的，那如何保证唯一性呢？我们首先想到的是用equals比较，没错，这样可以实现，但随着内部元素的增多，put和get的效率将越来越低，这里的时间复杂度是O(n)，假如有1000个元素，put时需要比较1000次。实际上，HashMap很少会用到equals方法，因为其内通过一个哈希表管理所有元素，哈希是通过hash单词音译过来的，也可以称为散列表，哈希算法可以快速的存取元素，当我们调用put存值时，HashMap首先会调用K的hashCode方法，获取哈希码，通过哈希码快速找到某个存放位置，这个位置可以被称之为bucketIndex，通过上面所述hashCode的协定可以知道，如果hashCode不同，equals一定为false，如果hashCode相同，equals不一定为true。所以理论上，hashCode可能存在冲突的情况，有个专业名词叫碰撞，当碰撞发生时，计算出的bucketIndex也是相同的，这时会取到bucketIndex位置已存储的元素，最终通过equals来比较，equals方法就是哈希码碰撞时才会执行的方法，所以前面说HashMap很少会用到equals。HashMap通过hashCode和equals最终判断出K是否已存在，如果已存在，则使用新V值替换旧V值，并返回旧V值，如果不存在 ，则存放新的键值对到bucketIndex位置。整个put过程的流程图如下：

![hashmap_put](./images/hashmap_put)

```java
// 在此映射中关联指定值与指定键。如果该映射以前包含了一个该键的映射关系，则旧值被替换
public V put(K key, V value) {
    // 当key为null，调用putForNullKey方法，保存null与table第一个位置中，这是HashMap允许为null的原因 
    if (key == null)
        return putForNullKey(value);
    // 使用hash函数预处理hashCode，计算key的hash值  
    int hash = hash(key.hashCode());//-------（1）
    // 计算key hash 值在 table 数组中的位置 
    int i = indexFor(hash, table.length);//------(2)
    // 从i出开始迭代 e,找到 key 保存的位置
    for (Entry<K, V> e = table[i]; e != null; e = e.next) {
        Object k;
        // 判断该条链上是否有hash值相同的(key相同) 
        // 若存在相同，则直接覆盖value，返回旧value 
        if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
            // 旧值 = 新值  
            V oldValue = e.value;
            // 将要存储的value存进去
            e.value = value;
            e.recordAccess(this);
            // 返回旧的value
            return oldValue;
        }
    }
    // 修改次数增加1
    modCount++;
    // 将key、value添加至i位置处
    addEntry(hash, key, value, i);
    return null;
}
```

通过源码可以清晰看到HashMap保存数据的过程为：首先判断key是否为null，若为null，则直接调用putForNullKey方法。若不为空则先计算key的hash值，然后根据hash值搜索在table数组中的索引位置，如果table数组在该位置处有元素，则通过比较是否存在相同的key，若存在则覆盖原来key的value，否则将该元素保存在链头（最先保存的元素放在链尾）。若table在该处没有元素，则直接保存。

这个过程看似比较简单，其实深有内幕, 有如下几点：

- 1、先看迭代处。此处迭代原因就是为了防止存在相同的key值，若发现两个hash值（key）相同时，HashMap的处理方式是用新value替换旧value，这里并没有处理key，这就解释了HashMap中没有两个相同的key。
- 2、在看（1）、（2）处。这里是HashMap的精华所在。首先是hash方法，该方法为一个纯粹的数学计算，就是计算h的hash值。

```java
final int hash(Object k) {
    int h = 0;
    if (useAltHashing) {
        if (k instanceof String) {
            return sun.misc.Hashing.stringHash32((String) k);
        }
        h = hashSeed;
    }
    //得到k的hashcode值
    h ^= k.hashCode();
    //进行计算
    h ^= (h >>> 20) ^ (h >>> 12);
    return h ^ (h >>> 7) ^ (h >>> 4);
}
```

可以看到在 HashMap 中要找到某个元素，需要根据 key 的 hash 值来求得对应数组中的位置。如何计算这个位置就是 hash 算法。前面说过 HashMap 的数据结构是数组和链表的结合，所以当然希望这个 HashMap 里面的元素位置尽量的分布均匀些，尽量使得每个位置上的元素数量只有一个，那么当用 hash 算法求得这个位置的时候，马上就可以知道对应位置的元素就是我们要的，而不用再去遍历链表，这样就大大优化了查询的效率。

对于任意给定的对象，只要它的 hashCode() 返回值相同，那么程序调用 hash(int h) 方法所计算得到的 hash 码值总是相同的。我们首先想到的就是把 hash 值对数组长度取模运算，这样一来，元素的分布相对来说是比较均匀的。但是，“模”运算的消耗还是比较大的，在 HashMap 中是这样做的：调用 indexFor(int h, int length) 方法来计算该对象应该保存在 table 数组的哪个索引处。indexFor(int h, int length) 方法的代码如下：

```java
/**
 * Returns index for hash code h.
 */
static int indexFor(int h, int length) {  
    return h & (length-1);
}
```

这个方法非常巧妙，它通过 h & (table.length -1) 来得到该对象的保存位，而 HashMap 底层数组的长度总是 2 的 n 次方，这是 HashMap 在速度上的优化。在 HashMap 构造器中有如下代码：

```java
// Find a power of 2 >= initialCapacity
int capacity = 1;
    while (capacity < initialCapacity)  
        capacity <<= 1;
```

这段代码保证初始化时 HashMap 的容量总是 2 的 n 次方，即底层数组的长度总是为 2 的 n 次方。

当 length 总是 2 的 n 次方时，h& (length-1)运算等价于对 length 取模，也就是 h%length，但是 & 比 % 具有更高的效率。这看上去很简单，其实比较有玄机的，我们举个例子来说明：

假设数组长度分别为 15 和 16，优化后的 hash 码分别为 8 和 9，那么 & 运算后的结果如下：

h & (table.length-1)|hash||table.length-1||
---|:--|:--|:--|---
8 & (15-1)：|0100|&|1110|=0100
9 & (15-1)：|0101|&|1110|=0100
8 & (16-1)：|0100|&|1111|=0100
9 & (16-1)：|0101|&|1111|=0101

从上面的例子中可以看出：当它们和 15-1（1110）“与”的时候，产生了相同的结果，也就是说它们会定位到数组中的同一个位置上去，这就产生了碰撞，8 和 9 会被放到数组中的同一个位置上形成链表，那么查询的时候就需要遍历这个链表，得到8或者9，这样就降低了查询的效率。同时，我们也可以发现，当数组长度为 15 的时候，hash 值会与 15-1（1110）进行“与”，那么最后一位永远是 0，而 0001，0011，0101，1001，1011，0111，1101 这几个位置永远都不能存放元素了，空间浪费相当大，更糟的是这种情况中，数组可以使用的位置比数组长度小了很多，这意味着进一步增加了碰撞的几率，减慢了查询的效率！而当数组长度为16时，即为2的n次方时，2n-1 得到的二进制数的每个位上的值都为 1，这使得在低位上&时，得到的和原 hash 的低位相同，加之 hash(int h)方法对 key 的 hashCode 的进一步优化，加入了高位计算，就使得只有相同的 hash 值的两个值才会被放到数组中的同一个位置上形成链表。

所以说，当数组长度为 2 的 n 次幂的时候，不同的 key 算得得 index 相同的几率较小，那么数据在数组上分布就比较均匀，也就是说碰撞的几率小，相对的，查询的时候就不用遍历某个位置上的链表，这样查询效率也就较高了。

根据上面 put 方法的源代码可以看出，当程序试图将一个key-value对放入HashMap中时，程序首先根据该 key 的 hashCode() 返回值决定该 Entry 的存储位置：如果两个 Entry 的 key 的 hashCode() 返回值相同，那它们的存储位置相同。如果这两个 Entry 的 key 通过 equals 比较返回 true，新添加 Entry 的 value 将覆盖集合中原有 Entry 的 value，但key不会覆盖。如果这两个 Entry 的 key 通过 equals 比较返回 false，新添加的 Entry 将与集合中原有 Entry 形成 Entry 链，而且新添加的 Entry 位于 Entry 链的头部——具体说明继续看 addEntry() 方法的说明。

### 读取实现

```java
// 返回指定键所映射的值；如果对于该键来说，此映射不包含任何映射关系，则返回 null
public V get(Object key) {
    // 若为null，调用getForNullKey方法返回相对应的value 
    if (key == null)
        return getForNullKey();
    // 根据该 key 的 hashCode 值计算它的 hash 码  
    int hash = hash(key.hashCode());
    // 取出 table 数组中指定索引处的值
    for (Entry<K, V> e = table[indexFor(hash, table.length)]; e != null; e = e.next) {
        Object k;
        // 如果hash值相等，并且key相等则证明这个桶里的东西是我们想要的
        if (e.hash == hash && ((k = e.key) == key || key.equals(k)))
            return e.value;
    }
    // 所有桶都找遍了，没找到想要的，所以返回null
    return null;
}
```

在这里能够根据key快速的取到value除了和HashMap的数据结构密不可分外，还和Entry有莫大的关系，在前面就提到过，HashMap在存储过程中并没有将key，value分开来存储，而是当做一个整体key-value来处理的，这个整体就是Entry对象。同时value也只相当于key的附属而已。在存储的过程中，系统根据key的hashcode来决定Entry在table数组中的存储位置，在取的过程中同样根据key的hashcode取出相对应的Entry对象。

## 总结

**简单地说，HashMap 在底层将 key-value 当成一个整体进行处理，这个整体就是一个 Entry 对象。HashMap 底层采用一个 Entry[] 数组来保存所有的 key-value 对，当需要存储一个 Entry 对象时，会根据 hash 算法来决定其在数组中的存储位置，在根据 equals 方法决定其在该数组位置上的链表中的存储位置；当需要取出一个Entry 时，也会根据 hash 算法找到其在数组中的存储位置，再根据 equals 方法从该位置上的链表中取出该Entry。**

### HashMap 的 resize（rehash）

当 HashMap 中的元素越来越多的时候，hash 冲突的几率也就越来越高，因为数组的长度是固定的。所以为了提高查询的效率，就要对 HashMap 的数组进行扩容，数组扩容这个操作也会出现在 ArrayList 中，这是一个常用的操作，而在 HashMap 数组扩容之后，最消耗性能的点就出现了：原数组中的数据必须重新计算其在新数组中的位置，并放进去，这就是 resize。

那么 HashMap 什么时候进行扩容呢？当 HashMap 中的元素个数超过数组大小 *loadFactor时，就会进行数组扩容，loadFactor的默认值为 0.75，这是一个折中的取值。也就是说，默认情况下，数组大小为 16，那么当 HashMap 中元素个数超过 16*0.75=12 的时候，就把数组的大小扩展为 2*16=32，即扩大一倍，然后重新计算每个元素在数组中的位置，而这是一个非常消耗性能的操作，所以如果我们已经预知 HashMap 中元素的个数，那么预设元素的个数能够有效的提高 HashMap 的性能。

### HashMap 的性能参数

HashMap 包含如下几个构造器：

- HashMap()：构建一个初始容量为 16，负载因子为 0.75 的 HashMap。
- HashMap(int initialCapacity)：构建一个初始容量为 initialCapacity，负载因子为 0.75 的 HashMap。
- HashMap(int initialCapacity, float loadFactor)：以指定初始容量、指定的负载因子创建一个 HashMap。

HashMap 的基础构造器 HashMap(int initialCapacity, float loadFactor) 带有两个参数，它们是初始容量 initialCapacity 和负载因子 loadFactor。

负载因子 loadFactor 衡量的是一个散列表的空间的使用程度，负载因子越大表示散列表的装填程度越高，反之愈小。对于使用链表法的散列表来说，查找一个元素的平均时间是 O(1+a)，因此如果负载因子越大，对空间的利用更充分，然而后果是查找效率的降低；如果负载因子太小，那么散列表的数据将过于稀疏，对空间造成严重浪费。

HashMap 的实现中，通过 threshold 字段来判断 HashMap 的最大容量：

```java
threshold = (int)(capacity * loadFactor);
```

结合负载因子的定义公式可知，threshold 就是在此 loadFactor 和 capacity 对应下允许的最大元素数目，超过这个数目就重新 resize，以降低实际的负载因子。默认的的负载因子 0.75 是对空间和时间效率的一个平衡选择。当容量超出此最大容量时， resize 后的 HashMap 容量是容量的两倍。

### Fail-Fast 机制

#### 原理

java.util.HashMap 不是线程安全的，因此如果在使用迭代器的过程中有其他线程修改了 map，那么将抛出 ConcurrentModificationException，这就是所谓 fail-fast 策略。

fail-fast 机制是 java 集合(Collection)中的一种错误机制。 当多个线程对同一个集合的内容进行操作时，就可能会产生 fail-fast 事件。

例如：当某一个线程 A 通过 iterator去遍历某集合的过程中，若该集合的内容被其他线程所改变了；那么线程 A 访问集合时，就会抛出 ConcurrentModificationException 异常，产生 fail-fast 事件。

这一策略在源码中的实现是通过 modCount 域，modCount 顾名思义就是修改次数，对 HashMap 内容（当然不仅仅是 HashMap 才会有，其他例如 ArrayList 也会）的修改都将增加这个值（大家可以再回头看一下其源码，在很多操作中都有 modCount++ 这句），那么在迭代器初始化过程中会将这个值赋给迭代器的 expectedModCount。

```java
HashIterator() {
    expectedModCount = modCount;
    if (size > 0) { // advance to first entry
        Entry[] t = table;
        while (index < t.length && (next = t[index++]) == null)  
            ;
    }
}
```

在迭代过程中，判断 modCount 跟 expectedModCount 是否相等，如果不相等就表示已经有其他线程修改了 Map.

注意到 modCount 声明为 volatile，保证线程之间修改的可见性。

```java
final Entry<K,V> nextEntry() {
    if (modCount != expectedModCount)
        throw new ConcurrentModificationException();
```

在 HashMap 的 API 中指出：

由所有 HashMap 类的“collection 视图方法”所返回的迭代器都是快速失败的：在迭代器创建之后，如果从结构上对映射进行修改，除非通过迭代器本身的 remove 方法，其他任何时间任何方式的修改，迭代器都将抛出 ConcurrentModificationException。因此，面对并发的修改，迭代器很快就会完全失败，而不冒在将来不确定的时间发生任意不确定行为的风险。

注意，迭代器的快速失败行为不能得到保证，一般来说，存在非同步的并发修改时，不可能作出任何坚决的保证。快速失败迭代器尽最大努力抛出 ConcurrentModificationException。因此，编写依赖于此异常的程序的做法是错误的，正确做法是：迭代器的快速失败行为应该仅用于检测程序错误。

#### 解决方案

在上文中也提到，fail-fast 机制，是一种错误检测机制。它只能被用来检测错误，因为 JDK 并不保证 fail-fast 机制一定会发生。若在多线程环境下使用 fail-fast 机制的集合，建议使用“java.util.concurrent 包下的类”去取代“java.util 包下的类”。

### HashMap 的两种遍历方式

#### 第一种(效率高)

**Iterator iter = map.entrySet().iterator();**

```java
Map map = new HashMap();
Iterator iter = map.entrySet().iterator();
while (iter.hasNext()) {
　　Map.Entry entry = (Map.Entry) iter.next();
　　Object key = entry.getKey();
　　Object val = entry.getValue();
}
```

#### 第二种(效率低)

**Iterator iter = map.keySet().iterator();**

```java
Map map = new HashMap();
Iterator iter = map.keySet().iterator();
while (iter.hasNext()) {
　　Object key = iter.next();
　　Object val = map.get(key);
}
```
