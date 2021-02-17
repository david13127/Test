package com.nuban.tree;

/**
 * @Description: 二叉查找树
 * @author : david
 * @date Date : 2021年02月10日 14:54
 * @version V1.0
 */
public class BinarySearchTree<T extends Comparable<T>> extends Node {

	public BinarySearchTree(T data) {
		super(data, null, null);
	}

	public void insert(Node root, T data) {
		if (root.getData().compareTo(data) < 0) {
			// 根结点小于data，插右子树
			if (root.getRight() == null) {
				root.setRight(new BinarySearchTree(data));
			} else {
				insert(root.getRight(), data);
			}
		} else {
			if (root.getLeft() == null) {
				root.setLeft(new BinarySearchTree(data));
			} else {
				insert(root.getLeft(), data);
			}
		}
	}

	public void pre(Node root) {
		if (root != null) {
			System.out.print(root.getData() + " ");
			pre(root.getLeft());
			pre(root.getRight());
		}
	}

	public void mid(Node root) {
		if (root != null) {
			mid(root.getLeft());
			System.out.print(root.getData() + " ");
			mid(root.getRight());
		}
	}

	public void post(Node root) {
		if (root != null) {
			post(root.getLeft());
			post(root.getRight());
			System.out.print(root.getData() + " ");
		}
	}

	public Node find(Node root, T data) {
		Node currentNode = root;
		while (currentNode != null && data != currentNode.getData()) {
			if (data.compareTo((T) currentNode.getData()) < 0) {
				currentNode = currentNode.getLeft();
			} else {
				currentNode = currentNode.getRight();
			}
		}
		return currentNode;
	}

	public static void main(String[] args) {
		int[] data = {0, 5, 9, 1, 2, 3, 10};
		BinarySearchTree root = new BinarySearchTree(data[0]);
		for (int i = 1; i < data.length; i++) {
			root.insert(root, data[i]);
		}
		System.out.println("中序：");
		root.mid(root);
		System.out.println();
		BinarySearchTree find = (BinarySearchTree) root.find(root, 10);
		if (find != null) {
			System.out.println("找到：" + find.getData());
		} else {
			System.out.println("没有找到：" + data);
		}
	}
}
