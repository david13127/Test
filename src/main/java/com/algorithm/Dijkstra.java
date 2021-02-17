package com.algorithm;

/**
 * @Description: 迪杰斯特拉最短路径算法
 * @author : david
 * @date Date : 2020年04月10日 7:04
 * @version V1.0
 */
public class Dijkstra {
	private static final int N = 100; // 城市的个数
	private static final int INF = Integer.MAX_VALUE; // 最大值
	private static int[][] map = new int[N][N]; // 邻接矩阵
	private static int[] dist = new int[N]; // 最短路径数据
	private static int[] p = new int[N]; // 前驱数组
	private static int n; // 实际城市个数
	private static boolean[] flag = new boolean[N]; // 如果flag为true,说明顶点i已经加入到集合S,否则顶点属于V-S

	private static void solve(int u) {
		for (int i = 1; i <= n; i++) {
			dist[i] = map[u][i]; // 初始化原点u到其他各个顶点的最短路径长度
			flag[i] = false;
			p[i] = dist[i] == INF ? -1 : u; // 无穷大说明不相连, 相连则前驱设置为u
		}
		dist[u] = 0;
		flag[u] = true; // 初始化时，集合S只有一个元素u
		for (int i = 1; i <= n; i++) {
			int temp = INF;
			int t = u;
			//在集合V-S中寻找到u最近的顶点t
			for (int j = 1; j <= n; j++) {
				if (!flag[j] && dist[j] < temp) {
					t = j;
					temp = dist[j];
				}
			}
			if (t == u) {
				return; // 找不到t,跳出循环
			}
			flag[t] = true; // 找到，将t加入集合
			// 更新集合V-S中与t邻接的顶点到u的距离
			for (int j = 1; j <= n; j++) {
				if (!flag[j] && map[t][j] < INF) {
					if (dist[j] > (dist[t] + map[t][j])) {
						dist[j] = dist[t] + map[t][j];
						p[j] = t;
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
		int beg = 1;
		solve(beg);
		for (int i = 1; i <= 5; i++) {
			System.out.println("小明从" + beg + "去" + i);
			if (dist[i] == INF) {
				System.out.println("去不了");
			}
			else {
				System.out.println("最短路径为：" + dist[i] + ", 前驱：" + p[i]);
			}
		}
	}
}
