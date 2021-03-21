package com.mashibing.algorithm.sort.practice;

import com.mashibing.algorithm.sort.Sort;

import java.util.Arrays;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2021年02月10日 9:21
 * @version V1.0
 */
public class BubbleSortTest implements Sort {
	@Override
	public void sort(int n, int[] data) {
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < n - i; j++) {
				if (data[j - 1] > data[j]) {
					swap(data, j - 1, j);
				}
			}
		}
	}

	public static void main(String[] args) {
		Sort sorter = new BubbleSortTest();
		sorter.sort(SORT_DATA.length, SORT_DATA);
		System.out.println(Arrays.toString(SORT_DATA));
	}
}
