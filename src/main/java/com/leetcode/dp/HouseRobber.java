package com.leetcode.dp;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月25日 22:15
 * @version V1.0
 */
public class HouseRobber {
	public int rob(int[] nums) {
		int len = nums.length;
		int first = nums[0];
		int second = Math.max(nums[0], nums[1]);
		for (int i = 2; i < len; i++) {
			int temp = second;
			second = Math.max(first + nums[i], second);
			first = temp;
		}
		return second;
	}
}
