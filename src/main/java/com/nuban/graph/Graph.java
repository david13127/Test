package com.nuban.graph;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2021年02月11日 9:56
 * @version V1.0
 */
public class Graph {
	private int n; // 行
	private int m; // 列

	// 目标坐标
	private int dx;
	private int dy;

	private int[][] data; // 邻接矩阵
	private boolean[][] mark;

	private int minStep = Integer.MAX_VALUE;

	public Graph(int n, int m, int dx, int dy, int[][] data, boolean[][] mark) {
		this.n = n;
		this.m = m;
		this.dx = dx;
		this.dy = dy;
		this.data = data;
		this.mark = mark;
	}

	public void bfs(int startX, int startY) {
		mark[startX][startY] = true;
		Queue<Point> queue = new ArrayBlockingQueue<>(n * m);
		Point start = new Point(startX, startY);
		queue.add(start);
		// 右下左上移动
		int[][] next = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		while (!queue.isEmpty()) {
			// 判断前后左右
			Point curPoint = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nextX = curPoint.getX() + next[i][0];
				int nextY = curPoint.getY() + next[i][1];
				// 判断是否可以走
				if (nextX < 0 || nextX > n - 1 || nextY < 0 || nextY > m - 1) {
					continue;
				}
				if (!mark[nextX][nextY] && data[nextX][nextY] == 0) {
					if (nextX == dx && nextY == dy) {
						System.out.println("true");
						return;
					}
					queue.add(new Point(nextX, nextY));
					mark[nextX][nextY] = true;
					System.out.println("下一个点：" + nextX + "," + nextY);
				}
			}
		}
		System.out.println("false");
	}

	public void dfs(int startX, int startY, int step) {
		// 右下左上移动
		int[][] next = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		if (startX == dx && startY == dy) {
			if (step < minStep) {
				minStep = step;
			}
			return;
		}
		for (int i = 0; i < 4; i++) {
			int nextX = startX + next[i][0];
			int nextY = startY + next[i][1];
			if (nextX < 0 || nextX > n - 1 || nextY < 0 || nextY > m - 1) {
				continue;
			}
			if (data[nextX][nextY] == 0 && !mark[nextX][nextY]) {
				mark[nextX][nextY] = true;
				dfs(nextX, nextY, step + 1);
				mark[nextX][nextY] = false;
			}
		}
	}
	public static void main(String[] args) {
		int n = 5;
		int m = 4;
		int[][] data = {
			{0, 0, 1, 0},
			{0, 0, 0, 0},
			{0, 0, 1, 0},
			{0, 1, 0, 0},
			{0, 0, 0, 1}
		};
		int dx = 3;
		int dy = 2;
		boolean[][] mark = new boolean[n][m];
		Graph graph = new Graph(n, m, dx, dy, data, mark);
		graph.dfs(4, 1, 0);
		System.out.println(graph.minStep);
		graph.bfs(4, 1);
	}
}

class Point {
	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}