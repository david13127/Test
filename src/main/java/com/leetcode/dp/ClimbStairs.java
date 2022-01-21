package com.leetcode.dp;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月21日 23:10
 * @version V1.0
 */
public class ClimbStairs {
	public int climbStairs(int n) {
		int[] dp = new int[n + 1];
		dp[0] = 1;
		dp[1] = 2;
		if (n <= 1) {
			return dp[n - 1];
		}
		for (int i = 2; i < n; i++) {
			dp[i] = dp[i - 2] + dp[i - 1];
		}
		return dp[n - 1];
	}
}
