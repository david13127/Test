package com.mashibing.algorithm.merge;

/**
 * @Description: 一个数组中，计算所有比自己右边的数的2倍都大的数的个数
 * @author : david
 * @date Date : 2021年03月07日 11:57
 * @version V1.0
 */
public class BiggerThanRightTwice {
	public static int biggerThanRightTwice(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		return process(arr, 0, arr.length - 1);
	}

	private static int process(int[] arr, int l, int r) {
		if (l == r) {
			return 0;
		}
		int mid = l + ((r - l) >> 1);
		return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
	}

	private static int merge(int[] arr, int l, int mid, int r) {
		int res = 0;
		int windowR = mid + 1;
		for (int i = l; i <= mid; i++) {
			while (windowR <= r && arr[i] > (arr[windowR] * 2)) {
				windowR++;
			}
			res += windowR - (mid + 1);
		}
		int[] help = new int[r - l + 1];
		int i = 0;
		int p1 = l;
		int p2 = mid + 1;
		while (p1 <= mid && p2 <= r) {
			help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
		}
		while (p1 <= mid) {
			help[i++] = arr[p1++];
		}
		while (p2 <= r) {
			help[i++] = arr[p2++];
		}
		for (int j = 0; j < help.length; j++) {
			arr[l + j] = help[j];
		}
		return res;
	}

	public static void main(String[] args) {
		int[] arr = {2, 6, 7, 3, 8, 1};
		int ans = biggerThanRightTwice(arr);
		System.out.println(ans);
	}
}
