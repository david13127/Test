package com.mashibing.algorithm.tree;

import com.nuban.tree.BinarySearchTree;
import com.nuban.tree.Node;

/**
 * @Description: morris树遍历
 * @author : david
 * @date Date : 2021年02月14日 10:31
 * @version V1.0
 */
public class MorrisTravel<T extends Comparable<T>> {
	public void morrisIn(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight;
		while (cur != null) {
			mostRight = cur.getLeft();
			// 有左树
			if (mostRight != null) {
				// 找到左树的真实的最右结点，即左树最右结点，并且最右结点不是自己
				// mostRight.getRight() == null第一次来到此结点，mostRight.getRight() == cur第二次来到此结点
				while (mostRight.getRight() != null && mostRight.getRight() != cur) {
					mostRight = mostRight.getRight();
				}
				if (mostRight.getRight() == null) { // 第一次来到此结点
					mostRight.setRight(cur); // 将其右结点指向自己
					cur = cur.getLeft(); // 并且往左移动
					continue;
				} else { // 第二次来到此结点
					mostRight.setRight(null); // 将其右结点指向null
				}
			}
			// 没有左树
			System.out.print(cur.getData() + " ");
			cur = cur.getRight();
		}
		System.out.println();
	}

	public void morrisPre(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight;
		while (cur != null) {
			mostRight = cur.getLeft();
			// 有左树
			if (mostRight != null) {
				// 找到左树的真实的最右结点，即左树最右结点，并且最右结点不是自己
				// mostRight.getRight() == null第一次来到此结点，mostRight.getRight() == cur第二次来到此结点
				while (mostRight.getRight() != null && mostRight.getRight() != cur) {
					mostRight = mostRight.getRight();
				}
				if (mostRight.getRight() == null) { // 第一次来到此结点
					mostRight.setRight(cur); // 将其右结点指向自己
					System.out.print(cur.getData() + " ");
					cur = cur.getLeft(); // 并且往左移动
					continue;
				} else { // 第二次来到此结点
					mostRight.setRight(null); // 将其右结点指向null
				}
			} else {
				System.out.print(cur.getData() + " ");
			}
			// 没有左树
			cur = cur.getRight();
		}
		System.out.println();
	}

	public void morrisPost(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight;
		while (cur != null) {
			mostRight = cur.getLeft();
			// 有左树
			if (mostRight != null) {
				// 找到左树的真实的最右结点，即左树最右结点，并且最右结点不是自己
				// mostRight.getRight() == null第一次来到此结点，mostRight.getRight() == cur第二次来到此结点
				while (mostRight.getRight() != null && mostRight.getRight() != cur) {
					mostRight = mostRight.getRight();
				}
				if (mostRight.getRight() == null) { // 第一次来到此结点
					mostRight.setRight(cur); // 将其右结点指向自己
					cur = cur.getLeft(); // 并且往左移动
					continue;
				} else { // 第二次来到此结点
					mostRight.setRight(null); // 将其右结点指向null
					// 打印cur左树的右边界，逆序
					outPutRightEdge(cur.getLeft());
				}
			}
			// 没有左树
			cur = cur.getRight();
		}
		outPutRightEdge(head);
		System.out.println();
	}

	public boolean isBST(Node head) {
		if (head == null) {
			return true;
		}
		Node cur = head;
		Node mostRight;

		T pre = null;
		boolean ans = true;
		while (cur != null) {
			mostRight = cur.getLeft();
			// 有左树
			if (mostRight != null) {
				// 找到左树的真实的最右结点，即左树最右结点，并且最右结点不是自己
				// mostRight.getRight() == null第一次来到此结点，mostRight.getRight() == cur第二次来到此结点
				while (mostRight.getRight() != null && mostRight.getRight() != cur) {
					mostRight = mostRight.getRight();
				}
				if (mostRight.getRight() == null) { // 第一次来到此结点
					mostRight.setRight(cur); // 将其右结点指向自己
					cur = cur.getLeft(); // 并且往左移动
					continue;
				} else { // 第二次来到此结点
					mostRight.setRight(null); // 将其右结点指向null
				}
			}
			// 没有左树
			if (pre != null && pre.compareTo((T) cur.getData()) >= 0) {
				ans = false;
			}
			pre = (T) cur.getData();
			cur = cur.getRight();
		}
		return ans;
	}

	// 根据morris遍历改写
	public int minHeight(Node head) {
		if (head == null) {
			return 0;
		}
		Node cur = head;
		Node mostRight;
		int curLevel = 0;
		int minHeight = Integer.MAX_VALUE;
		while (cur != null) {
			mostRight = cur.getLeft();
			if (mostRight != null) {
				int rightBoardSize = 1;
				while (mostRight.getRight() != null && mostRight.getRight() != cur) {
					rightBoardSize++;
					mostRight = mostRight.getRight();
				}
				if (mostRight.getRight() == null) { // 第一次到达
					curLevel++;
					mostRight.setRight(cur);
					cur = cur.getLeft();
					continue;
				} else { // 第二次到达
					if (mostRight.getLeft() == null) {
						minHeight = Math.min(minHeight, curLevel);
					}
					curLevel -= rightBoardSize;
					mostRight.setRight(null);
				}
			} else { // 只有一次到达
				curLevel++;
			}
			cur = cur.getRight();
		}
		int finalRight = 1;
		cur = head;
		while (cur.getRight() != null) {
			finalRight++;
			cur = cur.getRight();
		}
		if (cur.getLeft() == null && cur.getRight() == null) {
			minHeight = Math.min(minHeight, finalRight);
		}
		return minHeight;
	}

	private void outPutRightEdge(Node head) {
		Node tail = reverseRightEdge(head);
		Node cur = tail;
		while (cur != null) {
			System.out.print(cur.getData() + " ");
			cur = cur.getRight();
		}
		reverseRightEdge(tail);
	}

	private Node reverseRightEdge(Node from) {
		Node pre = null;
		Node next;
		while (from != null) {
			next = from.getRight();
			from.setRight(pre);
			pre = from;
			from = next;
		}
		return pre;
	}

	public static void main(String[] args) {
		int[] data = {0, 5, 9, 1, 2, 3, 10};
		BinarySearchTree root = new BinarySearchTree(data[0]);
		for (int i = 1; i < data.length; i++) {
			root.insert(root, data[i]);
		}
		MorrisTravel morrisTravel = new MorrisTravel();
		root.pre(root);
		System.out.println();
		morrisTravel.morrisPre(root);
		root.mid(root);
		System.out.println();
		morrisTravel.morrisIn(root);
		root.post(root);
		System.out.println();
		morrisTravel.morrisPost(root);
		System.out.println(morrisTravel.isBST(root));
		System.out.println(morrisTravel.minHeight(root));
	}
}
