package com.leetcode;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月23日 10:19
 * @version V1.0
 */
public class GuessNumbers {
	public int game(int[] guess, int[] answer) {
		int ans = 0;
		int n = guess.length;
		for (int i = 0; i < n; i++) {
			if (guess[i] == answer[i]) {
				ans++;
			}
		}
		return ans;
	}
}
