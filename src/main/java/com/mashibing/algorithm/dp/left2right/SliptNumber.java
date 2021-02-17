package com.mashibing.algorithm.dp.left2right;

/**
 * @Description: 数字裂开，把一个数拆分成多个比它小的数相加，要求前面的数比后面的数小，约定不能有负数
 * @author : david
 * @date Date : 2021年02月17日 18:59
 * @version V1.0
 */
public class SliptNumber {

	public static int ways(int n) {
		if (n < 0) {
			return 0;
		}
		if (n == 0) {
			return 1;
		}
		return process(1, n);
	}

	public static int process(int pre, int rest) {
		// 要求前面的数不能大于后面的数
		if (rest == 0) { // 6 = 6 + 0 或上一步合规，如6 = 2 + 2 + 2
			return 1;
		}
		if (pre > rest) { // 6 = 4 + 2
			return 0;
		}
		if (pre == rest) { // 6 = 3 + 3
			return 1;
		}
		// pre < rest
		int ways = 0;
		for (int first = pre; first <= rest; first++) {
			ways += process(first, rest - first);
		}
		return ways;
	}

	public static int dp(int n) {
		if (n < 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		int[][] dp = new int[n + 1][n + 1];
		for (int j = 1; j <= n; j++) {
			dp[j][0] = 1;
			dp[j][j] = 1;
		}
		for (int pre = n - 1; pre >= 1; pre--) {
			for (int rest = pre + 1; rest <= n; rest++) {
				int ways = 0;
				for (int first = pre; first <= rest; first++) {
					ways += dp[first][rest - first];
				}
				dp[pre][rest] = ways;
				// dp[pre][rest]为dp表中，某一条斜线的值相加
				// dp[3][6] = dp[3][3] + dp[4][2] + dp[5][1] + dp[6][0]
				//            dp[4][6] = dp[4][2] + dp[5][1] + dp[6][0]
				// dp[3][6] = dp[4][6] + dp[3][3]
				// dp[pre][rest] = dp[pre + 1][rest];
				// dp[pre][rest] += dp[pre][rest - pre];
			}
		}
		return dp[1][n];
	}

	public static void main(String[] args) {
		int test = 121;
		System.out.println(ways(test));
		System.out.println(dp(test));
	}
}
