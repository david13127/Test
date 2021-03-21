package com.mashibing.algorithm.sort.practice;

import com.mashibing.algorithm.sort.Sort;

import java.util.Arrays;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2021年02月10日 9:49
 * @version V1.0
 */
public class ShellSortTest implements Sort {
	@Override
	public void sort(int n, int[] data) {
		int step = n;
		while (step >= 1) {
			step /= 2;
			for (int i = step; i < n; i++) {
				for (int j = i; j >= step; j -= step) {
					if (data[j - step] > data[j]) {
						swap(data, j - step, j);
					} else {
						break;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Sort sorter = new ShellSortTest();
		sorter.sort(SORT_DATA.length, SORT_DATA);
		System.out.println(Arrays.toString(SORT_DATA));
	}
}
