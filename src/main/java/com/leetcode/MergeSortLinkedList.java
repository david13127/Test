package com.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description: 合并多个有序链表
 * @author : david
 * @date Date : 2021年03月20日 22:57
 * @version V1.0
 */
public class MergeSortLinkedList {
	public static class ListNode {
		private int value;
		private ListNode next;
	}

	public static class ListNodeComparator implements Comparator<ListNode> {
		@Override
		public int compare(ListNode o1, ListNode o2) {
			return o1.value - o2.value;
		}
	}
	public static ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}
		PriorityQueue<ListNode> heap = new PriorityQueue<>(new ListNodeComparator());
		for (int i = 0; i < lists.length; i++) {
			if (lists[i] != null) {
				heap.add(lists[i]);
			}
		}
		if (heap.isEmpty()) {
			return null;
		}
		ListNode head = heap.poll();
		ListNode pre = head;
		if (pre.next != null) {
			heap.add(pre.next);
		}
		while (!heap.isEmpty()) {
			ListNode cur = heap.poll();
			pre.next = cur;
			pre = cur;
			if (cur.next != null) {
				 heap.add(cur.next);
			}
		}
		return head;
	}
}
