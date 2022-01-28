package com.leetcode;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月28日 20:24
 * @version V1.0
 */
public class MinimumSizeSubarraySum {
	public int minSubArrayLen(int target, int[] nums) {
		int[] preSum = new int[nums.length + 1];
		preSum[0] = nums[0];
		for (int i = 0; i < nums.length; i++) {
			preSum[i + 1] = preSum[i] + nums[i];
		}
		int left = 0;
		int right = 0;
		int ans = nums.length + 1;
		while (left <= right && right < nums.length) {
			if (preSum[right + 1] - preSum[left] >= target) {
				ans = Math.min(ans, right - left + 1);
				left++;
			} else {
				right++;
			}
		}
		return ans == nums.length + 1 ? 0 : ans;
	}

	public static void main(String[] args) {
		MinimumSizeSubarraySum solution = new MinimumSizeSubarraySum();
		int target = 6;
		int[] nums = {10, 2, 3};
		int ans = solution.minSubArrayLen(target, nums);
		System.out.println(ans);
	}
}
