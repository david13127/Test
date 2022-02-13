package com.mashibing.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月13日 11:10
 * @version V1.0
 */
public class BinaryTreeSearch {
	static class Node {
		Node left;
		Node right;
		int value;
	}

	public void preOrder(Node root) {
		System.out.println("pre-order: ");
		if (root != null) {
			Stack<Node> stack = new Stack<>();
			stack.push(root);
			while (!stack.isEmpty()) {
				root = stack.pop();
				System.out.print(root.value + " ");
				if (root.right != null) {
					stack.push(root.right);
				}
				if (root.left != null) {
					stack.push(root.left);
				}
			}
		}
		System.out.println();
	}

	public void inOrder(Node root) {
		System.out.println("in-order: ");
		if (root != null) {
			Stack<Node> stack = new Stack<>();
			while (!stack.isEmpty() || root != null) {
				if (root != null) {
					stack.push(root);
					root = root.left;
				} else {
					root = stack.pop();
					System.out.print(root.value + " ");
					root = root.right;
				}
			}
		}
		System.out.println();
	}

	public void postOrder(Node root) {
		System.out.println("post-order: ");
		if (root != null) {
			Stack<Node> stack1 = new Stack<>();
			Stack<Node> stack2 = new Stack<>();
			stack1.push(root);
			while (!stack1.isEmpty()) {
				root = stack1.pop();
				stack2.push(root);
				if (root.left != null) {
					stack1.push(root.left);
				}
				if (root.right != null) {
					stack1.push(root.right);
				}
			}
			while (!stack2.isEmpty()) {
				System.out.print(stack2.pop().value + "");
			}
		}
		System.out.println();
	}

	public void levelOrder(Node root) {
		System.out.println("level-order: ");
		if (root != null) {
			Queue<Node> queue = new LinkedList<>();
			queue.add(root);
			while (!queue.isEmpty()) {
				root = queue.poll();
				System.out.println(root.value);
				if (root.left != null) {
					queue.add(root.left);
				}
				if (root.right != null) {
					queue.add(root.right);
				}
			}
		}
		System.out.println();
	}
}
