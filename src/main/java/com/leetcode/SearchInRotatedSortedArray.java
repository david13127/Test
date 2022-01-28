package com.leetcode;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月23日 19:44
 * @version V1.0
 */
public class SearchInRotatedSortedArray {
	public int search(int[] nums ,int target) {
		int left = 0;
		int right = nums.length - 1;
		while (left <= right) {
			int mid = (left + right) >>> 1;
			if (nums[mid] == target) {
				return mid;
			}
			// 左侧有序
			if (nums[left] <= nums[mid]) {
				if (nums[left] <= target && target < nums[mid]) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			} else {
				if (nums[mid] < target && target <= nums[right]) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		SearchInRotatedSortedArray solution = new SearchInRotatedSortedArray();
		int[] nums = new int[] {1};
		int target = 1;
		int ans = solution.search(nums, target);
		System.out.println(ans);
	}
}
