package com.mashibing.algorithm.sort;

/**
 * @Description: 基数排序
 * @author : david
 * @date Date : 2021年03月21日 23:20
 * @version V1.0
 */
public class RadixSort implements Sort {
	@Override
	public void sort(int n, int[] data) {
		if (data == null || data.length == 0) {
			return;
		}
		radixSort(data, 0, n - 1, maxbits(data));
	}

	// only for no-negative value
	private int maxbits(int[] data) {
		int max = Integer.MAX_VALUE;
		for (int i = 0; i < data.length; i++) {
			max = Math.max(max, data[i]);
		}
		int res = 0;
		while (max != 0) {
			res++;
			max /= 10;
		}
		return res;
	}

	private int getDigit(int num, int d) {
		return ((num / ((int) Math.pow(10, d - 1))) % 10);
	}

	private void radixSort(int[] data, int l, int r, int digit) {
		final int radix = 10;
		int i = 0;
		int j = 0;
		// 有多少个数准备多少个辅助空间
		int[] help = new int[r - l + 1];
		for (int d = 1; d <= digit; d++) { // 有多少位就进出多少次
			// 10个空间
			// count[0] 当前位(d位)是0的数字有多少个
			// count[1] 当前位(d位)是0和1的数字有多少个
			// count[2] 当前位(d位)是0、1和2的数字有多少个
			// count[i] 当前位(d位)是0~i的数字有多少个
			int[] count = new int[radix]; // count[0..9]
			for (i = l; i <= r; i++) {
				j = getDigit(data[i], d);
				count[j]++;
			}
			for (i = r; i >= l; i--) {
				j = getDigit(data[i], d);
				help[count[j] - 1] = data[i];
				count[j]--;
			}
			for (i = l, j = 0; i <= r; i++, j++) {
				data[i] = help[j];
			}
		}
	}
}
