package com.nuban.sort;

/**
 * @Description: 希尔排序
 * @author : david
 * @date Date : 2021年02月09日 20:57
 * @version V1.0
 */
public class ShellSort implements Sort {
	@Override
	public void sort(int n, int[] data) {
		// 4 1 2 3 5
		// 第一步：step = len / 2 ==> 5/2 = 2
		// 4 2 ==> 插入排序 2 4
		// 1 3 ==> 1 3
		// 5 ==> 5
		// 得到 2 4 1 3 5
		// 第二步： step = step/2 ==> 2/2 = 1
		// 2 1 4 3 5 ==> 插入排序
		// 取step的目的是为了减少判断次数
		int step = n;
		while (step >= 1) {
			step /= 2;
			for (int i = step; i < n; i++) {
				for (int j = i; j - step >= 0; j -= step) {
					if (data[j] < data[j - step]) {
						swap(data, j, j - step);
					} else {
						break;
					}
				}
			}
		}
	}
}
