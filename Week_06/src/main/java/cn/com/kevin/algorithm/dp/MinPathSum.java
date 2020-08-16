package cn.com.kevin.algorithm.dp;

/**
 * LeeCode 64
 *
 * <p>最小路径和
 *
 * <p>给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * <p>说明：每次只能向下或者向右移动一步。
 *
 * <p>示例:
 *
 * <p>输入: [ [1,3,1], [1,5,1], [4,2,1] ] 输出: 7 解释: 因为路径 1→3→1→1→1 的总和最小。
 */
public class MinPathSum {
  /**
   * 最小路径和
   *
   * @param grid
   * @return
   */
  public static int minPathSum(int[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }
    int rowLen = grid.length; // 行数
    int colLen = grid[0].length; // 列数
    for (int i = 0; i < rowLen; i++) {
      for (int j = 0; j < colLen; j++) {
        if (i == 0 && j == 0) {
          // 当前为起点
          continue;
        } else if (i == 0) {
          // 在顶层遍历，只有从左侧到当前单元格
          grid[i][j] = grid[i][j - 1] + grid[i][j];
        } else if (j == 0) {
          // 在第一列遍历，只能从上面到当前单元格
          grid[i][j] = grid[i - 1][j] + grid[i][j];
        } else {
          // 可能从左侧或上面到当前单元格，因此需要取小的值
          grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
        }
      }
    }
    return grid[rowLen - 1][colLen - 1];
  }

  public static void main(String[] args) {
    int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
    System.out.println(minPathSum(grid));
  }
}
