package com.leetcode;

/**
 * @Description: 求最长回文子串
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 * @author : david
 * @date Date : 2021年03月13日 12:13
 * @version V1.0
 */
public class LongestPalindromicSubstring {
	public String longestPalindrome(String s) {
		int len = s.length();
		if (len < 2) {
			return s;
		}

		int maxLen = 1;
		int begin = 0;
		// s.charAt(i) 每次都会检查数组下标越界，因此先转换成字符数组
		char[] charArray = s.toCharArray();

		// 枚举所有长度大于 1 的子串 charArray[i..j]
		for (int i = 0; i < len - 1; i++) {
			for (int j = i + 1; j < len; j++) {
				if (j - i + 1 > maxLen && validPalindromic(charArray, i, j)) {
					maxLen = j - i + 1;
					begin = i;
				}
			}
		}
		return s.substring(begin, begin + maxLen);
	}

	public String dp(String s) {
		int len = s.length();
		if (len < 2) {
			return s;
		}
		boolean[][] dp = new boolean[len][len];
		String ans = "";
		for (int l = 0; l < len; ++l) {
			for (int i = 0; i + l < len; ++i) {
				int j = i + l;
				if (l == 0) {
					dp[i][j] = true;
				}
				else if (l == 1) {
					dp[i][j] = (s.charAt(i) == s.charAt(j));
				}
				else {
					dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
				}
				if (dp[i][j] && l + 1 > ans.length()) {
					ans = s.substring(i, i + l + 1);
				}
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		String s = "babad";
		System.out.println(new LongestPalindromicSubstring().dp(s));
	}

	/**
	 * 验证子串 s[left..right] 是否为回文串
	 */
	private boolean validPalindromic(char[] charArray, int left, int right) {
		while (left < right) {
			if (charArray[left] != charArray[right]) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}
}
