package com.sort;

/**
 * @Description: 冒泡排序
 * 排序思想是两两比较，但不是和所有人比较，和冒上去的人比较
 * @author : david
 * @date Date : 2021年02月09日 20:27
 * @version V1.0
 */
public class BubbleSort implements Sort {
	@Override
	public void sort(int n, int[] data) {
		// 4 2 3 1
		// 第一步：4
		// 第二步：4 2 ==> 2 4
		// 第三步：2 4 3 ==> 2 3 4
		// 第四步：2 3 4 1 ==> 2 3 1 4 ==> 2 1 3 4 ==> 1 2 3 4
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (data[j] > data[j + 1]) {
					// 交换
					swap(data, j, j + 1);
				}
			}
		}
	}
}
