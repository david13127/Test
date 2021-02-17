package com.nuban.sort.practice;

import com.nuban.sort.Sort;

import java.util.Arrays;

public class InsertSortTest implements Sort {
	@Override
	public void sort(int n, int[] data) {
		for (int i = 0; i < n; i++) {
			for (int j = i; j > 0; j--) {
				if (data[j - 1] > data[j]) {
					swap(data, j - 1, j);
				}
			}
		}
	}

	public static void main(String[] args) {
		Sort sorter = new InsertSortTest();
		sorter.sort(SORT_DATA.length, SORT_DATA);
		System.out.println(Arrays.toString(SORT_DATA));
	}
}