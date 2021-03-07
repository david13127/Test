package com.nuban.sort;

import java.util.Stack;

/**
 * @Description: 快速排序
 * 基准数：根据基准数分组，分成两组，左边都双基准数小，右边则大
 * 分组后，对组内进行排序
 * @author : david
 * @date Date : 2021年02月09日 22:17
 * @version V1.0
 */
public class QuickSort implements Sort {
	@Override
	public void sort(int n, int[] data) {
		// 45 28 80 90 50 16 100 10
		// 基准数假设找45
		// 1.1 从后往前找，比45小的，进行交换，并记录右边找到的位置
		// 找到10比45小，交换后得[10] 28 80 90 50 16 100 [45]，记录之前的位置
		// 1.2 从前往后找，比45大的，进行交换，并记录左边找到的位置
		// 10 28 [45] 90 50 16 100 [80]
		// 1.3 重复步骤1.1和1.2，直到前后碰头，交换完成，结束。
		// 10 28 [16] 90 50 [45] 100 80
		// 10 28 16 [45] 50 [90] 100 80
		// 得{10 28 16} 45 {50 90 100 80}
		// 对上述两组，分别进行步骤1
		// qSort(data, 0, n - 1);

		// 非递归
		qSort4(data);
	}

	// 双指针逼近，大的在右，小在的左
	private void qSort(int[] data, int left, int right) {
		int ll = left; // 从左找的位置
		int rr = right; // 从右找的位置
		int base = data[ll]; // 取第一个作为基准数
		while (ll < rr) {
			// 从后往前找比base小的进行交换
			while (ll < rr && data[rr] >= base) {
				rr--;
			}
			if (ll < rr) {
				swap(data, ll, rr);
				ll++;
			}
			// 从前往后找比base大的进行交换
			while (ll < rr && data[ll] <= base) {
				ll++;
			}
			if (ll < rr) {
				swap(data, ll, rr);
				rr--;
			}
		}
		// 分组后，对左右两组再进行排序
		if (ll > left) {
			qSort(data, left, ll - 1);
		}
		if (rr < right) {
			qSort(data, ll + 1, right);
		}
	}

	private void qSort2(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process2(arr, 0, arr.length - 1);
	}

	private void qSort3(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process3(arr, 0, arr.length - 1);
	}

	private void process2(int[] arr, int l, int r) {
		if (l >= r) {
			return;
		}
		int[] eqArea = partition(arr, l, r);
		process2(arr, l, eqArea[0] - 1);
		process2(arr, eqArea[1] + 1, r);
	}

	private void process3(int[] arr, int l, int r) {
		if (l >= r) {
			return;
		}
		swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
		int[] eqArea = partition(arr, l, r);
		process3(arr, l, eqArea[0] - 1);
		process3(arr, eqArea[1] + 1, r);
	}

	// 小于的左边，等于的中间，大于的右边
	private int[] partition(int[] arr, int l, int r) {
		if (l > r) {
			return new int[]{-1, -1};
		}
		if (l == r) {
			return new int[]{l, r};
		}
		int less = l - 1;
		int more = r;
		int index = l;
		while (index < more) { // 当前位置不能和大于区域撞上
			if (arr[index] == arr[r]) {
				index++;
			} else if (arr[index] < arr[r]) {
				swap(arr, index++, ++less);
			} else {
				swap(arr, index, --more);
			}
		}
		swap(arr, more, r);
		return new int[]{less + 1, more};
	}

	// 非递归实现
	private void qSort4(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int n = arr.length;
		swap(arr, (int) (Math.random() * n), n - 1);
		int[] eqArea = partition(arr, 0, n - 1);
		int el = eqArea[0];
		int er = eqArea[1];
		Stack<Op> stack = new Stack<>();
		stack.push(new Op(0, el - 1));
		stack.push(new Op(er + 1, n - 1));
		while (!stack.isEmpty()) {
			Op op = stack.pop();
			if (op.l < op.r) {
				swap(arr, op.l + (int) (Math.random() * (op.r - op.l + 1)), op.r);
				eqArea = partition(arr, op.l, op.r);
				el = eqArea[0];
				er = eqArea[1];
				stack.push(new Op(op.l, el - 1));
				stack.push(new Op(er + 1, op.r));
			}
		}
	}

	class Op {
		int l;
		int r;

		Op(int left, int right) {
			l = left;
			r = right;
		}
	}
}
