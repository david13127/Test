package com.mashibing.algorithm.sort;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
		int min = 0;
		for (int i = 0; i < n; i++) {
			data[i] = getRandom(min, n);
		}
		System.out.println(Arrays.toString(data));
		Sort sorter1 = new QuickSort();
		Sort sorter2 = new ShellSort();
		Sort sorter3 = new MergeSort();
		Sort sorter4 = new SelectSort();
		Sort sorter5 = new InsertSort();
		Sort sorter6 = new BubbleSort();
		Sort sorter7 = new HeapSort();
		Sort[] sorterList = {sorter1, sorter2, sorter3, sorter4, sorter5, sorter6, sorter7};
		ExecutorService cachedThreadPool = Executors.newFixedThreadPool(5);
		for (Sort sorter : sorterList) {
			Thread thread = new Thread(() -> {
				int[] temp = new int[n];
				System.arraycopy(data, 0, temp, 0, n);
				long start = System.currentTimeMillis();
				sorter.sort(n, temp);
				long end = System.currentTimeMillis();
				System.out.println(sorter.getClass().getSimpleName() + "：" + (end - start) + "ms");
			});
			cachedThreadPool.execute(thread);
		}
		cachedThreadPool.shutdown();
	}

	private static int getRandom(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}
}
