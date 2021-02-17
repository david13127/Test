package com.mashibing.algorithm.dp;

/**
 * @Description: 最大回文子序列
 * 除本方法外，可令原始字符串为str1，将原始字符串倒序为str2，然后求它们的最长公共子序列
 * @author : david
 * @date Date : 2021年02月16日 20:14
 * @version V1.0
 */
public class PalindromeSubsequence {
	public static int lpsl(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] str = s.toCharArray();
		return f(str, 0, str.length - 1);
	}

	// str[l..r]
	public static int f(char[] str, int l, int r) {
		if (l == r) {
			return 1;
		}
		if (l == r - 1) {
			return str[l] == str[r] ? 2 : 1;
		}
		// 既不以l开头，也不以r结尾
		int p1 = f(str, l + 1, r - 1);
		// 以l开头，不以r结尾
		int p2 = f(str, l, r - 1);
		// 不以l开头，以r结尾
		int p3 = f(str, l + 1, r);
		// 既以l开头，也以r结尾
		int p4 = str[l] == str[r] ? 2 + f(str, l + 1, r - 1) : 0;
		return Math.max(Math.max(p1, p2), Math.max(p3, p4));
	}

	public static int lpslDp(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] str = s.toCharArray();
		int n = str.length;
		int[][] dp = new int[n][n];
		dp[n - 1][n - 1] = 1;
		for (int i = 0; i < n - 1; i++) {
			dp[i][i] = 1;
			dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
		}
		// 从下往上填
		for (int i = n - 3; i >= 0; i--) {
			for (int j = i + 2; j < n; j++) {
				// 既不以l开头，也不以r结尾
				int p1 = dp[i + 1][j - 1];
				// 以l开头，不以r结尾
				int p2 = dp[i][j - 1];
				// 不以l开头，以r结尾
				int p3 = dp[i + 1][j];
				// 既以l开头，也以r结尾
				int p4 = str[i] == str[j] ? 2 + dp[i + 1][j - 1] : 0;
				dp[i][j] = Math.max(Math.max(p1, p2), Math.max(p3, p4));

				// 优化, 左下（p1）的情况不用考虑
				// dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
				// if (str[i] == str[j]) {
				// 	dp[i][j] = Math.max(dp[i][j], 2 + dp[i + 1][j - 1]);
				// }
			}
		}
		return dp[0][n - 1];
	}

	public static void main(String[] args) {
		String str = "sdfa4353gdshhabc44676hj4dg4789c756b4a";
		System.out.println(lpslDp(str));
	}
}
