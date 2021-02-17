package com.mashibing.algorithm.dp.left2right;

/**
 * @Description: 面值不同，张数无限，求组合出目标金额的方法
 * @author : david
 * @date Date : 2021年02月17日 9:54
 * @version V1.0
 */
public class CoinsWayNoLimit {
	public static int coinsWay(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		return process(arr, 0, aim);
	}

	// arr[index..]所有面值，每一个面值都可以选择任意张数
	public static int process(int[] arr, int index, int rest) {
		if (index == arr.length) {
			return rest == 0 ? 1 : 0;
		}
		int ways = 0;
		for (int per = 0; per * arr[index] <= rest; per++) {
			ways += process(arr, index + 1, rest - per * arr[index]);
		}
		return ways;
	}

	public static int dp(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		int n = arr.length;
		int[][] dp = new int[n + 1][aim + 1];
		dp[n][0] = 1;

		for (int index = n - 1; index >= 0; index--) {
			for (int rest = 0; rest <= aim; rest++) {
				int ways = 0;
				for (int per = 0; per * arr[index] <= rest; per++) {
					ways += dp[index + 1][rest - per * arr[index]];
				}
				dp[index][rest] = ways;
				// 求index行14列的值，面值为3
				// 需要将index+1行，14列，11列，8列，5列，2列的值相加，是一个枚举过程，可优化
				// 通过dp表可发现，11列，8列，5列，2列的值相加，是index行11列的值，
				// 所以可以用dp[index][11] + dp[index+1][14]代替
				// dp[index][rest] = dp[index + 1][rest];
				// if (rest - arr[index] >= 0) {
				// 	dp[index][rest] += dp[index][rest - arr[index]];
				// }
			}
		}
		return dp[0][aim];
	}
}
