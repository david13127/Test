package com.leetcode.dp;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月28日 22:14
 * @version V1.0
 */
public class JumpGameI {
	public boolean canJump(int[] nums) {
		boolean[] dp = new boolean[nums.length];
		dp[0] = true;
		for (int i = 1; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				if (dp[j] && nums[j] + j >= i) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[nums.length - 1];
	}
}
