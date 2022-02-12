package com.leetcode;

import java.util.*;

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
	public int numIslands2(char[][] grid) {
		int row = grid.length;
		int col = grid[0].length;
		List<String> pointList = new ArrayList<>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == '1') {
					pointList.add("r" + i + "c" + j);
				}
			}
		}
		UnionFind unionFind = new UnionFind(pointList);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == '1') {
					if (i - 1 >= 0 && grid[i - 1][j] == '1') {
						unionFind.union("r" + (i - 1) + "c" + j, "r" + i + "c" + j);
					}
					if (j - 1 >= 0 && grid[i][j - 1] == '1') {
						unionFind.union("r" + i + "c" + (j - 1), "r" + i + "c" + j);
					}
				}
			}
		}
		return unionFind.sizeMap.size();
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
							queue.offer(new int[]{r - 1, c});
						}
						if (c + 1 < col && grid[r][c + 1] == '1') {
							grid[r][c + 1] = '0';
							queue.offer(new int[]{r, c + 1});
						}
						if (r + 1 < row && grid[r + 1][c] == '1') {
							grid[r + 1][c] = '0';
							queue.offer(new int[]{r + 1, c});
						}
						if (c - 1 >= 0 && grid[r][c - 1] == '1') {
							grid[r][c - 1] = '0';
							queue.offer(new int[]{r, c - 1});
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
		if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] != '1') {
			return;
		}
		grid[row][col] = '0';
		// 右为1
		dfs(grid, row, col + 1);
		// 上为1
		dfs(grid, row - 1, col);
		// 左为1
		dfs(grid, row, col - 1);
		// 下为1
		dfs(grid, row + 1, col);
	}

	static class UnionFind {
		Map<String, String> parents;
		Map<String, Integer> sizeMap;
		UnionFind(List<String> values) {
			parents = new HashMap<>();
			sizeMap = new HashMap<>();
			for (String value : values) {
				parents.put(value, value);
				sizeMap.put(value, 1);
			}
		}

		private String find(String str) {
			Stack<String> path = new Stack<>();
			while (!str.equals(parents.get(str))) {
				path.push(str);
				str = parents.get(str);
			}
			while (!path.isEmpty()) {
				parents.put(path.pop(), str);
			}
			return str;
		}

		public void union(String str1, String str2) {
			String root1 = find(str1);
			String root2 = find(str2);
			if (!root1.equals(root2)) {
				Integer size1 = sizeMap.get(root1);
				Integer size2 = sizeMap.get(root2);
				if (size1 >= size2) {
					parents.put(root2, root1);
					sizeMap.put(root1, size1 + size2);
					sizeMap.remove(root2);
				} else {
					parents.put(root1, root2);
					sizeMap.put(root2, size1 + size2);
					sizeMap.remove(root1);
				}
			}
		}

	}
	public static void main(String[] args) {
		NumbersOfIslands solution = new NumbersOfIslands();
		char[][] grid = {
				{'1','0','0','1','1','1','0','1','1','0','0','0','0','0','0','0','0','0','0','0'},
				{'1','0','0','1','1','0','0','1','0','0','0','1','0','1','0','1','0','0','1','0'},
				{'0','0','0','1','1','1','1','0','1','0','1','1','0','0','0','0','1','0','1','0'},
				{'0','0','0','1','1','0','0','1','0','0','0','1','1','1','0','0','1','0','0','1'},
				{'0','0','0','0','0','0','0','1','1','1','0','0','0','0','0','0','0','0','0','0'},
				{'1','0','0','0','0','1','0','1','0','1','1','0','0','0','0','0','0','1','0','1'},
				{'0','0','0','1','0','0','0','1','0','1','0','1','0','1','0','1','0','1','0','1'},
				{'0','0','0','1','0','1','0','0','1','1','0','1','0','1','1','0','1','1','1','0'},
				{'0','0','0','0','1','0','0','1','1','0','0','0','0','1','0','0','0','1','0','1'},
				{'0','0','1','0','0','1','0','0','0','0','0','1','0','0','1','0','0','0','1','0'},
				{'1','0','0','1','0','0','0','0','0','0','0','1','0','0','1','0','1','0','1','0'},
				{'0','1','0','0','0','1','0','1','0','1','1','0','1','1','1','0','1','1','0','0'},
				{'1','1','0','1','0','0','0','0','1','0','0','0','0','0','0','1','0','0','0','1'},
				{'0','1','0','0','1','1','1','0','0','0','1','1','1','1','1','0','1','0','0','0'},
				{'0','0','1','1','1','0','0','0','1','1','0','0','0','1','0','1','0','0','0','0'},
				{'1','0','0','1','0','1','0','0','0','0','1','0','0','0','1','0','1','0','1','1'},
				{'1','0','1','0','0','0','0','0','0','1','0','0','0','1','0','1','0','0','0','0'},
				{'0','1','1','0','0','0','1','1','1','0','1','0','1','0','1','1','1','1','0','0'},
				{'0','1','0','0','0','0','1','1','0','0','1','0','1','0','0','1','0','0','1','1'},
				{'0','0','0','0','0','0','1','1','1','1','0','1','0','0','0','1','1','0','0','0'}
		};
		char[][] board = new char[grid.length][grid[0].length];
		for (int i = 0; i < grid.length; i++) {
			System.arraycopy(grid[i], 0, board[i], 0, grid[0].length);
		}
		int ans1 = solution.numIslands(grid);
		int ans2 = solution.numIslands2(board);
		System.out.println(ans1);
		System.out.println(ans2);
	}
}
