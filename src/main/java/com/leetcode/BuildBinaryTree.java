package com.leetcode;

/**
 * @Description: 通过二叉树的前序和中序遍历建立二叉树
 * @author : david
 * @date Date : 2021年03月20日 23:31
 * @version V1.0
 */
public class BuildBinaryTree {
	public static class TreeNode {
		private int value;
		private TreeNode left;
		private TreeNode right;

		public TreeNode(int val) {
			this.value = val;
		}
	}

	public static TreeNode buildTree(int[] pre, int[] in) {
		if (pre == null || in == null || pre.length != in.length) {
			return null;
		}
		return createNode(pre, 0, pre.length - 1, in, 0, in.length - 1);
	}

	public static TreeNode createNode(int[] pre, int l1, int r1, int[] in, int l2, int r2) {
		if (l1 > r1) {
			return null;
		}
		TreeNode root = new TreeNode(pre[l1]);
		if (l1 == r1) {
			return root;
		}
		int find = l2;
		// 遍历过程可以优化为一个map，存储值与序号的映射
		while (in[find] != pre[l1]) {
			find++;
		}
		root.left = createNode(pre, l1 + 1, l1 + find - l2, in, l2, find - 1);
		root.right = createNode(pre, l1 + find - l2 + 1, r1, in, find + 1, r2);
		return root;
	}

}
