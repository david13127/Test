package com.algorithm;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2020年04月09日 22:41
 * @version V1.0
 */
public class GoldbachConjecture {
	public static void main(String[] args) {
		int i = 4;
		for (; i <= 2000; i += 2) {
			int n = 2;
			for (; n < i; n++) {
				if (prime(n) > 0) {
					if (prime(i - n) > 0) {
						System.out.println(i + "=" + n + "+" + (i - n));
						break;
					}
				}
			}
			if (i == n) {
				System.out.println("error");
			}
		}
	}

	private static int prime(int i) {
		if (i <= 1) {
			return 0;
		}
		if (i == 2) {
			return 1;
		}
		for (int j = 2; j <= (int) (Math.sqrt((double) i)); j++) {
			if (i % j == 0) {
				return 0;
			}
		}
		return 1;
	}
}
