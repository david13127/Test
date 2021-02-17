package com.mashibing.algorithm.dp.left2right;

/**
 * @Description: 斐波那契数列
 * @author : david
 * @date Date : 2021年02月16日 11:38
 * @version V1.0
 */
public class FibonacciArray {
	public static int fibonacci(int n) {
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 1;
		}
		return fibonacci(n - 1) + fibonacci(n - 2);
	}

	public static int fibonacciDp(int n) {
		int[] dp = new int[n + 1];
		dp[1] = dp[2] = 1;
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n];
	}

	public static void main(String[] args) {
		System.out.println(fibonacci(10));
		System.out.println(fibonacciDp(10));
	}
}
