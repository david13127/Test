package com.sort;

/**
 * @Description: 快速排序
 * 基准数：根据基准数分组，分成两组，左边都双基准数小，右边则大
 * 分组后，对组内进行排序
 * @author : david
 * @date Date : 2021年02月09日 22:17
 * @version V1.0
 */
public class QuickSort implements Sort {
	@Override
	public void sort(int n, int[] data) {
		// 45 28 80 90 50 16 100 10
		// 基准数假设找45
		// 1.1 从后往前找，比45小的，进行交换，并记录右边找到的位置
		// 找到10比45小，交换后得[10] 28 80 90 50 16 100 [45]，记录之前的位置
		// 1.2 从前往后找，比45大的，进行交换，并记录左边找到的位置
		// 10 28 [45] 90 50 16 100 [80]
		// 1.3 重复步骤1.1和1.2，直到前后碰头，交换完成，结束。
		// 10 28 [16] 90 50 [45] 100 80
		// 10 28 16 [45] 50 [90] 100 80
		// 得{10 28 16} 45 {50 90 100 80}
		// 对上述两组，分别进行步骤1
		sort(data, 0, n - 1);
	}

	private void sort(int[] data, int left, int right) {
		int ll = left; // 从左找的位置
		int rr = right; // 从右找的位置
		int base = data[ll]; // 取第一个作为基准数
		while (ll < rr) {
			// 从后往前找比base小的进行交换
			while (ll < rr && data[rr] >= base) {
				rr--;
			}
			if (ll < rr) {
				swap(data, ll, rr);
				ll++;
			}
			// 从前往后找比base大的进行交换
			while (ll < rr && data[ll] <= base) {
				ll++;
			}
			if (ll < rr) {
				swap(data, ll, rr);
				rr--;
			}
		}
		// 分组后，对左右两组再进行排序
		if (ll > left) {
			sort(data, left, ll - 1);
		}
		if (rr < right) {
			sort(data, ll + 1, right);
		}
	}
}
