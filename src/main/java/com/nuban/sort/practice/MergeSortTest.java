package com.nuban.sort.practice;

import com.nuban.sort.Sort;

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
		if (left < right) {
			int mid = (left + right) >> 1;
			mergeSort(data, left, mid);
			mergeSort(data, mid + 1, right);
			merge(data, left, mid, right);
		}
	}

	private void merge(int[] data, int left, int mid, int right) {
		int[] temp = new int[data.length];
		int point1 = left;
		int point2 = mid + 1;
		int loc = left;
		while (point1 <= mid && point1 <= right) {
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

	public static void main(String[] args) {
		Sort sorter = new MergeSortTest();
		sorter.sort(SORT_DATA.length, SORT_DATA);
		System.out.println(Arrays.toString(SORT_DATA));
	}
}
