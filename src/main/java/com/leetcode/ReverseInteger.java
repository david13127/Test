package com.leetcode;

/**
 * @Description: 整数反转
 * https://leetcode-cn.com/problems/reverse-integer/submissions/
 * @author : david
 * @date Date : 2021年03月14日 15:21
 * @version V1.0
 */
public class ReverseInteger {
	public int reverse(int x) {
		String str = String.valueOf(x);
		String neg = "";
		if (x < 0) {
			str = str.substring(1);
			neg = "-";
		}
		String result = "";
		for(int i = str.length() - 1; i >= 0; i--) {
			result += str.charAt(i) + "";
		}
		int res = 0;
		if (Long.valueOf(result) < Integer.MAX_VALUE) {
			int ans = Integer.valueOf(result);
			res = neg.equals("-") ? - ans : ans;
		}
		return res;
	}

	public static void main(String[] args) {
		System.out.println(new ReverseInteger().reverse(1534236469));
	}
}
