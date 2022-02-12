package com.mashibing.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月12日 10:09
 * @version V1.0
 */
public class ClosetCommonParent {
	public Node lowestAncestor(Node head, Node o1, Node o2) {
		if (head == null) {
			return null;
		}
		Map<Node, Node> parentMap = new HashMap<>();
		parentMap.put(head, null);
		fillParentMap(head, parentMap);
		Set<Node> o1Set = new HashSet<>();
		Node cur = o1;
		o1Set.add(cur);
		while (parentMap.get(cur) != null) {
			cur = parentMap.get(cur);
			o1Set.add(cur);
		}
		cur = o2;
		while (!o1Set.contains(cur)) {
			cur = parentMap.get(cur);
		}
		return cur;
	}

	public Node lowestAncestor2(Node head, Node a, Node b) {
		return process(head, a, b).ans;
	}
	private Info process(Node x, Node a, Node b) {
		if (x == null) {
			return new Info(false, false, null);
		}
		Info leftInfo = process(x.left, a, b);
		Info rightInfo = process(x.right, a, b);
		boolean findA = (x == a) || leftInfo.findA || rightInfo.findA;
		boolean findB = (x == b) || leftInfo.findB || rightInfo.findB;
		Node ans = null;
		if (leftInfo.ans != null) {
			ans = leftInfo.ans;
		} else if (rightInfo.ans != null) {
			ans = rightInfo.ans;
		} else {
			if (findA && findB) {
				ans = x;
			}
		}
		return new Info(findA, findB, ans);
	}

	private void fillParentMap(Node head, Map<Node, Node> parentMap) {
		if (head == null) {
			return;
		}
		if (head.left != null) {
			parentMap.put(head.left, head);
		}
		if (head.right != null) {
			parentMap.put(head.right, head);
		}
		fillParentMap(head.left, parentMap);
		fillParentMap(head.right, parentMap);
	}

	static class Info {
		boolean findA;
		boolean findB;
		Node ans;

		Info(boolean fa, boolean fb, Node an) {
			this.findA = fa;
			this.findB = fb;
			this.ans = an;
		}
	}

	static class Node {
		Node left;
		Node right;
		int value;

		Node(int data) {
			this.value = data;
		}
	}
}
