package com.algorithm;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2021年02月10日 11:18
 * @version V1.0
 */
public class DP {
	public static void main(String[] args) {
		int[] value = {60, 100, 120}; // 每个物品的价值
		int[] wight = {10, 20, 30}; // 每个物品的价值
		int w = 40; // 袋子容量
		int n = 3; // 物品个数
		int[][] dp = new int[n + 1][w + 1];
		for (int i = 1; i <= n; i++) { // 往袋子里面装物品
			for (int cw = 1; cw <= w; cw++) { // cw当前重量，dp[i][cw]袋子在每个容积下所装的最大的价值
				if (wight[i - 1] <= cw) { // 判断是否可以装下
					// 剩余重量
					int leftWeight = cw - wight[i - 1];
					// 如果装
					int va1 = value[i - 1] + dp[i - 1][leftWeight];
					// 如果不装
					int va2 = dp[i-1][cw];
					dp[i][cw] = Math.max(va1, va2);
				}
				else { // 装不下
					dp[i][cw] = dp[i - 1][cw];
				}
			}
		}
		System.out.println(dp[n][w]);
	}
}
