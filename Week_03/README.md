# 学习笔记

## 总结

本周学习已经掉队了，目前卡在分治和二叉树递归代码实现上，虽然老师强调不要人肉递归，但是总是不自觉地在头脑中执行，普通的递归目前已经能够很好地掌握并应有到解题过程中。然而当递归遇到二叉树和分治后就感觉很难理解其运行的过程了，因此在写代码使就难以灵活运用。希望能通过多做题尽快能够顿悟吧。

## 本周知识点

### 递归实现

#### 代码模板

```java
// Java
public void recur(int level, int param) {
  // terminator
  if (level > MAX_LEVEL) {
    // process result
    return;
  }
  // process current logic
  process(level, param);
  // drill down
  recur( level: level + 1, newParam);
  // restore current status
}

```

### 分治回溯的实现

#### 代码模板

```javascript
//Javascript
const divide_conquer = (problem, params) => {

  // recursion terminator

  if (problem == null) {

    process_result

    return

  } 

  // process current problem

  subproblems = split_problem(problem, data)

  subresult1 = divide_conquer(subproblem[0], p1)

  subresult2 = divide_conquer(subproblem[1], p1)

  subresult3 = divide_conquer(subproblem[2], p1)

  ...

  // merge

  result = process_result(subresult1, subresult2, subresult3)

  // revert the current level status

}
```
