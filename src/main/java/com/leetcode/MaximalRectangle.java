package com.leetcode;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月15日 12:13
 * @version V1.0
 */
public class MaximalRectangle {
	public int maximalRectangle(char[][] matrix) {
		int m = matrix.length;
		if (m == 0) {
			return 0;
		}
		int n = matrix[0].length;
		int[][] left = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] != '0') {
					left[i][j] = j == 0 ? 0 : left[i][j - 1] + 1;
				}
			}
		}
		int ans = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] != '0') {
					int width = left[i][j];
					int area = width;
					for (int k = i - 1; k >= 0; k--) {
						width = Math.min(width, left[k][j]);
						area = Math.max(area, (i - k + 1) * width);
					}
					ans = Math.max(area, ans);
				}
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		char[][] matrix = {{'1'}};
		MaximalRectangle maximalRectangle = new MaximalRectangle();
		int ans = maximalRectangle.maximalRectangle(matrix);
		System.out.println(ans);
	}
}
