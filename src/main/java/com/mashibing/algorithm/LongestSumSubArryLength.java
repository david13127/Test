package com.mashibing.algorithm;


import java.util.HashMap;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2021年02月15日 13:36
 * @version V1.0
 */
public class LongestSumSubArryLength {
	public static int maxLength(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, -1); // 重要
		int len = 0;
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if (map.containsKey(sum - k)) {
				len = Math.max(i - map.get(sum - k), len);
			}
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}
		return len;
	}
}
