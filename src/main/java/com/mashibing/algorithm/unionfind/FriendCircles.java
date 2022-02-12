package com.mashibing.algorithm.unionfind;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月12日 19:01
 * @version V1.0
 */
public class FriendCircles {
	public int findCircleNums(int[][] relations) {
		int n = relations.length;
		List<Integer> values = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			values.add(i);
		}
		UnionFindForMap<Integer> unionFind1 = new UnionFindForMap<Integer>(values);
		UnionFindForArray unionFind2 = new UnionFindForArray(n);
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (relations[i][j] == 1) {
					unionFind1.union(i, j);
					unionFind2.union(i, j);
				}
			}
		}
		return unionFind1.sizeMap.size();
	}
}
