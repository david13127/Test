package com.mashibing.algorithm.class01;

import java.util.LinkedList;

/**
 * @Description: 滑动窗口求最大值
 * @author : david
 * @date Date : 2021年02月16日 9:14
 * @version V1.0
 */
public class SlidingWindowMaxArray {
	public static int[] getMaxWindow(int[] arr, int w) {
		// w是窗口的长度
		if (arr == null || w < 1 || arr.length < w) {
			return null;
		}
		// 队列里面放的是位置，头部到尾部是由大到小的
		LinkedList<Integer> qMax = new LinkedList<>();
		int[] res = new int[arr.length - w + 1];
		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			// 把i位置的值，放到队列最后，如果i位置的值，大于队列最后一个的值，弹出最后一个
			while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[i]) {
				qMax.pollLast();
			}
			qMax.addLast(i);
			// 过期下标 i - w, 过期则从头部弹出
			if (qMax.peekFirst() == i - w) {
				qMax.pollFirst();
			}
			// 窗口形成之后才收集答案
			if (i >= w - 1) {
				res[index++] = arr[qMax.peekFirst()];
			}
		}
		return res;
	}
}
