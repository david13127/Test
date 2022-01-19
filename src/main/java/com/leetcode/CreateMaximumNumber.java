package com.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

/**
 * 	作者：LeetCode-Solution
 * 	链接：https://leetcode-cn.com/problems/create-maximum-number/solution/pin-jie-zui-da-shu-by-leetcode-solution/
 * 	来源：力扣（LeetCode）
 * 	著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class CreateMaximumNumber {
	public int[] maxNumber(int[] nums1, int[] nums2, int k) {
		int m = nums1.length;
		int n = nums2.length;
		int[] maxSubsequence = new int[k];
		int start = Math.max(0, k - n);
		int end = Math.min(k, m);
		for (int i = start; i <= end; i++) {
			int[] subsequence1 = maxSubsequence(nums1, i);
			int[] subsequence2 = maxSubsequence(nums2, k - i);
			int[] curMaxSubsequence = merge(subsequence1, subsequence2);
			if (compare(curMaxSubsequence, 0, maxSubsequence, 0) > 0) {
				System.arraycopy(curMaxSubsequence, 0, maxSubsequence, 0, k);
			}
		}
		return maxSubsequence;
	}

	public int compare(int[] subsequence1, int index1, int[] subsequence2, int index2) {
		int x = subsequence1.length, y = subsequence2.length;
		while (index1 < x && index2 < y) {
			int difference = subsequence1[index1] - subsequence2[index2];
			if (difference != 0) {
				return difference;
			}
			index1++;
			index2++;
		}
		return (x - index1) - (y - index2);
	}

	public int[] merge(int[] nums1, int[] nums2) {
		if (nums1.length == 0) {
			return nums2;
		}
		if (nums2.length == 0) {
			return nums1;
		}
		Deque<Integer> stack = new ArrayDeque<>();
		int i = 0;
		int j = 0;
		while (i < nums1.length && j < nums2.length) {
			if (compareBit(nums1, nums2, i, j)) {
				stack.push(nums1[i]);
				i++;
			} else {
				stack.push(nums2[j]);
				j++;
			}
		}
		if (i < nums1.length) {
			for (int k = i; k < nums1.length; k++) {
				stack.push(nums1[k]);
			}
		}
		if (j < nums2.length) {
			for (int k = j; k < nums2.length; k++) {
				stack.push(nums2[k]);
			}
		}
		int size = stack.size();
		int[] ans = new int[size];
		for (int l = 0; l < size; l++) {
			ans[size - l - 1] = stack.pop();
		}
		return ans;
	}

	public int[] maxSubsequence(int[] nums, int k) {
		int length = nums.length;
		int[] stack = new int[k];
		int top = -1;
		int remain = length - k;
		for (int i = 0; i < length; i++) {
			int num = nums[i];
			while (top >= 0 && stack[top] < num && remain > 0) {
				top--;
				remain--;
			}
			if (top < k - 1) {
				stack[++top] = num;
			} else {
				remain--;
			}
		}
		return stack;
	}

	private boolean compareBit(int[] nums1, int[] nums2, int i, int j) {
		if (i <= nums1.length - 1 && j <= nums2.length - 1) {
			if (nums1[i] > nums2[j]) {
				return true;
			} else if (nums1[i] < nums2[j]) {
				return false;
			} else {
				return compareBit(nums1, nums2, i + 1, j + 1);
			}
		} else {
			return i != nums1.length;
		}
	}

	public static void main(String[] args) {
		int[] nums1 = {3, 4, 6, 5};
		int[] nums2 = {9, 1, 2, 5, 8, 3};
		int k = 5;
		CreateMaximumNumber solution = new CreateMaximumNumber();
		int[] ints = solution.maxNumber(nums1, nums2, k);
		String sout = Arrays.stream(ints).mapToObj(e -> e + "").collect(Collectors.joining(","));
		System.out.println(sout);
	}
}
