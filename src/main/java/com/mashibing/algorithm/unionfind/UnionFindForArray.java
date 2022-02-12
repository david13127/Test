package com.mashibing.algorithm.unionfind;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月12日 19:10
 * @version V1.0
 */
public class UnionFindForArray {
	// parent[i] = k i的父亲就是k
	int[] parent;
	// size[i] = k, 如果i是代表节点, size[i]才有意义, 表示i所在集合大小
	int[] size;
	// 辅助结构
	int[] help;
	// 一共多少集合
	int sets;

	public UnionFindForArray(int n) {
		parent = new int[n];
		size = new int[n];
		help = new int[n];
		sets = n;
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			size[i] = 1;
		}
	}

	private int find(int i) {
		int hi = 0;
		while (i != parent[i]) {
			help[hi++] = i;
			i = parent[i];
		}
		for (hi--; hi >= 0; hi--) {
			parent[help[hi]] = i;
		}
		return i;
	}

	public void union(int a, int b) {
		int aHead = find(a);
		int bHead = find(b);
		if (aHead != bHead) {
			if (size[aHead] >= size[bHead]) {
				size[aHead] += size[bHead];
				parent[bHead] = aHead;
			} else {
				size[bHead] += size[aHead];
				parent[aHead] = bHead;
			}
			sets--;
		}
	}
}
