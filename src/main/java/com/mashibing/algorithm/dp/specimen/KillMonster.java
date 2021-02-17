package com.mashibing.algorithm.dp.specimen;

/**
 * @Description: 怪兽n滴血，每次砍它掉[0..m]滴血，砍k次，k次后怪兽被砍死的概率
 * @author : david
 * @date Date : 2021年02月17日 11:39
 * @version V1.0
 */
public class KillMonster {
	public static double right1(int n, int m, int k) {
		if (n < 1 || m < 1 || k < 1) {
			return 0;
		}
		long all = (long) Math.pow(m + 1, k);
		long kill = process1(k, m, n);
		return (double) kill / (double) all;
	}

	// 怪兽还剩余hp滴血
	// 每次伤害在[0..m]范围
	// 还有times次可以砍
	// 返回砍死的概率
	private static long process1(int times, int m, int hp) {
		if (times == 0) {
			return hp <= 0 ? 1 : 0;
		}
		if (hp <= 0) {
			return (long) Math.pow(m + 1, times);
		}
		long ways = 0;
		for (int i = 0; i <= m; i++) {
			ways += process1(times - 1, m, hp - i);
		}
		return ways;
	}

	public static double dp1(int n, int m, int k) {
		if (n < 1 || m < 1 || k < 1) {
			return 0;
		}
		long all = (long) Math.pow(m + 1, k);
		long[][] dp = new long[k + 1][n + 1];
		dp[0][0] = 1;
		for (int times = 1; times <= k; times++) {
			dp[times][0] = (long) Math.pow(m + 1, times);
			for (int hp = 1; hp <= n; hp++) {
				long ways = 0;
				for (int i = 0; i <= m; i++) {
					// 没砍死
					if (hp - i > 0) {
						ways += dp[times - 1][hp - i];
					} else { // 砍死了，鞭尸，可能性是m+1的times-1次方
						ways += (long) Math.pow(m + 1, times - 1);
					}
				}
				dp[times][hp] = ways;
				// times时，dp[times][hp]=dp[times-1][hp..hp-m]的和
				// dp[times][hp-1]=dp[times-1][hp-1..hp-m-1]
				// 所以dp[times][hp]=dp[times-1][hp] + dp[times][hp-1] - dp[times-1][hp-m-1]
				// dp[times][hp] = dp[times - 1][hp] + dp[times][hp - 1];
				// if (hp - m - 1 > 0) {
				// 	dp[times][hp] -= dp[times - 1][hp - m - 1];
				// } else {
				// 	dp[times][hp] -= Math.pow(m + 1, times - 1);
				// }
			}
		}
		long kill = dp[k][n];
		return (double) kill / (double) all;
	}

	public static void main(String[] args) {
		int NMax = 10;
		int MMax = 10;
		int KMax = 10;
		int testTime = 200;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int N = (int) (Math.random() * NMax);
			int M = (int) (Math.random() * MMax);
			int K = (int) (Math.random() * KMax);
			double ans1 = right1(N, M, K);
			double ans2 = dp1(N, M, K);
			if (ans1 != ans2) {
				System.out.println("Oops!");
				break;
			}
		}
		System.out.println("测试结束");
	}
}
