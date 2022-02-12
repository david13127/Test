package com.mashibing.algorithm.greed;

import com.mashibing.algorithm.util.Generator;

import java.util.PriorityQueue;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月12日 12:23
 * @version V1.0
 */
public class LessMoney {
	public int lessMoney(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for (int num : arr) {
			queue.add(num);
		}
		int ans = 0;
		while (queue.size() > 1) {
			int cur = queue.poll() + queue.poll();
			ans += cur;
			queue.add(cur);
		}
		return ans;
	}

	public int lessMoneyBao(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		return process(arr, 0);
	}

	private int process(int[] arr, int pre) {
		if (arr.length == 1) {
			return pre;
		}
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				ans = Math.min(ans, process(copyAndMergeTwo(arr, i, j), pre + arr[i] + arr[j]));
			}
		}
		return ans;
	}

	private int[] copyAndMergeTwo(int[] arr, int i, int j) {
		int[] ans = new int[arr.length - 1];
		int ansi = 0;
		for (int arri = 0; arri < arr.length; arri++) {
			if (arri != i && arri != j) {
				ans[ansi++] = arr[arri];
			}
		}
		ans[ansi] = arr[i] + arr[j];
		return ans;
	}

	public static void main(String[] args) {
		LessMoney solution = new LessMoney();
		int testTime = 100000;
		int maxSize = 6;
		int maxValue = 1000;
		for (int i = 0; i < testTime; i++) {
			int[] arr = Generator.generateRandomArray(maxSize, maxValue);
			if (solution.lessMoneyBao(arr) != solution.lessMoney(arr)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}
}
