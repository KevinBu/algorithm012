学习笔记

本周学习的内容是动态规划，其要点如下：

# 要点

动态规划 和 递归或者分治 没有根本上的区别（关键看有无最优的子结构）

共性：找到重复子问题

差异性：最优子结构、中途可以淘汰次优解

# 状态转移方程（DP 方程）

```java
opt[i , j] = opt[i + 1, j] + opt[i, j + 1]
```

## 完整逻辑

```java
if a[i, j] = ‘空地’:

    opt[i , j] = opt[i + 1, j] + opt[i, j + 1]

else:

    opt[i , j] = 0
```

## 动态规划关键点

1. 最优子结构 opt[n] = best_of(opt[n-1], opt[n-2], …)

2. 储存中间状态：opt[i]

3. 递推公式（美其名曰：状态转移方程或者 DP 方程）

```java
Fib: opt[i] = opt[n-1] + opt[n-2]
```

二维路径：opt[i,j] = opt[i+1][j] + opt[i][j+1] (且判断a[i,j]是否空地)

## 动态规划小结

1. 打破自己的思维惯性，形成机器思维

2. 理解复杂逻辑的关键

3. 也是职业进阶的要点要领

## 动态规划套路详解

https://juejin.im/post/6844903916908331015