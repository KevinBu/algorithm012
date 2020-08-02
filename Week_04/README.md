学习笔记

[TOC]

# 本周学习的知识点有

## 深度优先搜索与广度优先搜索

### 深度优先（Depth-First-Search）

#### 概述

深度优先搜索算法（英语：Depth-First-Search，DFS）是一种用于遍历或搜索树或图的算法。沿着树的深度遍历树的节点，尽可能深的搜索树的分支。当节点v的所在边都己被探寻过，搜索将回溯到发现节点v的那条边的起始节点。这一过程一直进行到已发现从源节点可达的所有节点为止。如果还存在未被发现的节点，则选择其中一个作为源节点并重复以上过程，整个进程反复进行直到所有节点都被访问为止。属于盲目搜索。

深度优先搜索是图论中的经典算法，利用深度优先搜索算法可以产生目标图的相应拓扑排序表，利用拓扑排序表可以方便的解决很多相关的图论问题，如最大路径问题等等。

![avatar](https://upload-images.jianshu.io/upload_images/3661808-441e2e1044af36d2.png?imageMogr2/auto-orient/strip|imageView2/2/w/285/format/webp)

遍历顺序为 1->2->4->8->5->3->6->7


#### 代码模板

```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> allResults = new ArrayList<>();
    if(root==null){
        return allResults;
    }
    travel(root,0,allResults);
    return allResults;
}


private void travel(TreeNode root,int level,List<List<Integer>> results){
    if(results.size()==level){
        results.add(new ArrayList<>());
    }
    results.get(level).add(root.val);
    if(root.left!=null){
        travel(root.left,level+1,results);
    }
    if(root.right!=null){
        travel(root.right,level+1,results);
    }
}
```

### 广度优先（Breadth-First-Search）

#### 概述

广度优先搜索算法（Breadth-First-Search），是一种图形搜索算法，简单的说，BFS是从根节点开始，沿着树(图)的宽度遍历树(图)的节点。如果所有节点均被访问，则算法中止。BFS同样属于盲目搜索。一般用队列数据结构来辅助实现BFS算法。

![avatar](https://upload-images.jianshu.io/upload_images/3661808-7f5c9490d17f2177.png?imageMogr2/auto-orient/strip|imageView2/2/w/285/format/webp)

遍历顺序为：1->2->3->4->5->6->7->8

#### 代码模板

```java
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> allResults = new ArrayList<>();
    if (root == null) {
        return allResults;
    }
    Queue<TreeNode> nodes = new LinkedList<>();
    nodes.add(root);
    while (!nodes.isEmpty()) {
        int size = nodes.size();
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TreeNode node = nodes.poll();
            results.add(node.val);
            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
        }
        allResults.add(results);
    }
    return allResults;
}
```

## 贪心算法

### 一、基本概念

所谓贪心算法是指，在对问题求解时，总是做出在当前看来是最好的选择。也就是说，不从整体最优上加以考虑，它所做出的仅仅是在某种意义上的局部最优解。

贪心算法没有固定的算法框架，算法设计的关键是贪心策略的选择。

必须注意的是，贪心算法不是对所有问题都能得到整体最优解，选择的贪心策略必须具备无后效性（即某个状态以后的过程不会影响以前的状态，只与当前状态有关。）

所以，对所采用的贪心策略一定要仔细分析其是否满足无后效性。

### 二、贪心算法的基本思路

- 建立数学模型来描述问题
- 把求解的问题分成若干个子问题
- 对每个子问题求解，得到子问题的局部最优解
- 把子问题的解局部最优解合成原来问题的一个解

### 三、该算法存在的问题

- 不能保证求得的最后解是最佳的
- 不能用来求最大值或最小值的问题
- 只能求满足某些约束条件的可行解的范围

### 四、贪心算法适用的问题

贪心策略适用的前提是：**局部最优策略能导致产生全局最优解。**

实际上，贪心算法适用的情况很少。一般对一个问题分析是否适用于贪心算法，可以先选择该问题下的几个实际数据进行分析，就可以做出判断。

### 五、贪心选择性质

所谓贪心选择性质是指所求问题的整体最优解可以通过一系列局部最优的选择，换句话说，当考虑做何种选择的时候，我们只考虑对当前问题最佳的选择而不考虑子问题的结果。这是贪心算法可行的第一个基本要素。贪心算法以迭代的方式作出相继的贪心选择，每作一次贪心选择就将所求问题简化为规模更小的子问题。

**对于一个具体问题，要确定它是否具有贪心选择性质，必须证明每一步所作的贪心选择最终导致问题的整体最优解。**

当一个问题的最优解包含其子问题的最优解时，称此问题具有最优子结构性质。问题的最优子结构性质是该问题可用贪心算法求解的关键特征。

### 六、贪心算法的实现框架

从问题的某一初始解出发：

```java
while (朝给定总目标前进一步)
{
    利用可行的决策，求出可行解的一个解元素。
}  
由所有解元素组合成问题的一个可行解；
```

## 二分查找的实现

### 概述

二分查找也称折半查找（Binary Search），它是一种效率较高的查找方法。但是，折半查找要求线性表必须采用顺序存储结构，而且表中元素按关键字有序排列。

算法要求：

- 1.必须采用顺序存储结构。
- 2.必须按关键字大小有序排列。
  
### 代码模板

```java
public int binarySearch(int[] array, int target) {
    int left = 0, right = array.length - 1, mid;
    while (left <= right) {
        mid = (right - left) / 2 + left;

        if (array[mid] == target) {
            return mid;
        } else if (array[mid] > target) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }

    return -1;
}
```

### 课堂作业

使用二分查找，寻找一个半有序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方

解题思路：可以将问题转行为通过二分查找数组中最小值的索引，代码如下：

```java
public class FindHalfOrderArray {

    /**
     * 使用二分查找，寻找一个半有序数组 [6, 7, 0, 1, 2, 4, 5] 中间无序的地方
     * 本题可以转换为查找数组中最小值的坐标来实现
     * 
     * @param array
     * @return 半序数组中无序值的索引
     */
    public static int findHalfOrderArray(int [] array){
        int left = 0, right = array.length - 1, mid;
        while (left < right) {
            mid = (right - left) / 2 + left;
            if (array[mid] > array[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int [] arr = {6, 7, 0, 1, 2, 4, 5};
        //int [] arr = {1, 2, 4, 5, 6, 7, 0};
        System.out.println(findHalfOrderArray(arr));
    }
}

```
