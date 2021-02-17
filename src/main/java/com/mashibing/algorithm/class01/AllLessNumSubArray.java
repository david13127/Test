package com.mashibing.algorithm.class01;

import java.util.LinkedList;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2021年02月16日 9:28
 * @version V1.0
 */
public class AllLessNumSubArray {
	// 暴力解
	public static int getNumBao(int[] arr, int num) {
		if (arr == null || arr.length == 1) {
			return 0;
		}
		int ans = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				int max = 0;
				int min = 0;
				for (int k = i; k <= j; k++) {
					if (max < arr[k]) {
						max = arr[k];
					}
					if (min > arr[k]) {
						min = arr[k];
					}
				}
				if (max - min <= num) {
					ans++;
				}
			}
		}
		return ans;
	}

	// 滑动窗口
	public int getNum(int[] arr, int num) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		LinkedList<Integer> qMin = new LinkedList<>();
		LinkedList<Integer> qMax = new LinkedList<>();
		int L = 0;
		int R = 0;
		// [L .. R) -> [0,0) 窗口无值
		int ans = 0;
		while (L < arr.length) { // 算每次从L开头的序列达标的数目
			while (R < arr.length) { // R往后移动，直到不达标
				while (!qMin.isEmpty() && arr[qMin.peekLast()] >= arr[R]) {
					qMin.pollLast();
				}
				qMin.addLast(R);
				while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[R]) {
					qMax.pollLast();
				}
				qMax.addLast(R);
				// 到达第一个不达标的位置，结束
				if (arr[qMax.getFirst()] - arr[qMin.getFirst()] > num) {
					break;
				}
				R++;
			}
			// R是最后一个达标位置的再下一个，第一个违规的位置
			ans += R - L;
			// L是否过期，过期弹出
			if (qMin.peekFirst() == L) {
				qMin.pollFirst();
			}
			if (qMax.peekFirst() == L) {
				qMax.pollFirst();
			}
			L++;
		}
		return ans;
	}
}
