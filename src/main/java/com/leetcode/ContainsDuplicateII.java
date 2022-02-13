package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月13日 20:01
 * @version V1.0
 */
public class ContainsDuplicateII {
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		int length = nums.length;
		for (int i = 0; i < length; i++) {
			int num = nums[i];
			if (map.containsKey(num) && i - map.get(num) <= k) {
				return true;
			}
			map.put(num, i);
		}
		return false;
	}
}
