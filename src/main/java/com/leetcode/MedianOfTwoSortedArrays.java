package com.leetcode;

/**
 * @Description: 寻找两个正序数组的中位数
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 * @author : david
 * @date Date : 2021年03月13日 11:09
 * @version V1.0
 */
public class MedianOfTwoSortedArrays {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		if ((nums1 == null || nums1.length == 0) && (nums2 == null || nums2.length == 0)) {
			return (double) 0;
		}
		int len1 = nums1 == null ? 0 : nums1.length;
		int len2 = nums2 == null ? 0 : nums2.length;
		int[] sums = new int[len1 + len2];
		if (nums1 != null) {
			for (int i = 0; i < len1; i++) {
				sums[i] = nums1[i];
			}
		}
		if (nums2 != null) {
			for (int i = 0; i < len2; i++) {
				sums[i + len1] = nums2[i];
			}
		}
		merge(sums, len1 - 1, len1 + len2);
		int mid1 = (len1 + len2) % 2 == 0 ? (len1 + len2) / 2 - 1 : (len1 + len2) / 2;
		int mid2 = (len1 + len2) / 2;
		return (double) (sums[mid1] + sums[mid2]) / 2;
	}

	private void merge(int[] arr, int mid, int r) {
		int[] help = new int[r];
		int i = 0;
		int p1 = 0;
		int p2 = mid + 1;
		while (p1 <= mid && p2 < r) {
			help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
		}
		while (p1 <= mid) {
			help[i++] = arr[p1++];
		}
		while (p2 < r) {
			help[i++] = arr[p2++];
		}
		for (int j = 0; j < help.length; j++) {
			arr[j] = help[j];
		}
	}

	public static void main(String[] args) {
		int[] nums1 = {1, 3};
		int[] nums2 = {2, 4};
		MedianOfTwoSortedArrays solution = new MedianOfTwoSortedArrays();
		System.out.println(solution.findMedianSortedArrays(nums1, nums2));
	}
}
