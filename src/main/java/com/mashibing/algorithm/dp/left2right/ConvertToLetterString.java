package com.mashibing.algorithm.dp.left2right;

/**
 * @Description: 1-26对应a-z，把一串数字的字符串解析成字母的种数
 * @author : david
 * @date Date : 2021年02月16日 14:19
 * @version V1.0
 */
public class ConvertToLetterString {
	public static int number(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		return process(str.toCharArray(), 0);
	}

	// 从后往前试
	// str[0..i-1]转化无需过问
	// str[i...]转化，返回多少种方法
	public static int process(char[] str, int i) {
		// 到了最后一个了，说明已经转化完成，返回方法数为1种
		if (i == str.length) {
			return 1;
		}
		// 如果是0，则之前转换的是1或者2，0不对应字母，说明应该转化10或者20，因此转化失败
		if (str[i] == '0') {
			return 0;
		}
		int ways = process(str, i + 1);
		if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
			ways += process(str, i + 2);
		}
		return ways;
	}

	public static int dp(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] str = s.toCharArray();
		int n = str.length;
		int[] dp = new int[n + 1];
		dp[n] = 1;
		for (int i = n - 1; i >=0 ; i--) {
			// 如果是0，则之前转换的是1或者2，0不对应字母，说明应该转化10或者20，因此转化失败
			if (str[i] != '0') {
				int ways = dp[i + 1];
				if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
					ways += dp[i + 2];
				}
				dp[i] = ways;
			}
		}

		return dp[0];
	}
	public static void main(String[] args) {
		String str = "114517132425";
		System.out.println(number(str));
		System.out.println(dp(str));
	}
}
