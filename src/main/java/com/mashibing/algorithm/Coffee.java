package com.mashibing.algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2021年02月16日 22:27
 * @version V1.0
 */
public class Coffee {

	public static int minTime1(int[] arr, int n, int a, int b) {
		PriorityQueue<Machine> heap = new PriorityQueue<>(new MachineComparator());
		for (int value : arr) {
			heap.add(new Machine(0, value));
		}
		int[] drinks = new int[n];
		for (int i = 0; i < n; i++) {
			Machine cur = heap.poll();
			cur.timePoint += cur.workTime;
			drinks[i] = cur.timePoint;
			heap.add(cur);
		}
		return process(drinks, a, b, 0, 0);
	}

	// process(drinks, 3, 10, 0, 0)
	// a 洗一杯的时候，固定不变
	// b 自己挥发干净的时候，固定不变
	// index 当前来的杯子的序号
	// drinks 每一个人喝完的时间，固定不变
	// drinks[0..index-1]都已经干净了，不用操心
	// drinks[index..]都想变干净，这是要操心的
	// washLine表示洗的机器何时可用
	// 返回drinks[index..]变干净，最小的时候点
	public static int process(int[] drinks, int a, int b, int index, int washLine) {
		if (index == drinks.length) {
			return 0;
		}
		// 剩下不止一杯咖啡
		// 决定洗
		int wash = Math.max(washLine, drinks[index]) + a; // wash是我当前的咖啡杯洗完的时间
		int next1 = process(drinks, a, b, index + 1, wash); // index+1...变干净的最早时间点
		int p1 = Math.max(wash, next1);
		// 决定挥发
		int dry = drinks[index] + b; // 挥发index一坏，结束的时间点
		int next2 = process(drinks, a, b, index + 1, washLine); // index+1...变干净的最早时间点
		int p2 = Math.max(dry, next2);
		return Math.min(p1, p2);
	}

	public static int minTime2(int[] arr, int n, int a, int b) {
		PriorityQueue<Machine> heap = new PriorityQueue<>(new MachineComparator());
		for (int value : arr) {
			heap.add(new Machine(0, value));
		}
		int[] drinks = new int[n];
		for (int i = 0; i < n; i++) {
			Machine cur = heap.poll();
			cur.timePoint += cur.workTime;
			drinks[i] = cur.timePoint;
			heap.add(cur);
		}
		return dp(drinks, a, b);
	}

	public static int dp(int[] drinks, int a, int b) {
		int maxFree = 0;
		for (int drink : drinks) {
			maxFree = Math.max(maxFree, drink) + a;
		}
		int n = drinks.length;
		int[][] dp = new int[n + 1][maxFree + 1];
		// dp[n][..] = 0
		for (int index = n - 1; index >= 0 ; index--) {
			for (int free = 0; free <= maxFree; free++) {
				int wash = Math.max(free, drinks[index]) + a; // wash是我当前的咖啡杯洗完的时间
				if (wash > maxFree) {
					continue;
				}
				int next1 = dp[index + 1][wash]; // index+1...变干净的最早时间点
				int p1 = Math.max(wash, next1);
				// 决定挥发
				int dry = drinks[index] + b; // 挥发index一坏，结束的时间点
				int next2 = dp[index + 1][free]; // index+1...变干净的最早时间点
				int p2 = Math.max(dry, next2);
				dp[index][free] = Math.min(p1, p2);
			}
		}
		return dp[0][0];
	}

	public static class Machine {
		private int timePoint;
		private int workTime;

		public Machine(int t, int w) {
			this.timePoint = t;
			this.workTime = w;
		}
	}

	public static class MachineComparator implements Comparator<Machine> {
		@Override
		public int compare(Machine o1, Machine o2) {
			return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
		}
	}

	private static int[] randomArray(int len, int max) {
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * max) + 1;
		}
		return arr;
	}
	public static void main(String[] args) {
		int len = 5;
		int max = 9;
		int testTime = 50000;
		for (int i = 0; i < testTime; i++) {
			int[] arr = randomArray(len, max);
			int n = (int) (Math.random() * 5) + 1;
			int a = (int) (Math.random() * 5) + 1;
			int b = (int) (Math.random() * 5) + 1;
			int ans1 = minTime1(arr, n, a, b);
			int ans2 = minTime2(arr, n, a, b);
			if (ans1 != ans2) {
				System.out.println("n: " + n);
				System.out.println("a: " + a);
				System.out.println("b: " + b);
				System.out.println(ans1 + ", " + ans2);
				System.out.println("====================");
				break;
			}
		}
	}
}
