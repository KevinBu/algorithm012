package cn.com.kevin.algorithm.string;

import java.util.HashMap;

/**
 * LeetCode 387 字符串中第一个唯一的字符
 *
 * <p>给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * <p>
 *
 * <p>示例：
 *
 * <p>s = "leetcode"
 *
 * <p>返回 0
 *
 * <p>s = "loveleetcode"
 *
 * <p>返回 2
 *
 * <p>提示：你可以假定该字符串只包含小写字母。
 */
public class FirstUniqChar {
  public static int firstUniqChar(String s) {
    // 定义 map 用于统计字符出现的次数
    HashMap<Character, Integer> count = new HashMap<Character, Integer>();
    int n = s.length(); // 放在外面获得长度，可以提高效率
    // 遍历字符串字符，统计字符出现的个数
    for (int i = 0; i < n; i++) {
      char c = s.charAt(i);
      count.put(c, count.getOrDefault(c, 0) + 1);
    }
    // 返回第一个仅出现一个字符的索引
    // 此处对字符串进行循环，可以保证顺序，否则hashmap中是无序的。
    for (int i = 0; i < n; i++) {
      if (count.get(s.charAt(i)) == 1) {
        return i;
      }
    }
    return -1;
  }

  public static int firstUniqChar1(String s) {
    int i;
    int[] count = new int[26];
    for (i = 0; i < s.length(); i++) {
      count[s.charAt(i) - 'a']++;
    }
    for (i = 0; i < s.length(); i++) {
      if (count[s.charAt(i) - 'a'] == 1) {
        return i;
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    System.out.println(firstUniqChar("leetcode"));
    System.out.println(firstUniqChar("loveleetcode"));
    System.out.println(firstUniqChar1("leetcode"));
    System.out.println(firstUniqChar1("loveleetcode"));
  }
}
