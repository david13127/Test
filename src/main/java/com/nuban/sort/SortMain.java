package com.nuban.sort;

import com.nuban.sort.practice.*;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

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
		for (int i = 0; i < n; i++) {
			data[i] = getRandom(0, n);
		}
		System.out.println(Arrays.toString(data));
		Sort sorter1 = new QuickSortTest();
		Sort sorter2 = new ShellSortTest();
		Sort sorter3 = new MergeSortTest();
		Sort sorter4 = new SelectSortTest();
		Sort sorter5 = new InsertSortTest();
		Sort sorter6 = new BubbleSortTest();

		Thread thread1 = new Thread(() -> {
			int[] temp = new int[n];
			System.arraycopy(data, 0, temp, 0, n);
			long start1 = System.currentTimeMillis();
			sorter1.sort(n, temp);
			long end1 = System.currentTimeMillis();
			System.out.println("快速：" + (end1 - start1) + "ms");
		});
		Thread thread2 = new Thread(() -> {
			int[] temp = new int[n];
			System.arraycopy(data, 0, temp, 0, n);
			long start2 = System.currentTimeMillis();
			sorter2.sort(n, temp);
			long end2 = System.currentTimeMillis();
			System.out.println("希尔：" + (end2 - start2) + "ms");
		});
		Thread thread3 = new Thread(() -> {
			int[] temp = new int[n];
			System.arraycopy(data, 0, temp, 0, n);
			long start3 = System.currentTimeMillis();
			sorter3.sort(n, temp);
			long end3 = System.currentTimeMillis();
			System.out.println("归并：" + (end3 - start3) + "ms");
		});
		Thread thread4 = new Thread(() -> {
			int[] temp = new int[n];
			System.arraycopy(data, 0, temp, 0, n);
			long start4 = System.currentTimeMillis();
			sorter4.sort(n, temp);
			long end4 = System.currentTimeMillis();
			System.out.println("选择：" + (end4 - start4) + "ms");
		});
		Thread thread5 = new Thread(() -> {
			int[] temp = new int[n];
			System.arraycopy(data, 0, temp, 0, n);
			long start5 = System.currentTimeMillis();
			sorter5.sort(n, temp);
			long end5 = System.currentTimeMillis();
			System.out.println("插入：" + (end5 - start5) + "ms");
		});
		Thread thread6 = new Thread(() -> {
			int[] temp = new int[n];
			System.arraycopy(data, 0, temp, 0, n);
			long start6 = System.currentTimeMillis();
			sorter6.sort(n, temp);
			long end6 = System.currentTimeMillis();
			System.out.println("冒泡：" + (end6 - start6) + "ms");
		});
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();
	}

	public static int getRandom(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}
}
