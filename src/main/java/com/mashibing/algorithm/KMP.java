package com.mashibing.algorithm;

/**
 * @Description: KMP算法
 * @author : david
 * @date Date : 2021年02月13日 13:26
 * @version V1.0
 */
public class KMP {
	public static int getIndexOf(String s, String m) {
		if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
			return -1;
		}
		char[] str = s.toCharArray();
		char[] match = m.toCharArray();
		int x = 0; // str中当前比对到的位置
		int y = 0; // match中当前比对到的位置
		int[] next = getNextArray(match); // next[i] match中i之前的字符串match[0..i-1]前缀和后缀相同的长度
		while (x < str.length && y < match.length) {
			if (str[x] == match[y]) {
				x++;
				y++;
			} else if (next[y] == -1) { // y == 0
				x++;
			} else {
				y = next[y];
			}
		}
		// 1. x越界，y没有，表示没匹配到
		// 2. x没越界，y越界了，找到了，返回位置
		return y == match.length ? x - y : -1;
	}

	public static int[] getNextArray(char[] match) {
		if (match.length == 1) {
			return new int[]{-1};
		}
		int[] next = new int[match.length];
		next[0] = -1;
		next[1] = 0;
		int i = 2;
		// cn代表，cn位置的字符，是当前和i-1位置比较的字符
		int cn = 0;
		while (i < next.length) {
			if (match[i - 1] == match[cn]) { // 跳出来的时候
				//	next[i] = cn + 1;
				//	i++;
				//	cn++;
				next[i++] = ++cn;
			} else if (cn > 0) { // 能往前跳，则跳
				cn = next[cn];
			} else { // 不能跳了
				next[i++] = 0;
			}
		}
		return next;
	}

	public static void main(String[] args) {
		String str1 = "abc1234efd";
		String str2 = "1234";
		System.out.println(getIndexOf(str1, str2));
	}
}
