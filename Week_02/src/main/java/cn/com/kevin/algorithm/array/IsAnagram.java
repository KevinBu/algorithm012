package cn.com.kevin.algorithm.array;

import java.util.Arrays;

/**
 * LeetCode: 242 有效的字母异位词
 * 
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。 示例 1: 输入: s = "anagram", t =
 * "nagaram" 输出: true
 * 
 * 示例 2: 输入: s = "rat", t = "car" 输出: false
 * 
 * 说明: 你可以假设字符串只包含小写字母。
 * 
 * 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 */
public class IsAnagram {

    /**
     * 通过 Java 数组的特性
     * 假设字符串只包含小写字母,因此没有加入字符大小写转换的代码
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram1(String s, String t) {
        // 如果两字符串的长度不等，则可以认为肯定不是有效的异位词
        if(s.length() != t.length()){
            return false;
        }
        char[] sChar = convertAndSort(s);
        char[] tChar = convertAndSort(t);
        
        return Arrays.equals(sChar, tChar);
    }

    /**
     * 思路
     * 标签：哈希映射
     * 1. 首先判断两个字符串长度是否相等，不相等则直接返回 false
     * 2. 若相等，则初始化 26 个字母哈希表，遍历字符串 s 和 t
     * 3. s 负责在对应位置增加，t 负责在对应位置减少
     * 4. 如果哈希表的值都为 0，则二者是字母异位词
     * 
     * 技巧：
     *      利用字符的charAt-'a'获得 [0 - 26] 的数组的索引
     *      当 s 中出现某个字符后，所在索引的位置上的数值 + 1（初始值为0）
     *      当 t 中出现某个字符后，所在索引的位置上的数值 - 1 (初始值为0)
     *      如果两个词是有效的异位词，则counter数组各个位置上的值应该全是0
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram2(String s, String t) {
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    private static char[] convertAndSort(String s){
        // 将字符串转换为字符数组
        char[] sChar = s.toCharArray();
        // 对字符数组进行排序
        Arrays.sort(sChar);
        return sChar;
    }
    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        System.out.println(isAnagram2(s, t));
    }
}