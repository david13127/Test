package com.mashibing.algorithm.dp;

/**
 * @Description: 机器人走路，自顶向下的动态规划（记忆化搜索）
 * @author : david
 * @date Date : 2021年02月16日 12:00
 * @version V1.0
 */
public class RobotWalk {
	public static int ways1(int n, int start, int aim, int k) {
		if (n < 2 || start < 1 || start > n || aim < 1 || aim > n || k < 1) {
			return -1;
		}
		return process1(start, k, aim, n);
	}

	// cur当前位置，剩余频数rest，最终目标aim，有多少位置n
	private static int process1(int cur, int rest, int aim, int n) {
		if (rest == 0) {
			// 走到了，即找到一种方法，否则0
			return cur == aim ? 1 : 0;
		}
		if (cur == 1) { // 1位置只能往右
			return process1(2, rest - 1, aim, n);
		}
		if (cur == n) {
			return process1(n - 1, rest - 1, aim, n);
		}
		return process1(cur - 1, rest - 1, aim, n) + process1(cur + 1, rest - 1, aim, n);

	}

	public static int ways2(int n, int start, int aim, int k) {
		if (n < 2 || start < 1 || start > n || aim < 1 || aim > n || k < 1) {
			return -1;
		}
		int[][] dp = new int[n + 1][k + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= k; j++) {
				dp[i][j] = -1;
			}
		}
		return process2(dp, start, k, aim, n);
	}

	// cur范围：1~n
	// rest范围：0~k
	private static int process2(int[][] dp, int cur, int rest, int aim, int n) {
		if (dp[cur][rest] != -1) {
			return dp[cur][rest];
		}
		int ans;
		if (rest == 0) {
			ans = cur == aim ? 1 : 0;
		}
		else if (cur == 1) {
			ans = process2(dp, 2, rest - 1, aim, n);
		}
		else if (cur == n) {
			ans = process2(dp, n - 1, rest - 1, aim, n);
		}
		else {
			ans = process2(dp, cur - 1, rest - 1, aim, n) + process2(dp, cur + 1, rest - 1, aim, n);
		}
		dp[cur][rest] = ans;
		return ans;
	}

	public static int ways3(int n, int start, int aim, int k) {
		if (n < 2 || start < 1 || start > n || aim < 1 || aim > n || k < 1) {
			return -1;
		}
		int[][] dp = new int[n + 1][k + 1];
		dp[aim][0] = 1;
		for (int rest = 1; rest <= k; rest++) {
			dp[1][rest] = dp[2][rest - 1];
			for (int cur = 2; cur < n; cur++) {
				dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
			}
			dp[n][rest] = dp[n - 1][rest - 1];
		}
		return dp[start][k];
	}

	public static void main(String[] args) {
		System.out.println(ways1(5, 2, 4, 6));
		System.out.println(ways2(5, 2, 4, 6));
		System.out.println(ways3(5, 2, 4, 6));
	}
}
