package com.leetcode;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月23日 19:09
 * @version V1.0
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
	public int[] searchRange(int[] nums, int target) {
		int length = nums.length;
		if (length == 0) {
			return new int[]{-1, -1};
		}
		int firstPosition = findFirstPosition(nums, target);
		if (firstPosition == -1) {
			return new int[]{-1, -1};
		}
		int lastPosition = findLastPosition(nums, target);
		return new int[]{firstPosition, lastPosition};
	}

	public int findFirstPosition(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		while (left < right) {
			int mid = (left + right) >>> 1;
			if (nums[mid] < target) {
				left = mid + 1;
			} else if (nums[mid] == target) {
				right = mid;
			} else {
				right = mid - 1;
			}
		}
		if (nums[left] == target) {
			return left;
		}
		return -1;
	}

	public int findLastPosition(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		while (left < right) {
			int mid = (left + right + 1) >>> 1;
			if (nums[mid] < target) {
				left = mid + 1;
			} else if (nums[mid] == target) {
				left = mid;
			} else {
				right = mid - 1;
			}
		}
		return left;
	}

	public static void main(String[] args) {
		FindFirstAndLastPositionOfElementInSortedArray solution = new FindFirstAndLastPositionOfElementInSortedArray();
		int[] nums = {5, 7, 7, 8, 8, 10};
		int target = 8;
		int[] ans = solution.searchRange(nums, target);
		System.out.println(ans[0] + "," + ans[1]);
	}
}
