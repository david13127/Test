package com.nuban.graph;

import java.util.Scanner;

/**
 * @Description: 迪杰斯特拉
 * @author : david
 * @date Date : 2021年02月12日 11:19
 * @version V1.0
 */
public class Dijkstra {
	public static void main(String[] args) {
		int n, m, x; // n：点的个数, m：边的数量, x：要求的最短路径的点
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		x = in.nextInt();

		int[][] value = new int[n][n]; // n个点之间的权重
		int[] dis = new int[n]; // 存从x到各点的最短路径
		for (int i = 0; i < n; i++) {
			dis[i] = Integer.MAX_VALUE;
			for (int j = 0; j < n; j++) {
				value[i][j] = Integer.MAX_VALUE;
				if (i == j) { // 是自己
					value[i][j] = 0;
				} else {
					value[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		for (int i = 0; i < m; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int weight = in.nextInt();
			value[a][b] = weight;
			// 计算第一个点
			if (a == x) {
				dis[b] = weight;
			}
		}
		//输入
		//6 8
		//0
		//0 5 100
		//0 4 30
		//0 2 10
		//1 2 5
		//2 3 50
		//3 5 10
		//4 3 20
		//4 5 60
		dijkstra(x, dis, value, n);
	}

	public static void dijkstra(int x, int[] dis, int[][] value, int n) {
		boolean[] mark = new boolean[n]; // 标记是否加过了
		// 第一个点
		mark[x] = true;
		dis[x] = 0;
		while (true) {
			// 找离x最近的点
			int min = Integer.MAX_VALUE;
			int loc = -1; // 最小的点
			for (int i = 0; i < n; i++) {
				if (dis[i] < min && !mark[i]) {
					min = dis[i];
					loc = i;
				}
			}
			if (loc != -1) {
				mark[loc] = true;
				for (int i = 0; i < n; i++) {
					if (!mark[i] && value[loc][i] < Integer.MAX_VALUE) {
						if (dis[loc] + value[loc][i] < dis[i]) {
							dis[i] = dis[loc] + value[loc][i];
						}
					}
				}

			} else {
				break;
			}
		}
		System.out.println(x + "点到每个点的最短路径如下：");
		for (int i = 0; i < n; i++) {
			if (dis[i] != Integer.MAX_VALUE) {
				System.out.println("到" + i + "点的最短距离为：" + dis[i]);
			} else {
				System.out.println("不能到达" + i);
			}
		}
	}
}
