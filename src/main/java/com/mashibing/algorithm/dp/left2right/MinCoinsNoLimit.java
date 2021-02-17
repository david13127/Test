package com.mashibing.algorithm.dp.left2right;

/**
 * @Description: 面值不同，张数无限，求组合出目标金额的最小张数
 * @author : david
 * @date Date : 2021年02月17日 14:20
 * @version V1.0
 */
public class MinCoinsNoLimit {
	public static int minCoins(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 1) {
			return 0;
		}
		return process(arr, 0, aim);
	}

	// arr[index..]面值，每种面值的张数自由选择
	// 搞出的金额正好rest，返回最小张数
	public static int process(int[] arr, int index, int rest) {
		if (rest < 0) {
			return Integer.MAX_VALUE;
		}
		if (index == arr.length) {
			return rest == 0 ? 0 : Integer.MAX_VALUE;
		} else {
			int ans = Integer.MAX_VALUE;
			for (int per = 0; per * arr[index] <= rest; per++) {
				int next = process(arr, index + 1, rest - per * arr[index]);
				if (next != Integer.MAX_VALUE) {
					ans = Math.min(ans, next + per);
				}
			}
			return ans;
		}
	}

	public static int dp(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 1) {
			return 0;
		}
		int n = arr.length;
		int[][] dp = new int[n + 1][aim + 1];
		dp[n][0] = 0;
		for (int j = 1; j <= aim; j++) {
			dp[n][j] = Integer.MAX_VALUE;
		}
		for (int index = n - 1; index >= 0; index--) {
			for (int rest = 0; rest <= aim; rest++) {
				int ans = Integer.MAX_VALUE;
				for (int per = 0; per * arr[index] <= rest; per++) {
					int next = dp[index + 1][rest - per * arr[index]];
					if (next != Integer.MAX_VALUE) {
						ans = Math.min(ans, next + per);
					}
				}
				dp[index][rest] = ans;
				// 求index行14列的值，面值为3
				// 需要将index+1行，14列，11列，8列，5列，2列的值求最小，是一个枚举过程，可优化
				// 通过dp表可发现，11列，8列，5列，2列的求最小，是index行11列的值，
				// 所以可以用dp[index][11] + 1与当前值求最小来代替
				// dp[index][rest] = dp[index + 1][rest];
				// if (rest - arr[index] >= 0 && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
				// 	dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - arr[index]] + 1);
				// }
			}
		}
		return dp[0][aim];
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
			int ans1 = minCoins(arr, aim);
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
