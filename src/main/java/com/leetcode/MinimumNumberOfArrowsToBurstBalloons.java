package com.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月11日 19:38
 * @version V1.0
 */
public class MinimumNumberOfArrowsToBurstBalloons {
	public int findMinArrowShots(int[][] points) {
		Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
		int index = 0;
		int ans = 0;
		while (index < points.length) {
			int left = points[index][0];
			int right = points[index][1];
			index++;
			while (index < points.length && right >= points[index][0]) {
				left = Math.max(left, points[index][0]);
				right = Math.min(right, points[index][1]);
				index++;
			}
			ans++;
		}
		return ans;
	}
}
