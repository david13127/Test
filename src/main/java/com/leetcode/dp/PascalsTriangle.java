package com.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 	作者：hardcore-einsteinsgt
 * 	链接：https://leetcode-cn.com/problems/pascals-triangle/solution/yang-hui-san-jiao-dong-tai-gui-hua-jie-f-uwr1/
 * 	来源：力扣（LeetCode）
 * 	著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class PascalsTriangle {
	public List<Integer> generate(int numRows) {
		List<List<Integer>> result = new ArrayList<>();
		int[][] dp = new int[numRows][numRows];
		dp[0][0] = 1;
		if (numRows == 1) {
			return new ArrayList<Integer>() {{ add(dp[0][0]);}};
		}

		for (int i = 1; i < numRows; i++) {
			List<Integer> num = new ArrayList<>();
			dp[i][0] = 1;
			num.add(dp[i][0]);
			for (int j = 1; j < i; j++) {
				dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
				num.add(dp[i][j]);
			}
			dp[i][i] = 1;
			num.add(dp[i][i]);
			if (i == numRows - 1) {
				return num;
			}
		}
		return new ArrayList<>();
	}
}
