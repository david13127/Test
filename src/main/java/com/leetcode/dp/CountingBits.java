package com.leetcode.dp;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 作者：LeetCode-Solution
 * 	链接：https://leetcode-cn.com/problems/counting-bits/solution/bi-te-wei-ji-shu-by-leetcode-solution-0t1i/
 * 	来源：力扣（LeetCode）
 * 	著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class CountingBits {
	public int[] countBits(int n) {
		int[] bits = new int[n + 1];
		int highBit = 0;
		for (int i = 1; i <= n; i++) {
			if ((i & (i - 1)) == 0) {
				highBit = i;
			}
			bits[i] = bits[i - highBit] + 1;
		}
		return bits;
	}

	public static void main(String[] args) {
		CountingBits solution = new CountingBits();
		int[] ans = solution.countBits(17);
		String res = Arrays.stream(ans).mapToObj(e -> e + "").collect(Collectors.joining(","));
		System.out.println(res);
	}
}
