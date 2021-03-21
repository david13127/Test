package com.leetcode;

/**
 * @Description: 两数相加
 * https://leetcode-cn.com/problems/add-two-numbers/
 * @author : david
 * @date Date : 2021年03月13日 9:42
 * @version V1.0
 */
public class AddTwoNumbers {

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode pre = new ListNode(0);
		ListNode cur = pre;
		int carry = 0;
		while (l1 != null || l2 != null || carry != 0) {
			int num1 = l1 != null ? l1.val : 0;
			int num2 = l2 != null ? l2.val : 0;
			int sum = num1 + num2 + carry;
			carry = sum / 10;

			ListNode sumNode = new ListNode(sum % 10);
			cur.next = sumNode;
			cur = sumNode;

			if (l1 != null) {
				l1 = l1.next;
			}
			if (l2 != null) {
				l2 = l2.next;
			}
		}
		return pre.next;
	}

	public static void main(String[] args) {
		int[] values1 = {9, 9, 9, 9, 9, 9, 9};
		ListNode[] nodes1 = new ListNode[values1.length];
		ListNode node1 = new ListNode(values1[0]);
		nodes1[0] = node1;
		for (int i = 1; i < values1.length; i++) {
			ListNode next = new ListNode(values1[i]);
			nodes1[i] = next;
			nodes1[i - 1].next = next;
		}
		int[] values2 = {9, 9, 9, 9};
		ListNode[] nodes2 = new ListNode[values2.length];
		ListNode node2 = new ListNode(values2[0]);
		nodes2[0] = node2;
		for (int i = 1; i < values2.length; i++) {
			ListNode next = new ListNode(values2[i]);
			nodes2[i] = next;
			nodes2[i - 1].next = next;
		}
		AddTwoNumbers solution = new AddTwoNumbers();
		ListNode head = solution.addTwoNumbers(node1, node2);
		while (head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}

	}
}

/**
 * Definition for singly-linked list.
 */
class ListNode {
	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}