package com.mashibing.algorithm.sort;

/**
 * @Description: 计数排序
 * @author : david
 * @date Date : 2021年03月21日 23:52
 * @version V1.0
 */
public class CountSort implements Sort {
	@Override
	public void sort(int n, int[] data) {
		if (data == null || data.length < 2) {
			return;
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < data.length; i++) {
			max = Math.max(max, data[i]);
		}
		int[] bucket = new int[max + 1];
		for (int i = 0; i < data.length; i++) {
			bucket[data[i]]++;
		}
		int i = 0;
		for (int j = 0; j < bucket.length; j++) {
			while (bucket[j]-- > 0) {
				data[i++] = j;
			}
		}
	}
}
