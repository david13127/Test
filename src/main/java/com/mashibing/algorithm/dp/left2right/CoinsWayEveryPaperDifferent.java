package com.mashibing.algorithm.dp.left2right;

/**
 * @Description: 面值不同，且张数都只有一张，求组合出目标金额的方法
 * @author : david
 * @date Date : 2021年02月17日 9:43
 * @version V1.0
 */
public class CoinsWayEveryPaperDifferent {

	public static int coinWays(int[] arr, int aim) {
		return process(arr, 0, aim);
	}

	public static int process(int[] arr, int index, int rest) {
		if (rest < 0) {
			return 0;
		}
		if (rest == arr.length) {
			return rest == 0 ? 1 : 0;
		} else {
			return process(arr, index + 1, rest) + process(arr, index + 1, rest - arr[index]);
		}
	}

	public static int dp(int[] arr, int aim) {
		if (aim == 0) {
			return 1;
		}
		int n = arr.length;
		int[][] dp = new int[n + 1][aim + 1];
		dp[0][0] = 1;
		for (int index = 0; index <= n; index++) {
			for (int rest = 0; rest <= aim; rest++) {
				dp[index][rest] = dp[index + 1][rest] + rest - arr[index] >= 0 ? dp[index + 1][rest - arr[index]] : 0;
			}
		}
		return dp[0][aim];
	}
}
