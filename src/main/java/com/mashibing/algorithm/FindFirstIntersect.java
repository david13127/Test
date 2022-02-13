package com.mashibing.algorithm;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月13日 10:14
 * @version V1.0
 */
public class FindFirstIntersect {
	static class Node {
		int value;
		Node next;
	}

	public Node getIntersectNode(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		Node loopNode1 = getLoopNode(head1);
		Node loopNode2 = getLoopNode(head2);
		if (loopNode1 == null && loopNode2 == null) {
			// 两个链表都无环
			return noLoop(head1, head2);
		}
		if (loopNode1 != null && loopNode2 != null) {
			// 两个链表都有环
			return bothLoop(head1, head2, loopNode1, loopNode2);
		}
		// 任意一个有环，另一个无环，则不相交
		return null;
	}

	private Node bothLoop(Node head1, Node head2, Node loopNode1, Node loopNode2) {
		if (loopNode1 == loopNode2) {
			Node cur1 = head1;
			Node cur2 = head2;
			int n = 0;
			while (cur1 != loopNode1) {
				n++;
				cur1 = cur1.next;
			}
			while (cur2 != loopNode2) {
				n--;
				cur2 = cur2.next;
			}
			cur1 = n > 0 ? head1 : head2;
			cur2 = cur1 == head1 ? head2 : head1;
			n = Math.abs(n);
			while (n != 0) {
				n--;
				cur1 = cur1.next;
			}
			while (cur1 != cur2) {
				cur1 = cur1.next;
				cur2 = cur2.next;
			}
			return cur1;
		} else {
			Node cur1 = loopNode1.next;
			while (cur1 != loopNode1) {
				if (cur1 == loopNode2) {
					return loopNode1;
				}
				cur1 = cur1.next;
			}
			return null;
		}
	}

	private Node noLoop(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		Node cur1 = head1;
		Node cur2 = head2;
		int n = 0;
		while (cur1.next != null) {
			n++;
			cur1 = cur1.next;
		}
		while (cur2.next != null) {
			n--;
			cur2 = cur2.next;
		}
		if (cur1 != cur2) {
			return null;
		}
		cur1 = n > 0 ? head1 : head2;
		cur2 = cur1 == head1 ? head2 : head1;
		n = Math.abs(n);
		while (n != 0) {
			n--;
			cur1 = cur1.next;
		}
		while (cur1 != cur2) {
			cur1 = cur1.next;
			cur2 = cur2.next;
		}
		return cur1;
	}

	private Node getLoopNode(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		Node slow = head.next;
		Node fast = head.next.next;
		while (slow != fast) {
			if (fast.next == null || fast.next.next == null) {
				return null;
			}
			fast = fast.next.next;
			slow = slow.next;
		}
		fast = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return slow;
	}
}
