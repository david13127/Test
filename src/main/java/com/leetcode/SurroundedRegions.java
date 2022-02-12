package com.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月30日 19:06
 * @version V1.0
 */
public class SurroundedRegions {
	int[] dx = {1, -1, 0, 0};
	int[] dy = {0, 0, 1, -1};

	public void solve(char[][] board) {
		int n = board.length;
		if (n == 0) {
			return;
		}
		int m = board[0].length;
		Queue<int[]> queue = new LinkedList<int[]>();
		for (int i = 0; i < n; i++) {
			if (board[i][0] == 'O') {
				queue.offer(new int[]{i, 0});
				board[i][0] = 'A';
			}
			if (board[i][m - 1] == 'O') {
				queue.offer(new int[]{i, m - 1});
				board[i][m - 1] = 'A';
			}
		}
		for (int i = 1; i < m - 1; i++) {
			if (board[0][i] == 'O') {
				queue.offer(new int[]{0, i});
				board[0][i] = 'A';
			}
			if (board[n - 1][i] == 'O') {
				queue.offer(new int[]{n - 1, i});
				board[n - 1][i] = 'A';
			}
		}
		while (!queue.isEmpty()) {
			int[] cell = queue.poll();
			int x = cell[0], y = cell[1];
			for (int i = 0; i < 4; i++) {
				int mx = x + dx[i], my = y + dy[i];
				if (mx < 0 || my < 0 || mx >= n || my >= m || board[mx][my] != 'O') {
					continue;
				}
				queue.offer(new int[]{mx, my});
				board[mx][my] = 'A';
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] == 'A') {
					board[i][j] = 'O';
				} else if (board[i][j] == 'O') {
					board[i][j] = 'X';
				}
			}
		}
	}

	public static void main(String[] args) {
		SurroundedRegions solution = new SurroundedRegions();
		char[][] board = {
			{'O', 'O', 'O'},
			{'O', 'O', 'O'},
			{'O', 'O', 'O'}
		};
		solution.solve(board);
	}
}
