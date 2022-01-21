package com.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 作者：LeetCode-Solution
 链接：https://leetcode-cn.com/problems/russian-doll-envelopes/solution/e-luo-si-tao-wa-xin-feng-wen-ti-by-leetc-wj68/
 来源：力扣（LeetCode）
 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class RussianDollEnvelopes {
	public int maxEnvelopes(int[][] envelopes) {
		if (envelopes.length == 0) {
			return 0;
		}

		int n = envelopes.length;
		Arrays.sort(envelopes, new Comparator<int[]>() {
			public int compare(int[] e1, int[] e2) {
				if (e1[0] != e2[0]) {
					return e1[0] - e2[0];
				} else {
					return e2[1] - e1[1];
				}
			}
		});

		int[] f = new int[n];
		Arrays.fill(f, 1);
		int ans = 1;
		for (int i = 1; i < n; ++i) {
			for (int j = 0; j < i; ++j) {
				if (envelopes[j][1] < envelopes[i][1]) {
					f[i] = Math.max(f[i], f[j] + 1);
				}
			}
			ans = Math.max(ans, f[i]);
		}
		return ans;
	}

	public static void main(String[] args) {
		RussianDollEnvelopes solution = new RussianDollEnvelopes();
		int[][] envelops = new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}};
		int ans = solution.maxEnvelopes(envelops);
		System.out.println(ans);
	}
}
