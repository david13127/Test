package com.mashibing.algorithm.dp;

/**
 * @Description: 最长公共子序列
 * @author : david
 * @date Date : 2021年02月16日 15:48
 * @version V1.0
 */
public class LongestCommonSubsequence {
	public static int longestCommonSubsequence(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
			return 0;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();

		// 尝试，从右往左
		return process1(str1, str2, str1.length - 1, str2.length - 1);
	}

	// str1[0..i] str2[0..j]
	private static int process1(char[] str1, char[] str2, int i, int j) {
		if (i == 0 && j == 0) {
			return str1[i] == str2[j] ? 1 : 0;
		} else if (i == 0) {
			return str2[j] == str1[i] ? 1 : process1(str1, str2, i, j - 1);
		} else if (j == 0) {
			return str1[j] == str2[i] ? 1 : process1(str1, str2, i - 1, j);
		} else { // i != 0 && j != 0
			int p1 = process1(str1, str2, i - 1, j);
			int p2 = process1(str1, str2, i, j - 1);
			int p3 = str1[i] == str2[j] ? (1 + process1(str1, str2, i, j - 1)) : 0;
			return Math.max(p1, Math.max(p2, p3));
		}
	}

	public static int longestCommonSubsequenceDp(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
			return 0;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int n = str1.length;
		int m = str2.length;
		int[][] dp = new int[n][m];

		dp[0][0] = str1[0] == str2[0] ? 1 : 0;
		for (int j = 1; j < m; j++) {
			dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
		}
		for (int i = 1; i < n; i++) {
			dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				int p1 = dp[i - 1][j];
				int p2 = dp[i][j - 1];
				int p3 = str1[i] == str2[j] ? 1 + dp[i - 1][j - 1] : 0;
				dp[i][j] = Math.max(p1, Math.max(p2, p3));
			}
		}
		return dp[n - 1][m - 1];
	}

	public static void main(String[] args) {
		String str1 = "abdasdtgfhl";
		String str2 = "slhgladglyhgd";
		System.out.println(longestCommonSubsequence(str1, str2));
		System.out.println(longestCommonSubsequenceDp(str1, str2));
	}
}
