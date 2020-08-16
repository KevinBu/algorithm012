package cn.com.kevin.algorithm.dp;

/**
 * LeeCode 91 解码方法
 *
 * <p>一条包含字母 A-Z 的消息通过以下方式进行了编码：
 *
 * <p>'A' -> 1 'B' -> 2 ... 'Z' -> 26 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *
 * <p>A、 B、 C、 D、 E、 F、 G、 H、 I、 J、 K、 L、 M、 N、 O、 P、 Q、 R、 S、 T、 U、 V、 W、 X、 Y、 Z
 *
 * <p>1、 2、 3、 4、 5、 6、 7、 8、 9、 10、11、12、13、14、15、16、17、18、19、20、21、22、23、24、25、26
 *
 * <p>示例 1:
 *
 * <p>输入: "12"
 *
 * <p>输出: 2
 *
 * <p>解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 *
 * <p>示例 2:
 *
 * <p>输入: "226"
 *
 * <p>输出: 3
 *
 * <p>解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 */
public class NumDecoding {

  public static int numDecodings(String s) {
    // 条件判断
    if (s == null || s.length() == 0) {
      return 0;
    }
    // 此处不考虑 “0X“ 这种情况，只要首字符为 0 则就返回 0
    if (s.charAt(0) == '0') {
      return 0;
    }
    int len = s.length();
    if (len == 1) {
      // 如果长度为 1， 则只有一种情况
      return 1;
    }

    // 根据动态规划，当前字符能编码的情况 = 前一个字符编码的情况 + 当前字符编码的情况
    // 定义当前字符的编码情况统计数，和当前字符
    int pre = 1, curr = 1; // 排除了不可能的情况后，最少有 1 种情况，因此开始值都为 1
    // 开始遍历字符串，当前字符要从 1 开始取，这样可以取前一个字符进行组合判断
    for (int i = 1; i < len; i++) {
      int tmp = curr;
      char preChar = s.charAt(i - 1); // 获得前一个字符
      char currChar = s.charAt(i); // 获得当前字符
      if (currChar == '0') {
        if (preChar == '1' || preChar == '2') {
          // 只能是 10 或 20，否则就大于字母的取值范围
          curr = pre;
        } else {
          // 大于字母的取值范围，就返回 0
          return 0;
        }
      } else if (preChar == '1' || preChar == '2' && currChar >= '1' && currChar <= '6') {
        curr = curr + pre;
      }
      pre = tmp;
    }

    return curr;
  }

  public static void main(String[] args) {
    String s = "1224"; // 1 2 2 4,  12 2 4, 1 22 4, 1 2 24, 12 24
    // String s = "3284"; // 3 2 8 4
    // String s = "3224"; // 3 2 24, 3 2 2 4, 3 22 4
    System.out.println(numDecodings(s));
  }
}
