package cn.com.kevin.algorithm.string;

/**
 * LeetCode 541 反转字符串二
 *
 * <p>给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。
 *
 * <p>如果剩余字符少于 k 个，则将剩余字符全部反转。 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 *
 * <p>示例:
 *
 * <p>输入: s = "abcdefg", k = 2 输出: "bacdfeg"
 *
 * <p>提示：
 *
 * <p>该字符串只包含小写英文字母。 给定字符串的长度和 k 在 [1, 10000] 范围内。
 */
public class ReverseStr {
  public static String reverseStr(String s, int k) {
    if (k <= 0) {
      return s;
    }
    char[] arr = s.toCharArray();
    int n = arr.length;
    int i = 0;
    while (i < n) {
      int j = Math.min(i + k - 1, n - 1);
      swap(arr, i, j);
      i += 2 * k;
    }
    return String.valueOf(arr);
  }

  private static void swap(char[] arr, int l, int r) {
    while (l < r) {
      char temp = arr[l];
      arr[l++] = arr[r];
      arr[r--] = temp;
    }
  }

  public static void main(String[] args) {
    System.out.println(reverseStr("abcdefg", 10));
  }
}
