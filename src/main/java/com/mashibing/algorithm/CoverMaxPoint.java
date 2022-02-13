package com.mashibing.algorithm;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月13日 12:39
 * @version V1.0
 */
public class CoverMaxPoint {
	public int coverMaxPoint(int[] arr, int k) {
		if (arr == null || arr.length == 0 || k == 0) {
			return 0;
		}
		int left = 0;
		int right = 0;
		int ans = 1;
		while (left <= right && right < arr.length) {
			while (right < arr.length && arr[right] - arr[left] <= k) {
				right++;
			}
			ans = Math.max(ans, right - left);
			left++;
		}
		return ans;
	}

	public static void main(String[] args) {
		CoverMaxPoint solution = new CoverMaxPoint();
		int[] arr = {1, 3, 4, 6, 9, 12, 13, 15};
		int k = 5;
		int ans = solution.coverMaxPoint(arr, k);
		System.out.println(ans);
	}
}
