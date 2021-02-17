package com.mashibing.algorithm.dp;

/**
 * @Description: N皇后问题，不能转成动态规划
 * @author : david
 * @date Date : 2021年02月17日 21:23
 * @version V1.0
 */
public class NotDp_NQueens {
	public static int num1(int n) {
		if (n < 1) {
			return 0;
		}
		int[] record = new int[n];
		return process1(0, record, n);
	}

	// 当前来到i行，一共是0..n-1行
	// 在i行上放皇后，所有列都尝试
	// 必须保证跟之前所有的皇后都不打架
	// record[x]=y 表示x行放在了y列的位置
	// 返回：不关心i以上发生了什么，因为已经在record里了，返回i...后续有多少合法的方法数
	private static int process1(int i, int[] record, int n) {
		if (i == n) {
			return 1;
		}
		int res = 0;
		// i行的皇后放的位置，一个一个尝试
		for (int j = 0; j < n; j++) {
			if (isValid(record, i, j)) {
				record[i] = j;
				res += process1(i + 1, record, n);
			}
		}
		return res;
	}

	private static boolean isValid(int[] record, int i, int j) {
		for (int k = 0; k < i; k++) {
			if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
				return false;
			}
		}
		return true;
	}

	// 请不要超过32皇后问题
	public static int num2(int n) {
		if (n < 1 || n > 32) {
			return 0;
		}
		// 如果你是13皇后问题，limit 最右13个1，其他都是0
		int limit = n == 32 ? -1 : (1 << n) - 1;
		return process2(limit, 0, 0, 0);
	}

	// 7皇后问题
	// limit : 0....0 1 1 1 1 1 1 1
	// 之前皇后的列影响：colLim
	// 之前皇后的左下对角线影响：leftDiaLim
	// 之前皇后的右下对角线影响：rightDiaLim
	private static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
		if (colLim == limit) {
			return 1;
		}
		// pos中所有是1的位置，是你可以去尝试皇后的位置
		int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
		int mostRightOne = 0;
		int res = 0;
		while (pos != 0) {
			mostRightOne = pos & (~pos + 1);
			pos = pos - mostRightOne;
			res += process2(limit,
					colLim | mostRightOne,
					(leftDiaLim | mostRightOne) << 1,
					(rightDiaLim | mostRightOne) >>> 1);
		}
		return res;
	}


	public static void main(String[] args) {
		int n = 17;
		System.out.println(n + "皇后问题：");
		long start2 = System.currentTimeMillis();
		System.out.println(num2(n));
		long end2 = System.currentTimeMillis();
		System.out.println("方法2耗时：" + (end2 - start2) + "ms");

		long start1 = System.currentTimeMillis();
		System.out.println(num1(n));
		long end1 = System.currentTimeMillis();
		System.out.println("方法1耗时：" + (end1 - start1) + "ms");
	}
}
