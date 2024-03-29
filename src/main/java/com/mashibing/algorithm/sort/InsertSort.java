package com.mashibing.algorithm.sort;

/**
 * @Description: 插入排序
 * @author : david
 * @date Date : 2021年02月09日 20:38
 * @version V1.0
 */
public class InsertSort implements Sort {
	@Override
	public void sort(int n, int[] data) {
		// 4 2 3 1
		// 第一步：4
		// 第二步：2 4
		// 第三步：2 3 4
		// 第四步：1 2 3 4
		for (int i = 0; i < n; i++) {
			for (int j = i - 1; j >= 0 && data[j] > data[j + 1]; j--) {
				swap(data, j, j + 1);
			}
			//			int j = i;
			//			while (j - 1 >= 0 && data[j - 1] > data[j]) {
			//				swap(data, j - 1, j);
			//				j--;
			//			}
		}
	}

}
