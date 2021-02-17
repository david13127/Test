package com.mashibing.algorithm.dp;

/**
 * @Description: 拿牌问题，两个人，每次只从左或者右拿一张牌，返回获胜者分数
 * @author : david
 * @date Date : 2021年02月16日 12:48
 * @version V1.0
 */
public class CardsInLine {
	public static int win1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int first = preHand1(arr, 0, arr.length - 1);
		int second = postHand1(arr, 0, arr.length - 1);
		return Math.max(first, second);
	}

	private static int preHand1(int[] arr, int l, int r) {
		if (l == r) {
			return arr[l];
		}
		int p1 = arr[l] + postHand1(arr, l + 1, r);
		int p2 = arr[r] + postHand1(arr, l, r - 1);
		return Math.max(p1, p2);
	}

	private static int postHand1(int[] arr, int l, int r) {
		if (l == r) {
			return 0;
		}
		int p1 = preHand1(arr, l + 1, r);
		int p2 = preHand1(arr, l, r - 1);
		return Math.min(p1, p2);
	}


	public static int win2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int n = arr.length;
		int[][] fMap = new int[n][n];
		int[][] gMap = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				fMap[i][j] = -1;
				gMap[i][j] = -1;
			}
		}
		int first = preHand2(fMap, gMap, arr, 0, arr.length - 1);
		int second = postHand2(fMap, gMap, arr, 0, arr.length - 1);
		return Math.max(first, second);
	}

	private static int preHand2(int[][] fMap, int[][] gMap, int[] arr, int l, int r) {
		if (fMap[l][r] != -1) {
			return fMap[l][r];
		}
		int ans;
		if (l == r) {
			ans = arr[l];
		} else {
			int p1 = arr[l] + postHand2(fMap, gMap, arr, l + 1, r);
			int p2 = arr[r] + postHand2(fMap, gMap, arr, l, r - 1);
			ans = Math.max(p1, p2);
		}
		fMap[l][r] = ans;
		return ans;
	}

	private static int postHand2(int[][] fMap, int[][] gMap, int[] arr, int l, int r) {
		if (gMap[l][r] != -1) {
			return gMap[l][r];
		}
		int ans = 0;
		if (l != r) {
			int p1 = preHand2(fMap, gMap, arr, l + 1, r);
			int p2 = preHand2(fMap, gMap, arr, l, r - 1);
			ans = Math.min(p1, p2);
		}
		gMap[l][r] = ans;
		return ans;
	}

	public static int win3(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int n = arr.length;
		int[][] fMap = new int[n][n];
		int[][] gMap = new int[n][n];
		for (int i = 0; i < n; i++) {
			fMap[i][i] = arr[i];
		}
		for (int startCol = 1; startCol < n; startCol++) {
			int row = 0;
			int col = startCol;
			while (col < n) {
				fMap[row][col] = Math.max(arr[row] + gMap[row + 1][col], arr[col] + gMap[row][col - 1]);
				gMap[row][col] = Math.min(fMap[row + 1][col], fMap[row][col - 1]);
				row++;
				col++;
			}
		}
		return Math.max(fMap[0][n - 1], gMap[0][n - 1]);
	}

	public static void main(String[] args) {
		int[] cards = {10, 20, 30};
		System.out.println(win1(cards));
		System.out.println(win2(cards));
		System.out.println(win3(cards));
	}

}
