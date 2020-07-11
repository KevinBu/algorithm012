package cn.com.kevin.algorithm;

/**
 * 189 旋转数组
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 *
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * 说明:
 *
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法。
 */
public class RotateArray {
    /**
     * 方案 1
     *  暴力解法:
     *      时间复杂度O(n^2)
     *      空间复杂度位O(1)
     * @param nums
     * @param k
     */
    private static void rotate1(int[] nums, int k) {
        if(k == 0){
            return;
        }
        k = k % nums.length;
        for(int i = 1; i <= k; i++){
            int tmp = nums[nums.length - 1];
            for(int j = nums.length - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = tmp;
        }
    }

    /**
     * 方案 2 使用环状替换
     * @param nums
     * @param k
     */
    private static void rotate2(int[] nums, int k) {
        // 防止k > nums.length,如果k>nums.length 移动的次数和 k % nums.length 是一样的
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }

    /**
     * 方案 3 使用反转
     *  当我们旋转数组 k 次，k % n 个尾部元素会被移动到头部，剩下的元素会被向后移动。
     *  首先将所有元素反转。然后反转前 k 个元素，再反转后面 n−k 个元素，就能得到想要的结果。
     *  假设 n=7 且 k=3
     *      原始数组                  : 1 2 3 4 5 6 7
     *      反转所有数字后             : 7 6 5 4 3 2 1
     *      反转前 k 个数字后          : 5 6 7 4 3 2 1
     *      反转后 n-k 个数字后        : 5 6 7 1 2 3 4 --> 结果
     * @param nums
     * @param k
     */
    private static void rotate3(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    /**
     * 由于 java 的ArrayList 没有提供 反转的api, 因此需要实现
     * 通过双指针夹逼的方式进行交换数据
     * @param nums
     * @param start
     * @param end
     */
    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int [] nums = {1,2,3,4,5,6,7};
        rotate1(nums, 3);
        rotate2(nums, 11);
        rotate3(nums, 3);
    }
}
