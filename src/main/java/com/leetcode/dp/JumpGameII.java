package com.leetcode.dp;

import java.util.Arrays;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月28日 22:14
 * @version V1.0
 */
public class JumpGameII {
	public int jump1(int[] nums) {
		int[] dp = new int[nums.length];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 1; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[j] + j >= i) {
					dp[i] = Math.min(dp[i], dp[j] + 1);
				}
			}
		}
		return dp[nums.length - 1];
	}
	public int jump2(int[] nums) {
		int[] dp = new int[nums.length];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j = 1; j <= nums[i]; j++) {
				if (i + j >= nums.length) {
					return dp[nums.length - 1];
				}
				dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
			}
		}
		return dp[nums.length - 1];
	}

	public static void main(String[] args) {
		JumpGameII solution = new JumpGameII();
		int[] nums = {2, 3, 1, 1, 4, 1, 2, 3};
		int ans = solution.jump2(nums);
		System.out.println(ans);
	}
}
