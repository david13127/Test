package com.leetcode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 字符串转换整数 (atoi)
 * https://leetcode-cn.com/problems/string-to-integer-atoi/
 * @author : david
 * @date Date : 2021年03月14日 15:34
 * @version V1.0
 */
public class StringToIntegerAtoi {
	public int myAtoi1(String s) {
		String pattern = "[+-]*[0-9]+";
		Pattern r = Pattern.compile(pattern);
		Matcher matcher = r.matcher(s);
		String resStr = "";
		if (matcher.find()) {
			resStr = matcher.group(0);
		}
		int res = 0;
		if (Long.valueOf(resStr) < Integer.MAX_VALUE) {
			res = Integer.valueOf(resStr);
		}
		return res;
	}

	public int myAtoi(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] chars = s.toCharArray();
		boolean neg = false;
		String resStr = "";
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] >= 48 && chars[i] <= 57) {
				if (i - 1 >= 0 && chars[i - 1] == '-') {
					neg = true;
				}
				resStr += chars[i] + "";
			} else if (chars[i] == '+' || chars[i] == '-') {
				if (i + 1 < chars.length && (resStr.length() > 0 || chars[i + 1] < 48 || chars[i + 1] > 57)) {
					break;
				}
			} else if (chars[i] != ' ' || chars[i] == ' ' && resStr.length() > 0 ) {
				break;
			}
			if (resStr.length() > 0 && Long.valueOf(resStr) > Integer.MAX_VALUE) {
				resStr = neg ? (long)Integer.MAX_VALUE + 1 + "" : Integer.MAX_VALUE + "";
				break;
			}
		}
		int res = 0;
		resStr = neg ? "-" + resStr : resStr;
		if (resStr != "") {
			if (Long.valueOf(resStr) <= Integer.MAX_VALUE && Long.valueOf(resStr) >= Integer.MIN_VALUE) {
				res = Integer.valueOf(resStr);
			} else {
				res = neg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		StringToIntegerAtoi solution = new StringToIntegerAtoi();
		System.out.println(solution.myAtoi("-91283472332"));
	}
}
