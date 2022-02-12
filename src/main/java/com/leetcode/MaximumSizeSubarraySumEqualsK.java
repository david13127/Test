package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月30日 21:50
 * @version V1.0
 */
public class MaximumSizeSubarraySumEqualsK {
	public int maxSubArrayLen(int[] nums, int k) {
		int n = nums.length;
		// 哈希表，映射前缀和值到第一次出现的下标位置
		Map<Integer, Integer> preSumIndex = new HashMap<>();
		int ans = 0;
		// 前缀和
		int preSum = 0;
		// 0 出现的位置在 -1 位置处
		preSumIndex.put(0, -1);
		for (int i = 0; i < n; ++i) {
			// 累加前缀和
			preSum += nums[i];
			// 确保记录的是第一次出现的位置
			if (!preSumIndex.containsKey(preSum)) {
				preSumIndex.put(preSum, i);
			}
			// 检查一下是否需要更新答案
			if (preSumIndex.containsKey(preSum - k)) {
				ans = Math.max(ans, i - preSumIndex.get(preSum - k));
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		MaximumSizeSubarraySumEqualsK solution = new MaximumSizeSubarraySumEqualsK();
		int[] nums = {1, 1, -1, 5, -2, 3};
		int k = 4;
		int ans = solution.maxSubArrayLen(nums, k);
		System.out.println(ans);
	}
}
