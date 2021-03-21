package com.mashibing.algorithm.sort;

import java.util.Arrays;

/**
 * @Description: 堆排序
 * @author : david
 * @date Date : 2021年03月07日 22:10
 * @version V1.0
 */
public class HeapSort implements Sort {
	@Override
	public void sort(int n, int[] data) {
		heapSort(data);
	}

	private void heapSort(int[] arr) {
		// O(N*logN)
		// for (int i = 0; i < arr.length; i++) {
		// 	heapInsert(arr, i);
		// }
		// O(N)
		for (int i = arr.length; i >= 0; i--) {
			heapify(arr, i, arr.length);
		}
		int heapSize = arr.length;
		swap(arr, 0, --heapSize);
		while (heapSize > 0) {
			heapify(arr, 0, heapSize);
			swap(arr, 0, --heapSize);
		}
	}

	// 新加进来的数，停在了index位置，请依次往上移动
	// 移动到0位置，或者干不掉自己的父节点了，停
	private void heapInsert(int[] arr, int index) {
		// 终止条件 index = 0 也包含了
		while (arr[index] > arr[(index - 1) / 2]) {
			swap(arr, index, (index - 1) / 2);
			index = (index - 1) / 2;
		}
	}

	// 从index位置，往下看，不断下沉
	// 停：较大的孩子都不再比index位置的数大，或者已经没有孩子了
	private void heapify(int[] arr, int index, int heapSize) {
		int left = index * 2 + 1; // 左孩子位置
		int right = left + 1; // 右孩子位置 index * 2 + 2
		while (left < heapSize) {
			int largest = right < heapSize && arr[right] > arr[left] ? right : left; // 找左右孩子中最大的
			int maxIndex = arr[largest] > arr[index] ? largest : index; // 和父pk
			if (maxIndex == index) {
				break;
			}
			swap(arr, largest, index);
			index = largest;
			left = index * 2 + 1;
		}
	}

	public static void main(String[] args) {
		Sort sorter = new HeapSort();
		sorter.sort(SORT_DATA.length, SORT_DATA);
		System.out.println(Arrays.toString(SORT_DATA));
	}
}
