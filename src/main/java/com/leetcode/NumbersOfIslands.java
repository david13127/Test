package com.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月28日 20:56
 * @version V1.0
 */
public class NumbersOfIslands {
	public int numIslands(char[][] grid) {
		return useBfs(grid);
	}

	private int useBfs(char[][] grid) {
		int row = grid.length;
		int col = grid[0].length;
		int ans = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == '1') {
					ans++;
					Queue<int[]> queue = new LinkedList<>();
					queue.offer(new int[]{i, j});
					while (!queue.isEmpty()) {
						int[] poll = queue.poll();
						int r = poll[0];
						int c = poll[1];
						if (r - 1 >= 0 && grid[r - 1][c] == '1') {
							grid[r - 1][c] = '0';
							queue.offer(new int[] {r - 1, c});
						}
						if (c + 1 < col && grid[r][c + 1] == '1') {
							grid[r][c + 1] = '0';
							queue.offer(new int[] {r, c + 1});
						}
						if (r + 1 < row && grid[r + 1][c] == '1') {
							grid[r + 1][c] = '0';
							queue.offer(new int[] {r + 1, c});
						}
						if (c - 1 >= 0 && grid[r][c - 1] == '1') {
							grid[r][c - 1] = '0';
							queue.offer(new int[] {r, c - 1});
						}
					}
				}
			}
		}
		return ans;
	}

	private int useDfs(char[][] grid) {
		int row = grid.length;
		int col = grid[0].length;
		int ans = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == '1') {
					ans++;
					dfs(grid, i, j);
				}
			}
		}
		return ans;
	}

	private void dfs(char[][] grid, int row, int col) {
		grid[row][col] = '0';
		// 右为1
		if (col + 1 < grid[0].length && grid[row][col + 1] == '1') {
			dfs(grid, row, col + 1);
		}
		// 上为1
		if (row - 1 >= 0 && grid[row - 1][col] == '1') {
			dfs(grid, row - 1, col);
		}
		// 左为1
		if (col - 1 >= 0 && grid[row][col - 1] == '1') {
			dfs(grid, row, col - 1);
		}
		// 下为1
		if (row + 1 < grid.length && grid[row + 1][col] == '1') {
			dfs(grid, row + 1, col);
		}
	}

	public static void main(String[] args) {
		NumbersOfIslands solution = new NumbersOfIslands();
		char[][] grid = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'},
				{'0', '0', '0', '1', '1'}};
		int ans = solution.numIslands(grid);
		System.out.println(ans);
	}
}
