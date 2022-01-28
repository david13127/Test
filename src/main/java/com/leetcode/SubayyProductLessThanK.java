package com.leetcode;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月27日 21:58
 * @version V1.0
 */
public class SubayyProductLessThanK {
	public int numSubarrayProductLessThanK(int[] nums, int k) {
		if (k == 0) {
			return 0;
		}
		double logk = Math.log(k);
		double[] prefix = new double[nums.length + 1];
		for (int i = 0; i < nums.length; i++) {
			prefix[i + 1] = prefix[i] + Math.log(nums[i]);
		}
		int ans = 0;
		for (int i = 0; i < prefix.length; i++) {
			int lo = i + 1, hi = prefix.length;
			while (lo < hi) {
				int mi = lo + (hi - lo) / 2;
				if (prefix[mi] < prefix[i] + logk - 1e-9) {
					lo = mi + 1;
				} else {
					hi = mi;
				}
			}
			ans += lo - i - 1;
		}
		return ans;
	}

	public int numSubarrayProductLessThanK1(int[] nums, int k) {
		if (k <= 1) {
			return 0;
		}
		int prod = 1;
		int ans = 0;
		int left = 0;
		for (int right = 0; right < nums.length; right++) {
			prod *= nums[right];
			while (prod >= k) {
				prod /= nums[left];
				left++;
			}
			ans += right - left + 1;
		}
		return ans;
	}

	public static void main(String[] args) {
		SubayyProductLessThanK solution = new SubayyProductLessThanK();
		int[] nums = {10, 5, 2, 6};
		int k = 100;
		int ans = solution.numSubarrayProductLessThanK1(nums, k);
		System.out.println(ans);
	}
}
