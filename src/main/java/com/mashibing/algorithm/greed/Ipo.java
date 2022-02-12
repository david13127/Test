package com.mashibing.algorithm.greed;

import com.mashibing.algorithm.util.Generator;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月12日 17:14
 * @version V1.0
 */
public class Ipo {
	public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
		PriorityQueue<Program> minCostQueue = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
		PriorityQueue<Program> maxProfitQueue = new PriorityQueue<>(Comparator.comparingInt(e -> -e.profit));
		for (int i = 0; i < profits.length; i++) {
			minCostQueue.add(new Program(capital[i], profits[i]));
		}
		for (int i = 0; i < k; i++) {
			while (!minCostQueue.isEmpty() && minCostQueue.peek().cost <= w) {
				maxProfitQueue.add(minCostQueue.poll());
			}
			if (maxProfitQueue.isEmpty()) {
				return w;
			}
			w += maxProfitQueue.poll().profit;
		}
		return w;
	}

	static class Program {
		int cost;
		int profit;
		Program(int c, int p) {
			this.cost = c;
			this.profit = p;
		}
	}

	public static void main(String[] args) {
		Ipo solution = new Ipo();
		int k = 3;
		int n = 7;
		int w = 5;
		int[] profits = Generator.generateRandomArrayFixedSize(n, 10);
		int[] captals = Generator.generateRandomArrayFixedSize(n, 10);
		int ans = solution.findMaximizedCapital(k, w, profits, captals);
		System.out.println(ans);
	}
}
