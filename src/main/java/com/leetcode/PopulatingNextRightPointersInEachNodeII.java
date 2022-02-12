package com.leetcode;

import java.util.LinkedList;
import java.util.Queue;

class Node {
	public int val;
	public Node left;
	public Node right;
	public Node next;

	public Node() {
	}

	public Node(int _val) {
		val = _val;
	}

	public Node(int _val, Node _left, Node _right, Node _next) {
		val = _val;
		left = _left;
		right = _right;
		next = _next;
	}
}
/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月29日 19:49
 * @version V1.0
 */
public class PopulatingNextRightPointersInEachNodeII {
	public Node connect(Node root) {
		if (root == null) {
			return null;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			Node cur = null;
			int i = 0;
			while (i < size) {
				if (cur == null) {
					cur = queue.poll();
					if (cur.left != null) {
						queue.offer(cur.left);
					}
					if (cur.right != null) {
						queue.offer(cur.right);
					}
				}
				if (i == size - 1) {
					cur.next = null;
				}
				else {
					Node next = queue.poll();
					if (next.left != null) {
						queue.offer(next.left);
					}
					if (next.right != null) {
						queue.offer(next.right);
					}
					cur.next = next;
					cur = next;
				}
				i++;
			}
		}
		return root;
	}
}
