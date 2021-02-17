package com.nuban.tree;

/**
 * @Description: 二叉树
 * @author : david
 * @date Date : 2021年02月10日 12:37
 * @version V1.0
 */
public class BinaryTree {
	public static void main(String[] args) {
		Node D = new Node("D", null, null);
		Node H = new Node("H", null, null);
		Node K = new Node("K", null, null);
		Node C = new Node("C", D, null);
		Node B = new Node("B", null, C);
		Node G = new Node("G", H, K);
		Node F = new Node("F", G, null);
		Node E = new Node("E", null, F);
		Node A = new Node("A", B, E);

		BinaryTree bTree = new BinaryTree();
		System.out.println("前序遍历：");
		bTree.pre(A);
		System.out.println();
		System.out.println("中序遍历：");
		bTree.mid(A);
		System.out.println();
		System.out.println("后序遍历：");
		bTree.post(A);
		System.out.println();
	}

	public void print(Node node) {
		System.out.print(node.getData());
	}
	public void pre(Node root) {
		print(root);
		if (root.getLeft() != null) {
			pre(root.getLeft());
		}
		if (root.getRight() != null) {
			pre(root.getRight());
		}
	}
	public void mid(Node root) {
		if (root.getLeft() != null) {
			mid(root.getLeft());
		}
		print(root);
		if (root.getRight() != null) {
			mid(root.getRight());
		}
	}
	public void post(Node root) {
		if (root.getLeft() != null) {
			post(root.getLeft());
		}
		if (root.getRight() != null) {
			post(root.getRight());
		}
		print(root);
	}
}

