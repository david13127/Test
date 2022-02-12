package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月09日 21:40
 * @version V1.0
 */
public class InsertInterval {
	public int[][] insert(int[][] intervals, int[] newInterval) {
		int left = newInterval[0];
		int right = newInterval[1];
		List<int[]> ans = new ArrayList<>();
		boolean placed = false;
		for(int[] interval : intervals) {
			int l = interval[0];
			int r = interval[1];
			if (l > right) {
				if (!placed) {
					ans.add(new int[]{left, right});
					placed = true;
				}
				ans.add(interval);
			}
			else if (r < left) {
				ans.add(interval);
			} else {
				left = Math.min(left, l);
				right = Math.max(right, r);
			}
		}
		if (!placed) {
			ans.add(new int[]{left, right});
		}
		int[][] res = new int[ans.size()][2];
		for (int i = 0; i < ans.size(); i++) {
			res[i] = ans.get(i);
		}
		return res;
	}
}
