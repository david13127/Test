package com.mashibing.algorithm.sort.practice;

import com.mashibing.algorithm.sort.Sort;

import java.util.Arrays;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2021年02月10日 10:40
 * @version V1.0
 */
public class SelectSortTest implements Sort {
	@Override
	public void sort(int n, int[] data) {
		for (int i = 0; i < n; i++) {
			int min = i;
			for (int j = i; j < n; j++) {
				if (data[min] > data[j]) {
					min = j;
				}
			}
			swap(data, i, min);
		}
	}

	public static void main(String[] args) {
		Sort sorter = new SelectSortTest();
		sorter.sort(SORT_DATA.length, SORT_DATA);
		System.out.println(Arrays.toString(SORT_DATA));
	}
}
