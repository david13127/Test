package com.mashibing.algorithm.sort;

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
		// 第一步：从0 到 i位置, i = n - 1
		// 1.1：2 3 4 1 比0 1位置, data[0] < data[1], 不交换, 得2 3 4 1
		// 1.2：2 3 4 1 比1 2位置, data[1] < data[2], 不交换, 得2 3 4 1
		// 1.3：2 3 4 1 比2 3位置, data[2] > data[3], 交换, 得2 3 1 4
		// 第二步：i位置以后有序，i--, 重复第一步，直到 i = 0
		for (int i = n - 1; i >= 0; i--) {
			for (int j = 1; j < i; j++) {
				if (data[j - 1] > data[j]) {
					// 交换
					swap(data, j - 1, j);
				}
			}
		}
	}
}
