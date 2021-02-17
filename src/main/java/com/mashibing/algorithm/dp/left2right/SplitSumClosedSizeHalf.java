package com.mashibing.algorithm.dp.left2right;

/**
 * @Description: 把一个非负的数组拆分成两个集合
 * 要求它们的累加和最接近，且个数最接近，求较小一个的累加和
 * @author : david
 * @date Date : 2021年02月17日 20:27
 * @version V1.0
 */
public class SplitSumClosedSizeHalf {

	public static int right(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int sum = 0;
		for (int num : arr) {
			sum += num;
		}
		int p1 = process(arr, 0, arr.length >> 1, sum >> 1);
		if ((arr.length & 1) == 0) {
			return p1;
		} else {
			return Math.max(p1, process(arr, 0, (arr.length >> 1) + 1, sum >> 1));
		}
	}

	// arr[i..]可以自由选择，挑选个数为picks个
	// 返回累加和尽量接近rest但不超过rest的情况下，最接近的累加和
	public static int process(int[] arr, int i, int picks, int rest) {
		if (i == arr.length) {
			return picks == 0 ? 0 : -1;
		} else {
			// p1 不使用arr[i]
			int p1 = process(arr, i + 1, picks, rest);
			// p2 使用arr[i]
			int p2 = 0;
			int next = -1;
			if (arr[i] <= rest && picks - 1 >= 0) {
				next = process(arr, i + 1, picks - 1, rest - arr[i]);
			}
			if (next != -1) {
				p2 = arr[i] + next;
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
		sum /= 2;
		int n = arr.length;
		int m = (n + 1) / 2; // 向上取整
		int[][][] dp = new int[n + 1][m + 1][sum + 1];

		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				for (int k = 0; k <= sum; k++) {
					dp[i][j][k] = -1;
				}
			}
		}
		for (int rest = 0; rest <= sum; rest++) {
			dp[n][0][rest] = 0;
		}
		for (int i = n - 1; i >= 0; i--) {
			for (int picks = 0; picks <= m; picks++) {
				for (int rest = 0; rest <= sum; rest++) {
					// p1 不使用arr[i]
					int p1 = dp[i + 1][picks][rest];
					// p2 使用arr[i]
					int p2 = 0;
					int next = -1;
					if (arr[i] <= rest && picks - 1 >= 0) {
						next = dp[i + 1][picks - 1][rest - arr[i]];
					}
					if (next != -1) {
						p2 = arr[i] + next;
					}
					dp[i][picks][rest] = Math.max(p1, p2);
				}
			}
		}

		int p1 = dp[0][n >> 1][sum];
		if ((arr.length & 1) == 0) {
			return p1;
		} else {
			return Math.max(p1, dp[0][(n >> 1) + 1][sum]);
		}
	}

	public static void main(String[] args) {
		int[] arr = {100, 2, 3, 20, 41, 22};
		System.out.println(right(arr));
		System.out.println(dp(arr));
	}
}
