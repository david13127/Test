package com.mashibing.algorithm.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月12日 21:58
 * @version V1.0
 */
public class BFS {
	public void bfs(Node start) {
		if (start == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<>();
		Set<Node> set = new HashSet<>();
		queue.add(start);
		set.add(start);
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			System.out.println(cur.value);
			for (Node next : cur.nexts) {
				if (set.contains(next)) {
					queue.add(next);
					set.add(next);
				}
			}
		}
	}
}
