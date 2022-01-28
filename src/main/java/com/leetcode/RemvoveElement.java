package com.leetcode;

import java.util.Arrays;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月27日 21:40
 * @version V1.0
 */
public class RemvoveElement {
	public int removeElement(int[] nums, int val) {
		int n = nums.length;
		int left = 0;
		for (int right = 0; right < n; right++) {
			if (nums[right] != val) {
				nums[left] = nums[right];
				left++;
			}
		}
		return left;
	}
}
