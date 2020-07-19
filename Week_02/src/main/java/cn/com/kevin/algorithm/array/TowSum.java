package cn.com.kevin.algorithm.array;

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
     * 使用 while 循环
     * 但是本质上还是通过 i 和 j 两个指针实现了嵌套循环，又因为里面有不少 if 条件判断，因此效率很差
     * 
     * 时间复杂度：n/2 * O(n)
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] nums, int target) {
        int index1 = -1;
        int index2 = -1;
        int i = 0;
        int j = 1;
        int len = nums.length;
        int loopIndex = 0;
        while (true){
            ++loopIndex;
            if(i == len || j == len){
                break;
            }
            if(nums[i] + nums[j] == target){
                index1 = i;
                index2 = j;
                break;
            }
            if(j == len - 1){
                if(i == len - 1){
                    break;
                }
                i++;
                j = i + 1;
            }else {
                j++;
            }
        }
        System.out.println("循环的次数：" + loopIndex);
        if(index1 == -1 || index2 == -1){
            return new int[0];
        }
        int [] res = {index1, index2};
        return res;
    }

    /**
     * 极端情况下的循环次数和1一样，但是由于没有很多if判断，因此耗时反而比1少
     * @param nums
     * @param target
     * @return
     */
    private static int[] twoSum2(int[] nums, int target){
        int index1 = -1;
        int index2 = -1;
        int dVal = -1;
        int loopIndex = 0;
        // 取到一个比target小的数并记录索引，同时
        for(int i = 0, len = nums.length; i < len; i++){
            
            int num = nums[i];
            index1 = i;
            dVal = target - num;
            boolean isFind = false;
            for(int j = i + 1, sublen = nums.length; j < sublen; j++){
                ++loopIndex;
                int subNum = nums[j];
                if(dVal == subNum) {
                    index2 = j;
                    isFind = true;
                    break;
                }
            }
            if(isFind){
                break;
            }
        }
        System.out.println("循环的次数：" + loopIndex);
        if(index1 == -1 || index2 == -1){
            return new int[0];
        }
        int [] res = {index1, index2};
        return res;
    }

    /**
     * 时间复杂度为 O(n)
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum3(int[] nums, int target) {
        int len = nums.length;
        // 因为题目要求返回符合条件的下标，因此key是数组中的数字，而value则为当前数字在数字中的索引
        Map<Integer, Integer> numMap = new HashMap<>();
        for(int i = 0; i < len; i++){
            // 判断当前数字-target在numMap中是否存在，如果存在则说明当前的数字符合要求
            int key = target - nums[i];
            if(numMap.containsKey(key)){
                return new int[]{numMap.get(key), i};
            }else{
                numMap.put(nums[i], i);
            }
        }
        
        return new int[0];
    }

    public static void main(String[] args) {
        int []nums = {1,1,1,1,1,1,1,2,7};
        int target = 9;
        System.out.println(Arrays.toString(twoSum3(nums, target)));
    }
    
}