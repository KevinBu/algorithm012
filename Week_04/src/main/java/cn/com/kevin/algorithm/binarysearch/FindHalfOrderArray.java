package cn.com.kevin.algorithm.binarysearch;

public class FindHalfOrderArray {

    /**
     * 使用二分查找，寻找一个半有序数组 [6, 7, 0, 1, 2, 4, 5] 中间无序的地方
     * 本题可以转换为查找数组中最小值的坐标来实现
     * 
     * @param array
     * @return 半序数组中无序值的索引
     */
    public static int findHalfOrderArray(int [] array){
        int left = 0, right = array.length - 1, mid;
        while (left < right) {
            mid = (right - left) / 2 + left;
            if (array[mid] > array[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int [] arr = {6, 7, 0, 1, 2, 4, 5};
        //int [] arr = {1, 2, 4, 5, 6, 7, 0};
        System.out.println(findHalfOrderArray(arr));
    }
}
