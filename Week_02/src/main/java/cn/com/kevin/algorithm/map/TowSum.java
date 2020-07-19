package cn.com.kevin.algorithm.map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode:1
 * 两数之和
 * 
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * 示例:
 *      给定 nums = [2, 7, 11, 15], target = 9
 *      因为 nums[0] + nums[1] = 2 + 7 = 9
 *      所以返回 [0, 1]
 */
public class TowSum {

    /**
     * 使用两次哈希表
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] nums, int target) {
        int len = nums.length;
        // 将数组转换为map
        Map<Integer, Integer> numsMap = new HashMap<>();
        for(int i = 0; i < len; i++){
            int num = nums[i];
            numsMap.put(num, i);
        }
        // 寻找符合条件的数字及下标
        for(int i = 0; i < len; i++){
            int num = nums[i];
            int key = target - num;
            Integer index = numsMap.get(key);
            if(numsMap.containsKey(key) && index.intValue() != i){
                return new int[]{numsMap.get(key), i};
            }
        }

        return new int[0];
    }

    /**
     * 一遍哈希表
     * 时间复杂度为 O(n)
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        int len = nums.length;
        // 因为题目要求返回符合条件的下标，因此key是数组中的数字，而value则为当前数字在数字中的索引
        Map<Integer, Integer> numMap = new HashMap<>();
        for(int i = 0; i < len; i++){
            // 判断当前数字-target在numMap中是否存在，如果存在则说明当前的数字符合要求
            int key = target - nums[i];
            // 排除自身相加
            if(numMap.containsKey(key) ){
                return new int[]{numMap.get(key), i};
            }else{
                numMap.put(nums[i], i);
            }
        }
        
        return new int[0];
    }

    public static void main(String[] args) {
        int []nums = {3,2,4};
        int target = 6;
        System.out.println(Arrays.toString(twoSum1(nums, target)));
    }
    
}