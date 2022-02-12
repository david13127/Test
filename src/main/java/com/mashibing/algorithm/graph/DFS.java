package com.mashibing.algorithm.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月12日 22:01
 * @version V1.0
 */
public class DFS {
	public void dfs1(Node start, Set<Node> set) {
		if (start == null) {
			return;
		}
		set.add(start);
		System.out.println(start.value);
		for (Node next : start.nexts) {
			if (!set.contains(next)) {
				dfs1(next, set);
			}
		}
	}

	public void dfs2(Node start) {
		if (start == null) {
			return;
		}
		Stack<Node> stack = new Stack<>(); // 栈中保存着遍历到所在节点时的路径
		Set<Node> set = new HashSet<>();
		stack.push(start);
		set.add(start);
		System.out.println(start.value);
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			for (Node next : cur.nexts) {
				if (!set.contains(next)) {
					stack.push(cur);
					stack.push(next);
					set.add(next);
					System.out.println(next.value);
					break;
				}
			}
		}
	}
}
