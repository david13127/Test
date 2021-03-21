package com.mashibing.algorithm.sort.practice;

import com.mashibing.algorithm.sort.Sort;

import java.util.Arrays;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2021年02月15日 21:21
 * @version V1.0
 */
public class MergeSortTest implements Sort {
	@Override
	public void sort(int n, int[] data) {
		mergeSort(data, 0, n - 1);
	}

	private void mergeSort(int[] data, int left, int right) {
		if (left == right) {
			return;
		}
		int mid = left + ((right - left) >> 1);
		mergeSort(data, left, mid);
		mergeSort(data, mid + 1, right);
		merge(data, left, mid, right);
	}

	private void merge(int[] data, int left, int mid, int right) {
		int[] temp = new int[data.length];
		int point1 = left;
		int point2 = mid + 1;
		int loc = left;
		while (point1 <= mid && point2 <= right) {
			if (data[point1] < data[point2]) {
				temp[loc++] = data[point1++];
			} else {
				temp[loc++] = data[point2++];
			}
		}
		while (point1 <= mid) {
			temp[loc++] = data[point1++];
		}
		while (point2 <= right) {
			temp[loc++] = data[point2++];
		}
		for (int i = left; i <= right; i++) {
			data[i] = temp[i];
		}
	}

	private void mergeSort1(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int n = arr.length;
		int mergeSize = 1; // 步长
		while (mergeSize < n) {
			int l = 0; // 当前左组的第一个数的位置
			while (l < n) {
				int m = l + mergeSize - 1; // 中间位置
				if (m >= n) {
					break;
				}
				int r = Math.min(m + mergeSize, n - 1); // 当前右组的第一个数的位置
				merge(arr, l, m, r);
				l = r + 1;
			}
			// 防止溢出
			if (mergeSize > n / 2) {
				break;
			}
			mergeSize *= 2;
		}
	}
	public static void main(String[] args) {
		Sort sorter = new MergeSortTest();
		sorter.sort(SORT_DATA.length, SORT_DATA);
		System.out.println(Arrays.toString(SORT_DATA));
	}
}
