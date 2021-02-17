package com.nuban.sort;

/**
 * @Description: 排序算法接口
 * @author : david
 * @date Date : 2021年02月09日 21:07
 * @version V1.0
 */
public interface Sort {
	int[] SORT_DATA = {2, 37, 49, 21, 20, 3, 4, 11, 6, 35, 40, 7, 14, 13, 41, 1};
	void sort(int n, int[] data);
	default void swap(int[] data, int a, int b) {
		if (a != b) {
			// 交换
			// int temp = data[j];
			// data[j] = data[j - 1];
			// data[j - 1] = temp;
			data[a] = data[a] + data[b];
			data[b] = data[a] - data[b];
			data[a] = data[a] - data[b];
		}
	}
}
