package com.mashibing.algorithm.dp.left2right;

/**
 * @Description: 把一个非负的数组拆分成两个集合
 * 要求它们的累加和最接近，求较小一个的累加和
 * @author : david
 * @date Date : 2021年02月17日 19:54
 * @version V1.0
 */
public class SplitSumClosed {
	public static int right(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int sum = 0;
		for (int num : arr) {
			sum += num;
		}
		return process(arr, 0, sum >> 1);
	}

	// arr[i..]可以自由选择，返回累加和尽量接近rest但不超过rest的情况下，最接近的累加和
	private static int process(int[] arr, int i, int rest) {
		if (i == arr.length) { // 没数了
			return 0;
		} else { // 还有
			// p1 不使用arr[i]
			int p1 = process(arr, i + 1, rest);
			// p2 使用arr[i]
			int p2 = 0;
			if (arr[i] <= rest) {
				p2 = arr[i] + process(arr, i + 1, rest - arr[i]);
			}
			return Math.max(p1, p2);
		}
	}

	public static int dp(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int sum = 0;
		for (int num : arr) {
			sum += num;
		}
		int n = arr.length;
		sum /= 2;
		int[][] dp = new int[n + 1][sum + 1];
		for (int i = n - 1; i >= 0; i--) {
			for (int rest = 0; rest <= sum; rest++) {
				// p1 不使用arr[i]
				int p1 = dp[i + 1][rest];
				// p2 使用arr[i]
				int p2 = 0;
				if (arr[i] <= rest) {
					p2 = arr[i] + dp[i + 1][rest - arr[i]];
				}
				dp[i][rest] = Math.max(p1, p2);
			}
		}
		return dp[0][sum];
	}

	public static void main(String[] args) {
		int[] arr = {100, 2, 3, 20, 41, 22};
		System.out.println(right(arr));
		System.out.println(dp(arr));
	}
}
