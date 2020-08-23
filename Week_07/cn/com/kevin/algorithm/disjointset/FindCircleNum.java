package cn.com.kevin.algorithm.disjointset;

/**
 * LeetCode 547 朋友圈
 *
 * <p>班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C
 * 的朋友。所谓的朋友圈，是指所有朋友的集合。
 *
 * <p>给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。
 * 你必须输出所有学生中的已知的朋友圈总数。
 *
 * <p>
 *
 * <p>示例 1：
 *
 * <p>输入： [[1,1,0], [1,1,0], [0,0,1]]
 *
 * <p>输出：2
 *
 * <p>解释：已知学生 0 和学生 1 互为朋友，他们在一个朋友圈。 第2个学生自己在一个朋友圈。所以返回 2 。
 *
 * <p>示例2：
 *
 * <p>输入： [[1,1,0], [1,1,1], [0,1,1]]
 *
 * <p>输出：1
 *
 * <p>解释：已知学生 0 和学生 1 互为朋友，学生 1 和学生 2 互为朋友，所以学生 0 和学生 2 也是朋友，所以他们三个在一个朋友圈，返回 1 。
 *
 * <p>提示：
 *
 * <p>1 <= N <= 200 M[i][i] == 1 M[i][j] == M[j][i] 通过次数59,282提交次数102,498
 */
public class FindCircleNum {
  public static int findCircleNum(int[][] M) {
    int n = M.length;
    UnionFind uf = new UnionFind(n);
    for (int i = 0; i < n - 1; i++) {
      for (int j = i + 1; j < n; j++) {
        if (M[i][j] == 1) {
          uf.union(i, j);
        }
      }
    }
    return uf.count();
  }

  public static void main(String[] args) {
    int[][] M = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
    System.out.println(findCircleNum(M));
  }
}