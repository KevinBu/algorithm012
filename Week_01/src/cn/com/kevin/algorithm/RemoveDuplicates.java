package cn.com.kevin.algorithm;

/**
 * LeetCode:26 删除排序数组中的重复项
 *
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1:
 *
 *  给定数组 nums = [1,1,2],
 *
 *  函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 *
 *  你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 2:
 *
 *  给定 nums = [0,0,1,1,1,2,2,3,3,4],
 *
 *  函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 *
 *  你不需要考虑数组中超出新长度后面的元素。
 *  
 *
 * 说明:
 *
 *  为什么返回数值是整数，但输出的答案是数组呢?
 *
 *  请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 *
 *  你可以想象内部操作如下:
 *
 *  // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 *  int len = removeDuplicates(nums);
 *
 *  // 在函数里修改输入数组对于调用者是可见的。
 *  // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 *  for (int i = 0; i < len; i++) {
 *      print(nums[i]);
 *  }
 *  通过次数359,031提交次数707,435
 *
 */
public class RemoveDuplicates {

    /**
     * 最优解法：双指针法（快慢指针）
     * 复杂度分析：
     *      因为只遍历了一次数组，因此：
     *          时间复杂度：O(n)
     *          空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    private static int removeDuplicates1(int [] nums){
        if (nums.length == 0) return 0;
        int i = 0; // 慢指针
        for (int j = 1; j < nums.length; j++) { // j 是快指针
            // 当满足 nums[j] == nums[i] 就增加 j 跳过
            if (nums[j] != nums[i]) { // 如果不等则说明与i重复项已经结束，需要增加i
                nums[++i] = nums[j]; // 将j所对应的值赋值给i+1 位
            }
        }
        return i + 1; // i 是从 0 开始的，因此最后返回的时候要+1来表示长度
    }

    public static void main(String[] args) {
        int [] nums =  {0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates1(nums));
    }
}
