package com.mashibing.algorithm.dp.left2right;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 面值不同，且张数有限，求组合出目标金额的方法
 * @author : david
 * @date Date : 2021年02月17日 10:33
 * @version V1.0
 */
public class CoinsWaySameValueSamePaper {
	public static class Info {
		int[] conis;
		int[] papers;

		Info(int[] c, int[] p) {
			this.conis = c;
			this.papers = p;
		}
	}

	public static int coinsWay(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		Info info = getInfo(arr);
		return process(info.conis, info.papers, 0, aim);
	}

	// coins 面值数组，正数且去重
	// papers 面值对应的张数
	public static int process(int[] coins, int[] papers, int index, int rest) {
		if (index == coins.length) {
			return rest == 0 ? 1 : 0;
		}
		int ways = 0;
		for (int per = 0; per * coins[index] <= rest && per <= papers[index]; per++) {
			ways += process(coins, papers, index + 1, rest - per * coins[index]);
		}
		return ways;
	}

	public static int dp(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		Info info = getInfo(arr);
		int[] coins = info.conis;
		int[] papers = info.papers;
		int n = coins.length;
		int[][] dp = new int[n + 1][aim + 1];
		dp[n][0] = 1;
		for (int index = n - 1; index >= 0; index--) {
			for (int rest = 0; rest <= aim; rest++) {
				int ways = 0;
				for (int per = 0; per * coins[index] <= rest && per <= papers[index]; per++) {
					ways += dp[index + 1][rest - per * coins[index]];
				}
				dp[index][rest] = ways;
				// 求index行14列的值，面值为3，3面值的有2张
				// 需要将index+1行，14列，11列，8列的值相加，是一个枚举过程，可优化
				// 通过dp表可发现，11列，8列，5列的值相加，是index行11列的值，比求index行14列的值多一个第5列的值
				// 所以可以用dp[index][11] + dp[index+1][14] - dp[index + 1][rest - coins[index] * (papers[index] + 1)]代替
				// dp[index][rest] = dp[index + 1][rest];
				// if (rest - coins[index] >= 0) {
				// 	dp[index][rest] += dp[index][rest - coins[index]];
				// }
				// if (rest - coins[index] * (papers[index] + 1) >= 0) {
				// 	dp[index][rest] -= dp[index + 1][rest - coins[index] * (papers[index] + 1)];
				// }
			}
		}
		return dp[0][aim];
	}

	private static Info getInfo(int[] arr) {
		HashMap<Integer, Integer> counts = new HashMap<>();
		for (int value : arr) {
			if (counts.containsKey(value)) {
				counts.put(value, counts.get(value) + 1);
			} else {
				counts.put(value, 1);
			}
		}
		int n = counts.size();
		int[] coins = new int[n];
		int[] papers = new int[n];
		int index = 1;
		for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
			coins[index] = entry.getKey();
			papers[index] = entry.getValue();
			index++;
		}
		return new Info(coins, papers);
	}

	// 为了测试
	private static int[] randomArray(int maxLen, int maxValue) {
		int N = (int) (Math.random() * maxLen);
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = (int) (Math.random() * maxValue) + 1;
		}
		return arr;
	}

	// 为了测试
	private static void printArray(int[] arr) {
		for (int value : arr) {
			System.out.print(value + " ");
		}
		System.out.println();
	}

	// 为了测试
	public static void main(String[] args) {
		int maxLen = 10;
		int maxValue = 20;
		int testTime = 1000000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int[] arr = randomArray(maxLen, maxValue);
			int aim = (int) (Math.random() * maxValue);
			int ans1 = coinsWay(arr, aim);
			int ans2 = dp(arr, aim);
			if (ans1 != ans2) {
				System.out.println("Oops!");
				printArray(arr);
				System.out.println(aim);
				System.out.println(ans1);
				System.out.println(ans2);
				break;
			}
		}
		System.out.println("测试结束");
	}

}

