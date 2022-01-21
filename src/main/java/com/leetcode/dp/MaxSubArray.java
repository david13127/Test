package com.leetcode.dp;

/**
 * https://leetcode-cn.com/problems/maximum-subarray/
 */
public class MaxSubArray {
	public int maxSubArray(int[] nums) {
		int[] pre = new int[nums.length];
		pre[0] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			int x = nums[i];
			pre[i] = Math.max(pre[i - 1] + x, x);
		}
		int ans = nums[0];
		for (int num : pre) {
			ans = Math.max(ans, num);
		}
		return ans;
	}
}
