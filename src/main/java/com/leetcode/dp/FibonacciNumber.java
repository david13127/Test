package com.leetcode.dp;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月23日 10:00
 * @version V1.0
 */
public class FibonacciNumber {
	public int fib(int n) {
		int[] dp = new int[n + 1];
		dp[0] = 0;
		if (n == 0) {
			return dp[0];
		}
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 2] + dp[i - 1];
		}
		return dp[n];
	}
}
