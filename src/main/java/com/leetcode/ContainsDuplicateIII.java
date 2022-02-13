package com.leetcode;

import java.util.TreeSet;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月13日 20:16
 * @version V1.0
 */
public class ContainsDuplicateIII {
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		int n = nums.length;
		TreeSet<Long> treeSet = new TreeSet<>();
		for (int i = 0; i < n; i++) {
			Long num = (long) nums[i];
			// 从 ts 中找到小于等于 num 的最大值（小于等于 num 的最接近 num 的数）
			Long floor = treeSet.floor(num);
			// 从 ts 中找到大于等于 num 的最小值（大于等于 num 的最接近 num 的数）
			Long ceiling = treeSet.ceiling(num);
			if(floor != null && num - floor <= t) {
				return true;
			}
			if(ceiling != null && ceiling - num <= t) {
				return true;
			}
			// 将当前数加到 ts 中，并移除下标范围不在 [max(0, i - k), i) 的数（维持滑动窗口大小为 k）
			treeSet.add(num);
			if (i >= k) {
				treeSet.remove((long) nums[i - k]);
			};
		}
		return false;
	}

	public boolean containsNearbyAlmostDuplicateBao(int[] nums, int k, int t) {
		long[] nums1 = new long[nums.length];
		for (int i = 0; i < nums.length; i++) {
			nums1[i] = nums[i];
		}
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j <= i + k; j++) {
				if (Math.abs(nums1[i] - nums[j]) <= t) {
					return true;
				}
			}
		}
		return false;
	}
}
