package com.leetcode;

/**
 * @Description: 位运算实现加减乘除运算
 * @author : david
 * @date Date : 2021年03月20日 22:02
 * @version V1.0
 */
public class BitAddMinusMultDiv {
	public static int add(int a, int b) {
		int sum = a;
		while (b != 0) {
			sum = a ^ b; // 无进位相加 -> sum
			b = (a & b) << 1; // 进位信息 -> b
			a = sum;
		}
		return sum;
	}

	public static int negNum(int num) {
		return add(~num, 1);
	}

	public static int minus(int a, int b) {
		return add(a, negNum(b));
	}

	public static int multi(int a, int b) {
		int res = 0;
		while (b != 0) {
			if ((b & 1) != 0) {
				res = add(res, a);
			}
			a <<= 1;
			b >>>= 1;
		}
		return res;
	}

	public static boolean isNeg(int num) {
		return num < 0;
	}

	public static int div(int a, int b) {
		int x = isNeg(a) ? negNum(a) : a;
		int y = isNeg(b) ? negNum(b) : b;
		int res = 0;
		for (int i = 30; i >= 0; i--) {
			if ((x >> i) >= y) {
				res |= (1 << i);
				x = minus(x, y << i);
			}
		}
		return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
	}

	public static int divide(int a, int b) {
		if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
			return 1;
		} else if (b == Integer.MIN_VALUE) {
			return 0;
		} else if (a == Integer.MIN_VALUE) {
			if (b == negNum(1)) {
				return Integer.MAX_VALUE;
			} else {
				int c = div(add(a, 1), b);
				return add(c, div(minus(a, minus(c, b)), b));
			}
		} else {
			return div(a, b);
		}
	}

	public static void main(String[] args) {
		System.out.println(add(12, 34));
		System.out.println(minus(12, 34));
		System.out.println(multi(12, 34));
		System.out.println(divide(-24, 12));
	}
}
