package com.fwx.leecode;

/**
 * @ClassName D09搜索插入位置
 * @Description
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 请必须使用时间复杂度为 O(log n) 的算法。
 *
 * 示例 1:
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 *
 * 示例2:
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 *
 * 示例 3:
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 *
 * 提示:
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 为无重复元素的升序排列数组
 * -104 <= target <= 104
 *
 * @Author Fwx
 * @Date 2023/5/31 15:07
 * @Version 1.0
 */
public class D0009搜索插入位置 {
    public static void main(String[] args) {
        int index = searchInsert(new int[]{1, 3, 5, 6}, 7);
        System.out.println("index = " + index);
    }

    public static int searchInsert(int[] nums, int target) {
        int index = -1;
        int length = nums.length;

        if (target > nums[length -1]) {
            index = length;
        }

        if (target < nums[0]) {
            return 0;
        }

        for (int i = 0; i < length; i++) {
            if (target == nums[i]) {
                index = i;
            } else if (length > i + 1 && nums[i + 1] > target && nums[i] < target) {
                index = i + 1;
            }
        }

        return index;
    }
}
