package com.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月30日 21:37
 * @version V1.0
 */
public class FloodFill {
	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
		int m = image.length;
		int n = image[0].length;
		int sColor = image[sr][sc];
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[]{sr, sc});
		boolean[][] visted = new boolean[m][n];
		while(!queue.isEmpty()) {
			int[] point = queue.poll();
			int x = point[0];
			int y = point[1];
			visted[x][y] = true;
			image[x][y] = newColor;
			// 上
			if (x - 1 >= 0 && image[x - 1][y] == sColor && !visted[x - 1][y]) {
				queue.offer(new int[]{x - 1, y});
			}
			// 下
			if (x + 1 < m && image[x + 1][y] == sColor && !visted[x + 1][y]) {
				queue.offer(new int[]{x + 1, y});
			}
			// 左
			if (y - 1 >= 0 && image[x][y - 1] == sColor && !visted[x][y - 1]) {
				queue.offer(new int[]{x, y - 1});
			}
			// 右
			if (y + 1 < n && image[x][y + 1] == sColor && !visted[x][y + 1]) {
				queue.offer(new int[]{x, y + 1});
			}
		}
		return image;
	}

	public static void main(String[] args) {
		FloodFill solution = new FloodFill();
		int[][] image = {{0, 0, 0}, {0, 1, 1}};
		int sr = 1;
		int sc = 1;
		int newColor = 1;
		solution.floodFill(image, sr, sc, newColor);
	}
}
