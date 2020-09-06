---
typora-copy-images-to: ./images
---

学习笔记

[TOC]

# 高级动态规划

## 递归、分治、回溯、动态规划复习

### 递归 - 函数自己调用自己

#### 代码模板

```java
public void recur(int level, int param) {
  
	// terminator 
	if (level > MAX_LEVEL) {
		// process result
		return; 
  }

	// process current logic 
  process(level, param);

	// drill down 
  recur(level: level + 1, newParam);
	// restore current status
}
```

### 分而治之

#### 代码模板

```python
def divide_conquer(problem, param1, param2, ...):

  # recursion terminator 
  if problem is None:
  	print_result 
    return

  # prepare data 
  data = prepare_data(problem) 
  subproblems = split_problem(problem, data)

  # conquer subproblems
  subresult1 = self.divide_conquer(subproblems[0], p1, ...)
  subresult2 = self.divide_conquer(subproblems[1], p1, ...)
  subresult3 = self.divide_conquer(subproblems[2], p1, ...)
  …
  
  # process and generate the final result 
  result = process_result(subresult1, subresult2, subresult3, …)

  # revert the current level states
```

本质：寻找重复性 —> 计算机指令集

#### 递归状态树

![image-20200906150345794](images/image-20200906150345794.png)

#### Fib(6) 状态树、重复子状态

![image-20200906150814362](images/image-20200906150814362.png)

### 动态规划 Dynamic Programming

1. “Simplifying a complicated problem by breaking it down into simpler sub-problems” (in a recursive manner)


2. Divide & Conquer + Optimal substructure 分治 + 最优子结构


3. 顺推形式： 动态递推

#### DP 顺推模板

```python
function DP():

  dp = [][] # ⼆维情况

  for i = 0 .. M { 
    for j = 0 .. N {
  		dp[i][j] = _Function(dp[i’][j’]…)
  	}
  } 
  
  return dp[M][N];
```

#### 关键点

动态规划 和 递归或者分治 没有根本上的区别（关键看有无最优的子结构） 

拥有共性：找到重复子问题

**差异性：最优子结构、中途可以淘汰次优解**

### 常见的 DP 题目和状态方程

#### 爬楼梯

![image-20200906151357106](images/image-20200906151357106.png)

##### 递归公式：

**f(n) = f(n - 1) + f(n - 2) , f(1) = 1, f(0) = 0**

##### 算法演进

![image-20200906151730188](images/image-20200906151730188.png)

![image-20200906151800609](images/image-20200906151800609.png)

![image-20200906151830482](images/image-20200906151830482.png)

![image-20200906151943467](images/image-20200906151943467.png)

#### 不同路径

![不同路径](images/不同路径.png)

##### 递归公式：

 **f(x, y) = f(x-1, y) + f(x, y-1)**

##### 代码演进

![image-20200906152413344](images/image-20200906152413344.png)

![image-20200906152450879](images/image-20200906152450879.png)

![image-20200906152528106](images/image-20200906152528106.png)

#### 打家劫舍

dp[i]状态的定义： max $ of robbing A[0 -> i]

dp[i] = max(dp[i - 2] + nums[i], dp[i - 1])

dp[i][0]状态定义：max $ of robbing A[0 -> i] 且没偷 nums[i] dp[i][1]状态定义：max $ of robbing A[0 -> i] 且偷了 nums[i] 

dp[i][0] = max(dp[i - 1][0], dp[i - 1][1]);

dp[i][1] = dp[i - 1][0] + nums[i];

#### 最小路径和

dp[i][j]状态的定义： minPath(A[1 -> i][1 -> j]) 

dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + A[i][j]

#### 股票买卖

![image-20200906152946382](images/image-20200906152946382.png)

https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/yi-ge-fang-fatuan-mie-6-dao-gu-piao-wen-ti-by-l-3/

dp[i][k][0 or 1] (0 <= i <= n-1, 1 <= k <= K)

• i 为天数

• k 为最多交易次数

• [0,1] 为是否持有股票

总状态数： n * K * 2 种状态

```python
for 0 <= i < n:

  for 1 <= k <= K:

    for s in {0, 1}:

    		dp[i][k][s] = max(buy, sell, rest)
```

https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/yi-ge-fang-fatuan-mie-6-dao-gu-piao-wen-ti-by-l-3/

dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])

max(选择 rest, 选择 sell)

解释：今天我没有持有股票，有两种可能：


- 我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有；


- 我昨天持有股票，但是今天我 sell 了，所以我今天没有持有股票了。

dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])

max(选择 rest, 选择 buy)

解释：今天我持有着股票，有两种可能：


- 我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；


- 我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。

初始状态：

dp[-1][k][0] = dp[i][0][0] = 0

dp[-1][k][1] = dp[i][0][1] = -infinity

状态转移方程：

dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])

dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])

https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/yi-ge-fang-fa-tuan-mie-6-dao-gu-piao-wen-ti-by-l-3/

## 高阶的 DP 问题

### 复杂度来源

1. 状态拥有更多维度（二维、三维、或者更多、甚至需要压缩）


2. 状态方程更加复杂

**本质：内功、逻辑思维、数学**

# 字符串

## 字符串基础知识

### 字符串

![image-20200906154501752](images/image-20200906154501752.png)

### 遍历字符串

![image-20200906154952592](images/image-20200906154952592.png)

