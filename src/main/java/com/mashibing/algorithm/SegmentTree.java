package com.mashibing.algorithm;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2021年02月14日 14:51
 * @version V1.0
 */
public class SegmentTree {
	private int maxN;
	private int[] arr; // 原序列的信息从0开始，但是在arr是从1开始
	private int[] sum; //模拟线段树维护区间和
	private int[] lazy; // 累加懒惰标记
	private int[] change; // 更新的值
	private boolean[] update; // 更新慵懒标记

	public SegmentTree(int[] origin) {
		maxN = origin.length + 1;
		arr = new int[maxN]; // arr[0]不用
		for (int i = 1; i < maxN; i++) {
			arr[i] = origin[i - 1];
		}
		sum = new int[maxN << 2]; // 用来支持某一个范围的累加和信息
		lazy = new int[maxN << 2]; // 用来支持某一个范围内没有往下传递的累加
		change = new int[maxN << 2]; // 某一个范围内有没有更新操作的
		update = new boolean[maxN << 2]; //
	}

	// 初始化的时候把sum数据填好
	// 在arr[l~r]范围上，去build，1~N
	// rt：这个范围内sum的下标
	public void buildTree(int l, int r, int rt) {
		if (l == r) {
			sum[rt] = arr[l];
			return;
		}
		int mid = (l + r) >> 1;
		buildTree(l, mid, rt << 1);
		buildTree(mid + 1, r, rt << 1 | 1);
		pushUp(rt);
	}

	private void pushUp(int rt) {
		sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
	}

	// L~R任务范围
	// l~r表达范围
	// rt去哪找
	public void add(int L, int R, int C, int l, int r, int rt) {
		// 任务范围彻底包含了表达的范围
		if (L <= l && r <= R) {
			sum[rt] += C * (r - l + 1);
			lazy[rt] += C;
			return;
		}
		// 没有全部包含，要把任务下发
		int mid = (l + r) >> 1; // 1~mid (rt << 1)  mid+1~r(rt << 1 | 1)
		// 下发之前的lazy add任务
		pushDown(rt, mid - l + 1, r - mid);
		// 左孩子是否需要接到任务
		if (L <= mid) {
			add(L, R, C, l, mid, rt << 1);
		}
		if (R > mid) {
			add(L, R, C, mid + 1, r, rt << 1 | 1);
		}
		pushUp(rt);
	}

	public void update(int L, int R, int C, int l, int r, int rt) {
		if (L <= l && r <= R) {
			update[rt] = true;
			change[rt] = C;
			sum[rt] = C * (r - l + 1);
			lazy[rt] = 0;
			return;
		}
		int mid = (l + r) >> 1;
		pushDown(rt, mid - l + 1, r - mid);
		if (L <= mid) {
			update(L, R, C, l, mid, rt << 1);
		}
		if (R > mid) {
			update(L, R, C, mid + 1, r, rt << 1 | 1);
		}
		pushUp(rt);
	}

	public long query(int L, int R, int l, int r, int rt) {
		if (L <= l && r <= R) {
			return sum[rt];
		}
		int mid = (l + r) >> 1;
		pushDown(rt, mid - l + 1, r - mid);
		long ans = 0;
		if (L <= mid) {
			ans += query(L, R, l, mid, rt << 1);
		}
		if (R > mid) {
			ans += query(L, R, mid + 1, r, rt << 1 | 1);
		}
		return ans;
	}

	private void pushDown(int rt, int ln, int rn) {
		if (update[rt]) { // 先下发update任务
			update[rt << 1] = true;
			update[rt << 1 | 1] = true;
			change[rt << 1] = change[rt];
			change[rt << 1 | 1] = change[rt];
			lazy[rt << 1] = 0;
			lazy[rt << 1 | 1] = 0;
			sum[rt << 1] = change[rt] * ln;
			sum[rt << 1 | 1] = change[rt] * rn;
			update[rt] = false;
		}
		if (lazy[rt] != 0) { // 再下发lazy任务
			lazy[rt << 1] += lazy[rt];
			sum[rt << 1] += lazy[rt] * ln;
			lazy[rt << 1 | 1] += lazy[rt];
			sum[rt << 1 | 1] += lazy[rt] * rn;
			lazy[rt] = 0;
		}
	}

