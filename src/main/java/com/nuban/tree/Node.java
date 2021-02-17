package com.nuban.tree;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2021年02月14日 10:33
 * @version V1.0
 */
public class Node<T extends Comparable<T>> implements Comparable<Node> {
	private T data;
	private Node left;
	private Node right;

	Node() {
	}

	Node(T data, Node left, Node right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	@Override
	public int compareTo(Node o) {
		return this.getData().compareTo((T) o.getData());
	}
}