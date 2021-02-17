package com.mashibing.algorithm.dp;

/**
 * @Description: 背包问题
 * @author : david
 * @date Date : 2021年02月16日 13:36
 * @version V1.0
 */
public class Knapsack {
	// w, v货物的重量和价值
	// bag背包容量
	public static int maxValue(int[] w, int[] v, int bag) {
		if (w == null || v == null || w.length == 0 || v.length == 0) {
			return 0;
		}
		return process(w, v, 0, bag);
	}

	// 当前考虑到了index号货物，index自由选择
	// 做的选择不能超过背包容量
	// 返回最大价值
	public static int process(int[] w, int[] v, int index, int bagRest) {
		// 货物选完了，或者背包没有容量了，返回0
		if ( bagRest < 0) {
			return -1;
		}
		if (index == w.length) {
			return 0;
		}
		// 还有货在index位置，不要当前的货
		int p1 = process(w, v, index + 1, bagRest);
		// 要当前的货
		int p2 = 0;
		int next = process(w, v, index + 1, bagRest - w[index]);
		if (next != -1) {
			p2 = v[index] + next;
		}
		return Math.max(p1, p2);
	}

	// index范围0~n
	// rest 负~bag
	public static int maxValueDp(int[] w, int[] v, int bag) {
		if (w == null || v == null || w.length == 0 || v.length == 0) {
			return 0;
		}
		int n = w.length;
		int[][] dp = new int[n + 1][bag + 1];
		for (int index = n - 1; index >= 0 ; index--) {
			for (int rest = 0; rest <= bag; rest++) {
				// 还有货在index位置，不要当前的货
				int p1 = dp[index + 1][rest];
				// 要当前的货
				int p2 = 0;
				int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
				if (next != -1) {
					p2 = v[index] + next;
				}
				dp[index][rest] = Math.max(p1, p2);
			}
		}
		return dp[0][bag];
	}
	public static void main(String[] args) {
		int[] wights = {3, 2, 0, 4, 7, 3, 1,  7, 0};
		int[] values = {5, 6, 30, 3, 19, 12, 4, 2, 50};
		int bag = 15;
		System.out.println(maxValue(wights, values, bag));
		System.out.println(maxValueDp(wights, values, bag));
	}
}
