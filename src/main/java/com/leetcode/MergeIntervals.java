package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月17日 23:14
 * @version V1.0
 */
public class MergeIntervals {
	public int[][] merge(int[][] intervals) {
		if (intervals.length == 0) {
			return new int[0][2];
		}
		Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
		List<int[]> merged = new ArrayList<int[]>();
		for (int i = 0; i < intervals.length; ++i) {
			int L = intervals[i][0], R = intervals[i][1];
			if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
				merged.add(new int[]{L, R});
			} else {
				merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
			}
		}
		return merged.toArray(new int[merged.size()][]);
	}
}
