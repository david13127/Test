package com.sort;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Description: 排序主程序
 * @author : david
 * @date Date : 2021年02月09日 21:30
 * @version V1.0
 */
public class SortMain {
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		int n = cin.nextInt();
		int[] data = new int[n];
		for (int i = 0; i < n; i++) {
			data[i] = cin.nextInt();
		}
		Sort insertSort = new QuickSort();
		insertSort.sort(n, data);
		System.out.println(Arrays.toString(data));
	}
}
