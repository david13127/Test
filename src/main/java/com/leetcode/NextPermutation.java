package com.leetcode;

import java.util.Arrays;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月13日 17:40
 * @version V1.0
 */
public class NextPermutation {
	public void nextPermutation(int[] nums) {
		if (nums.length == 1) {
			return;
		}
		int j = nums.length - 1;
		int i = j - 1;
		while (i > 0 && nums[i] >= nums[j]) {
			i--;
			j--;
		}
		if (i == 0 && nums[i] > nums[j]) {
			Arrays.sort(nums);
		} else {
			j = nums.length - 1;
			while (i < j && nums[i] >= nums[j]) {
				j--;
			}
			swap(nums, i, j);
			Arrays.sort(nums, i + 1, nums.length);
		}
	}

	private void swap(int[] nums, int i, int j) {
		if (i != j) {
			nums[i] = nums[i] + nums[j];
			nums[j] = nums[i] - nums[j];
			nums[i] = nums[i] - nums[j];
		}
	}
}