	public static class Right {
		public int[] arr;

		public Right(int[] origin) {
			arr = new int[origin.length + 1];
			for (int i = 0; i < origin.length; i++) {
				arr[i + 1] = origin[i];
			}
		}

		public void update(int L, int R, int C) {
			for (int i = L; i <= R; i++) {
				arr[i] = C;
			}
		}

		public void add(int L, int R, int C) {
			for (int i = L; i <= R; i++) {
				arr[i] += C;
			}
		}

		public long query(int L, int R) {
			long ans = 0;
			for (int i = L; i <= R; i++) {
				ans += arr[i];
			}
			return ans;
		}

	}

	public static int[] genarateRandomArray(int len, int max) {
		int size = (int) (Math.random() * len) + 1;
		int[] origin = new int[size];
		for (int i = 0; i < size; i++) {
			origin[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
		}
		return origin;
	}

	public static boolean test() {
		int len = 100;
		int max = 1000;
		int testTimes = 5000;
		int addOrUpdateTimes = 1000;
		int queryTimes = 500;
		for (int i = 0; i < testTimes; i++) {
			int[] origin = genarateRandomArray(len, max);
			SegmentTree seg = new SegmentTree(origin);
			int S = 1;
			int N = origin.length;
			int root = 1;
			seg.buildTree(S, N, root);
			Right rig = new Right(origin);
			for (int j = 0; j < addOrUpdateTimes; j++) {
				int num1 = (int) (Math.random() * N) + 1;
				int num2 = (int) (Math.random() * N) + 1;
				int L = Math.min(num1, num2);
				int R = Math.max(num1, num2);
				int C = (int) (Math.random() * max) - (int) (Math.random() * max);
				if (Math.random() < 0.5) {
					seg.add(L, R, C, S, N, root);
					rig.add(L, R, C);
				} else {
					seg.update(L, R, C, S, N, root);
					rig.update(L, R, C);
				}
			}
			for (int k = 0; k < queryTimes; k++) {
				int num1 = (int) (Math.random() * N) + 1;
				int num2 = (int) (Math.random() * N) + 1;
				int L = Math.min(num1, num2);
				int R = Math.max(num1, num2);
				long ans1 = seg.query(L, R, S, N, root);
				long ans2 = rig.query(L, R);
				if (ans1 != ans2) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int[] origin = {2, 1, 1, 2, 3, 4, 5};
		SegmentTree seg = new SegmentTree(origin);
		int S = 1; // 整个区间的开始位置，规定从1开始，不从0开始 -> 固定
		int N = origin.length; // 整个区间的结束位置，规定能到N，不是N-1 -> 固定
		int root = 1; // 整棵树的头节点位置，规定是1，不是0 -> 固定
		int L = 2; // 操作区间的开始位置 -> 可变
		int R = 5; // 操作区间的结束位置 -> 可变
		int C = 4; // 要加的数字或者要更新的数字 -> 可变
		// 区间生成，必须在[S,N]整个范围上build
		seg.buildTree(S, N, root);
		// 区间修改，可以改变L、R和C的值，其他值不可改变
		seg.add(L, R, C, S, N, root);
		// 区间更新，可以改变L、R和C的值，其他值不可改变
		seg.update(L, R, C, S, N, root);
		// 区间查询，可以改变L和R的值，其他值不可改变
		long sum = seg.query(L, R, S, N, root);
		System.out.println(sum);

		System.out.println("对数器测试开始...");
		System.out.println("测试结果 : " + (test() ? "通过" : "未通过"));

	}
}
