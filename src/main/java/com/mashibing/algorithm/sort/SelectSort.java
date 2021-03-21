package com.mashibing.algorithm.sort;

/**
 * @Description: 选择排序
 * @author : david
 * @date Date : 2021年02月09日 21:47
 * @version V1.0
 */
public class SelectSort implements Sort {
	@Override
	public void sort(int n, int[] data) {
		for (int i = 0; i < n; i++) {
			int min = i;
			for (int j = i; j < n; j++) {
				if (data[j] <= data[min]) {
					min = j;
				}
			}
			swap(data, i, min);
		}
	}
}
