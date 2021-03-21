package com.mashibing.algorithm.sort.practice;

import com.mashibing.algorithm.sort.Sort;

import java.util.Arrays;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2021年02月10日 10:45
 * @version V1.0
 */
public class QuickSortTest implements Sort {
	@Override
	public void sort(int n, int[] data) {
		qSort(data, 0, n - 1);
	}
	private void qSort(int[] data, int left, int right) {
		int ll = left;
		int rr = right;
		int base = data[ll];
		while (ll < rr) {
			while (ll < rr && data[rr] >= base) {
				rr--;
			}
			if (ll < rr) {
				swap(data, ll, rr);
				ll++;
			}
			while (ll < rr && data[ll] <= base) {
				ll++;
			}
			if (ll < rr) {
				swap(data, ll, rr);
				rr--;
			}
		}
		if (ll > left) {
			qSort(data, left, ll - 1);
		}
		if (rr < right) {
			qSort(data, ll + 1, right);
		}
	}

	public static void main(String[] args) {
		Sort sorter = new QuickSortTest();
		sorter.sort(SORT_DATA.length, SORT_DATA);
		System.out.println(Arrays.toString(SORT_DATA));
	}
}
