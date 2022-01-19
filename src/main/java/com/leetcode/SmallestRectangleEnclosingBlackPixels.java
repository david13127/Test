package com.leetcode;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月16日 10:39
 * @version V1.0
 */
public class SmallestRectangleEnclosingBlackPixels {
	public int minArea(char[][] image, int x, int y) {
		int minX = x;
		int minY = y;
		int maxX = x;
		int maxY = y;
		int m = image.length;
		int n = image[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (image[i][j] == '1') {
					minX = Math.min(minX, i);
					minY = Math.min(minY, j);
					maxX = Math.max(maxX, i);
					maxY = Math.max(maxY, j);
				}
			}
		}
		return (maxX - minX + 1) * (maxY - minY + 1);
	}

	public int minArea1(char[][] image, int x, int y) {
		int m = image.length, n = image[0].length;
		int left = searchColumns(image, 0, y, 0, m, true);
		int right = searchColumns(image, y + 1, n, 0, m, false);
		int top = searchRows(image, 0, x, left, right, true);
		int bottom = searchRows(image, x + 1, m, left, right, false);
		return (right - left) * (bottom - top);
	}

	private int searchColumns(char[][] image, int i, int j, int top, int bottom, boolean whiteToBlack) {
		while (i != j) {
			int k = top, mid = (i + j) / 2; 
			while (k < bottom && image[k][mid] == '0') ++k;
			if (k < bottom == whiteToBlack) // k < bottom means the column mid has black pixel
				j = mid; //search the boundary in the smaller half
			else
				i = mid + 1; //search the boundary in the greater half
		}
		return i;
	}
	private int searchRows(char[][] image, int i, int j, int left, int right, boolean whiteToBlack) {
		while (i != j) {
			int k = left, mid = (i + j) / 2;
			while (k < right && image[mid][k] == '0') ++k;
			if (k < right == whiteToBlack) // k < right means the row mid has black pixel
				j = mid;
			else
				i = mid + 1;
		}
		return i;
	}

	public static void main(String[] args) {
		SmallestRectangleEnclosingBlackPixels solution = new SmallestRectangleEnclosingBlackPixels();
		char[][] image = {{'0','0','1','0'},{'0','1','1','0'},{'0','1','0','0'}};
		int ans = solution.minArea(image, 0, 2);
		System.out.println(ans);
	}
}
