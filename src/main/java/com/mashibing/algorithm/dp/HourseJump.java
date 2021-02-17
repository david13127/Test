package com.mashibing.algorithm.dp;

/**
 * @Description: 跳马问题
 * @author : david
 * @date Date : 2021年02月13日 16:01
 * @version V1.0
 */
public class HourseJump {

	// 递归
	public static int ways1(int x, int y, int k) {
		return f1(x, y, k);
	}

	// 马从(0,0)出发，有K步要走，并且一定要走完，最终来到x，y位置的方法数是多少
	private static int f1(int x, int y, int k) {
		if (k == 0) {
			return x == 0 && y == 0 ? 1 : 0;
		}
		if (x < 0 || x > 9 || y < 0 || y > 8) {
			return 0;
		}
		// 有步数要走, x，y 也是棋盘上的位置
		return f1(x + 2, y - 1, k - 1) + f1(x + 2, y + 1, k - 1) + f1(x + 1, y + 2, k - 1) + f1(x - 1, y + 2, k - 1)
				+ f1(x - 2, y + 1, k - 1) + f1(x - 2, y - 1, k - 1) + f1(x - 1, y - 2, k - 1) + f1(x + 1, y - 2, k - 1);
	}

	// 动态规划
	public static int waysDp1(int x, int y, int k) {
		int[][][] dp = new int[10][9][k + 1];// 0~k

		dp[0][0][0] = 1; // dp[..][..][0] = 0

		for (int level = 1; level <= k; level++) {
			// level层，x y
			for (int i = 0; i < 10; i++) { // x可能性
				for (int j = 0; j < 9; j++) { // y的可能性
					dp[i][j][level] =
							getValue(dp, i + 2, j - 1, level - 1) + getValue(dp, i + 2, j + 1, level - 1) + getValue(dp,
									i + 1, j + 2, level - 1) + getValue(dp, i - 1, j + 2, level - 1) + getValue(dp,
									i - 2, j + 1, level - 1) + getValue(dp, i - 2, j - 1, level - 1) + getValue(dp,
									i - 1, j - 2, level - 1) + getValue(dp, i + 1, j - 2, level - 1);
				}
			}
		}

		return dp[x][y][k];
	}

	private static int getValue(int[][][] dp, int x, int y, int k) {
		if (x < 0 || x > 9 || y < 0 || y > 8) {
			return 0;
		}
		return dp[x][y][k];
	}


	public static int ways2(int x, int y, int k) {
		return f2(0, 0, k, x, y);
	}

	// 当前来到row，col位置，还剩rest步，走完rest步之后，来到x，y位置，方法数多少
	public static int f2(int x, int y, int rest, int a, int b) {
		if (rest == 0) {
			return x == a && y == b ? 1 : 0;
		}

		if (x < 0 || x > 9 || y < 0 || y > 8) {
			return 0;
		}
		// 8种跳法
		int p1 = f2(x + 2, y - 1, rest - 1, a, b);
		int p2 = f2(x + 2, y + 1, rest - 1, a, b);
		int p3 = f2(x + 1, y + 2, rest - 1, a, b);
		int p4 = f2(x - 1, y + 2, rest - 1, a, b);
		int p5 = f2(x - 2, y + 1, rest - 1, a, b);
		int p6 = f2(x - 2, y - 1, rest - 1, a, b);
		int p7 = f2(x - 1, y - 2, rest - 1, a, b);
		int p8 = f2(x + 1, y - 2, rest - 1, a, b);
		return p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8;
	}

	public static int waysDp2(int a, int b, int k) {
		int[][][] dp = new int[10][9][k + 1];// 0~k

		dp[a][b][0] = 1; // dp[..][..][0] = 0
		for (int rest = 1; rest <= k; rest++) {
			for (int x = 0; x < 10; x++) {
				for (int y = 0; y < 9; y++) {
					// 8种跳法
					int p1 = getValue(dp, x + 2, y - 1, rest - 1);
					int p2 = getValue(dp, x + 2, y + 1, rest - 1);
					int p3 = getValue(dp, x + 1, y + 2, rest - 1);
					int p4 = getValue(dp, x - 1, y + 2, rest - 1);
					int p5 = getValue(dp, x - 2, y + 1, rest - 1);
					int p6 = getValue(dp, x - 2, y - 1, rest - 1);
					int p7 = getValue(dp, x - 1, y - 2, rest - 1);
					int p8 = getValue(dp, x + 1, y - 2, rest - 1);
					dp[x][y][rest] = p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8;
				}
			}
		}

		return dp[0][0][k];
	}

	public static void main(String[] args) {
		int x = 8;
		int y = 6;
		int k = 10;
		System.out.println(ways1(x, y, k));
		System.out.println(waysDp1(x, y, k));
		System.out.println(ways2(x, y, k));
		System.out.println(waysDp2(x, y, k));
	}

}
