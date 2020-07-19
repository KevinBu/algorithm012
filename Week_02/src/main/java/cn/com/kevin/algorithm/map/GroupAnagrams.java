package cn.com.kevin.algorithm.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode: 49 字母异位词分组
 * 
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。 
 * 示例: 
 *      输入: ["eat", "tea", "tan", "ate", "nat", "bat"] 
 *      输出: 
 *      [ 
 *          ["ate","eat","tea"], 
 *          ["nat","tan"], 
 *          ["bat"] 
 *      ]
 * 
 * 说明： 
 *      所有输入均为小写字母。 不考虑答案输出的顺序。
 */
public class GroupAnagrams {

    /**
     * 解题思路：
     *      利用HashMap对字符串数组进行分组
     *          key：是对子字符串进行排序后的字符串
     *          value：排序后相同字符串的List集合
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs){
        if (strs.length == 0){
            return new ArrayList<List<String>>();
        } 
        Map<String, List<String>> wordMap = new HashMap<>();
        for(int i = 0, len = strs.length; i < len; i++){
            String str = strs[i];
            char[] sChar = str.toCharArray();
            Arrays.sort(sChar);
            String s = String.valueOf(sChar);
            if(wordMap.containsKey(s)){
                List<String> words = wordMap.get(s);
                words.add(str);
            }else{
                List<String> words = new ArrayList<>();
                words.add(str);
                wordMap.put(s, words);
            }
        }
        return new ArrayList<List<String>>(wordMap.values());
    }
    public static void main(String[] args) {
        String [] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
    }
    
}