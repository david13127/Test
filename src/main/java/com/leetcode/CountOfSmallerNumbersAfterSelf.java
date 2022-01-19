package com.leetcode;

import java.util.*;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月16日 21:41
 * @version V1.0
 */
public class CountOfSmallerNumbersAfterSelf {
	public List<Integer> countSmaller(int[] nums) {
		List<Integer> res = new ArrayList<>();
		int len = nums.length;
		if (len == 0) {
			return res;
		}

		// 使用二分搜索树方便排序
		Set<Integer> set = new TreeSet();
		for (int i = 0; i < len; i++) {
			set.add(nums[i]);
		}

		// 排名表
		Map<Integer, Integer> map = new HashMap<>();
		int rank = 1;
		for (Integer num : set) {
			map.put(num, rank);
			rank++;
		}

		FenwickTree fenwickTree = new FenwickTree(set.size() + 1);
		// 从后向前填表
		for (int i = len - 1; i >= 0; i--) {
			// 1、查询排名
			rank = map.get(nums[i]);
			// 2、在树状数组排名的那个位置 + 1
			fenwickTree.update(rank, 1);
			// 3、查询一下小于等于“当前排名 - 1”的元素有多少
			res.add(fenwickTree.query(rank - 1));
		}
		Collections.reverse(res);
		return res;
	}


	private static class FenwickTree {
		private final int[] tree;
		private final int len;

		public FenwickTree(int n) {
			this.len = n;
			tree = new int[n + 1];
		}

		// 单点更新：将 index 这个位置 + 1
		public void update(int i, int delta) {
			// 从下到上，最多到 size，可以等于 size
			while (i <= this.len) {
				tree[i] += delta;
				i += lowbit(i);
			}
		}


		// 区间查询：查询小于等于 index 的元素个数
		// 查询的语义是"前缀和"
		public int query(int i) {
			// 从右到左查询
			int sum = 0;
			while (i > 0) {
				sum += tree[i];
				i -= lowbit(i);
			}
			return sum;
		}

		public int lowbit(int x) {
			return x & (-x);
		}
	}


	public static void main(String[] args) {
		int[] nums = new int[]{5, 2, 6, 1};
		CountOfSmallerNumbersAfterSelf solution = new CountOfSmallerNumbersAfterSelf();
		List<Integer> countSmaller = solution.countSmaller(nums);
		System.out.println(countSmaller);
	}
}
