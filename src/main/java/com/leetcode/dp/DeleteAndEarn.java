package com.leetcode.dp;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月25日 21:56
 * @version V1.0
 */
public class DeleteAndEarn {
	public int deleteAndEarn(int[] nums) {
		int max = 0;
		for (int num : nums) {
			max = Math.max(max, num);
		}
		int[] sum = new int[max + 1];
		for (int num : nums) {
			sum[num] += num;
		}
		return rob(sum);
	}

	public int rob(int[] nums) {
		int len = nums.length;
		int first = nums[0];
		int second = Math.max(nums[0], nums[1]);
		for (int i = 2; i < len; i++) {
			int temp = second;
			second = Math.max(first + nums[i], second);
			first = temp;
		}
		return second;
	}
}
