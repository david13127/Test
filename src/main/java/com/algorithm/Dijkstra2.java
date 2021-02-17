package com.algorithm;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Description: 迪杰斯特拉最短路径算法，优先队列
 * @author : david
 * @date Date : 2020年04月11日 10:43
 * @version V1.0
 */
public class Dijkstra2 {
	private static final int N = 100; // 城市的个数
	private static final int INF = Integer.MAX_VALUE; // 最大值
	private static int[][] map = new int[N][N]; // 邻接矩阵
	private static int[] dist = new int[N]; // 最短路径数据
	private static boolean[] flag = new boolean[N]; // 如果flag为true,说明顶点i已经加入到集合S,否则顶点属于V-S
	private static int n; // 实际城市个数

	private static void solve(int st) {
		Queue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(st, 0));
		for (int i = 1; i <= n; i++) {
			dist[i] = INF;
		}
		dist[st] = 0;
		while (!queue.isEmpty()) {
			Node it = queue.poll();
			int t = it.u;
			if (flag[t]) {
				continue;
			}
			flag[t] = true;
			for (int i = 1; i <= n; i++) {
				if (!flag[i] && map[t][i] < INF) {
					if (dist[i] > dist[t] + map[t][i]) {
						dist[i] = dist[t] + map[t][i];
						queue.add(new Node(i, dist[i]));
					}
				}
			}
		}
	}

	private static void init() {
		n = 5;
		map = new int[][]{
				{0, 0,   0,   0,   0,   0  },
				{0, INF, 2,   5,   INF, INF},
				{0, INF, INF, 2,   6,   INF},
				{0, INF, INF, INF, 7,   1  },
				{0, INF, INF, 2,   INF, 4  },
				{0, INF, INF, INF, INF, INF}
		};
	}

	public static void main(String[] args) {
		init();
		int beg = 2;
		solve(beg);
		for (int i = 1; i <= 5; i++) {
			System.out.println("小明从" + beg + "去" + i);
			if (dist[i] == INF) {
				System.out.println("去不了");
			} else {
				System.out.println("最短路径为：" + dist[i]);
			}
		}
	}
}


class Node implements Comparable<Node> {
	int u;
	private int step;
	Node(int a, int sp) {
		u = a;
		step = sp;
	}

	@Override
	public int compareTo(Node o) {
		return o.step > step ? -1 : 1;
	}
}