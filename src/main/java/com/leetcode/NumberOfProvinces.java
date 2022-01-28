package com.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月28日 21:34
 * @version V1.0
 */
public class NumberOfProvinces {
	public int findCircleNum(int[][] isConnected) {
		return useBfs(isConnected);
	}

	private int useBfs(int[][] isConnected) {
		int cities = isConnected.length;
		boolean[] visted = new boolean[cities];
		int ans = 0;
		for (int i = 0; i < cities; i++) {
			if (!visted[i]) {
				Queue<Integer> queue = new LinkedList<>();
				queue.offer(i);
				ans++;
				while (!queue.isEmpty()) {
					int poll = queue.poll();
					visted[i] = true;
					for (int j = 0; j < cities; j++) {
						if (!visted[j] && isConnected[poll][j] == 1) {
							queue.offer(j);
							visted[j] = true;
						}
					}
				}
			}
		}
		return ans;
	}

	public int useDfs(int[][] isConnected) {
		int cities = isConnected.length;
		boolean[] visted = new boolean[cities];
		int ans = 0;
		for (int i = 0; i < cities; i++) {
			if (!visted[i]) {
				dfs(isConnected, i, visted);
				ans++;
			}
		}
		return ans;
	}

	private void dfs(int[][] isConnected, int i, boolean[] visted) {
		if (!visted[i]) {
			visted[i] = true;
			int[] connectedCities = isConnected[i];
			for (int j = 0; j < connectedCities.length; j++) {
				if (connectedCities[j] == 1) {
					dfs(isConnected, j, visted);
				}
			}
		}
	}

	public static void main(String[] args) {
		int[][] isConnected = new int[][]{{1,0,0,1}, {0,1,1,0}, {0,1,1,0}, {1,0,1,1}};
		NumberOfProvinces solution = new NumberOfProvinces();
		int ans = solution.findCircleNum(isConnected);
		System.out.println(ans);
	}
}
