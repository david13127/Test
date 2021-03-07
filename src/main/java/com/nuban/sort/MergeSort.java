package com.nuban.sort;

/**
 * @Description: 归并排序
 * @author : david
 * @date Date : 2021年02月10日 13:42
 * @version V1.0
 */
public class MergeSort implements Sort {
	@Override
	public void sort(int n, int[] data) {
		// mergeSort(data, 0, n - 1);
		mergeSort1(data);
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
		int loc = left; // 表示当前填了哪个数到temp
		// 两个数组都从左边开始比较，较小的写到temp，较大的再和另一组下个位置比较
		while (point1 <= mid && point2 <= right) {
			if (data[point1] <= data[point2]) {
				temp[loc] = data[point1];
				loc++;
				point1++;
			} else {
				temp[loc] = data[point2];
				loc++;
				point2++;
			}
		}
		// 如果右边的都比左边的小
		// 5 6 8 9    0 1 3 4
		while (point1 <= mid) {
			temp[loc++] = data[point1++];
		}
		// 如果左边的都比右边的小
		// 0 1 3 4    5 6 8 9
		while (point2 <= right) {
			data[loc++] = data[point2++];
		}
		// 回填
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
}
