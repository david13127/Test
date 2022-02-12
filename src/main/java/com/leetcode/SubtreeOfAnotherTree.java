package com.leetcode;

import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode() {
	}

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月29日 20:26
 * @version V1.0
 */
public class SubtreeOfAnotherTree {
	public boolean isSubtree(TreeNode root, TreeNode subRoot) {
		if (subRoot == null) {
			return true;
		}
		if (root == null) {
			return false;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			if (!isSame(node, subRoot)) {
				queue.offer(node.left);
				queue.offer(node.right);
			} else {
				return true;
			}
		}
		return false;
	}

	private boolean isSame(TreeNode root1, TreeNode root2) {
		Queue<TreeNode> queue1 = new LinkedList<>();
		Queue<TreeNode> queue2 = new LinkedList<>();
		queue1.offer(root1);
		queue2.offer(root2);
		while (!queue1.isEmpty() && !queue2.isEmpty()) {
			if (queue1.size() != queue2.size()) {
				return false;
			} else {
				TreeNode poll1 = queue1.poll();
				TreeNode poll2 = queue2.poll();
				if (poll1 != null && poll2 != null) {
					if (poll1.val == poll2.val) {
						queue1.offer(poll1.left);
						queue1.offer(poll1.right);
						queue2.offer(poll2.left);
						queue2.offer(poll2.right);
					} else {
						return false;
					}
				} else if (!(poll1 == null && poll2 == null)) {
					return false;
				}
			}
		}
		return queue1.isEmpty() && queue2.isEmpty();
	}
}
