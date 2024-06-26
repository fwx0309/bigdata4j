package com.fwx.leecode;

/**
 * [
 *  两数之和
 *  给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 *
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 *
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/10/10 16:35 ]
 */
public class D0001两数之和 {
    public static void main(String[] args) {
//        int[] nums = new int[]{2, 7, 11, 15};
//        int target = 9;

        int[] nums = new int[]{3,2,4};
        int target = 6;

        int[] indexs = twoSum(nums, target);
        for (int index : indexs) {
            System.out.println(index);
        }
    }

    public static int[] twoSum(int[] nums, int target) {

        int a = -1;
        int b = -1;

        for (int i = 0; i < nums.length; i++) {
            int num1 = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int num2 = nums[j];

                if ((num1 + num2) == target) {
                    a = i;
                    b = j;
                    break;
                }
            }
        }

        return new int[]{a, b};
    }
}
