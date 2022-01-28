package com.leetcode.dp;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月23日 10:07
 * @version V1.0
 */
public class NTthTribonacciNumber {
	public int tribonacci(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1 || n == 2) {
			return 1;
		}
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = dp[2] = 1;
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i -3] + dp[i - 2] + dp[i - 1];
		}
		return dp[n];
	}
}
