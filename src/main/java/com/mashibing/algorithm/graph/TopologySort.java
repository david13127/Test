package com.mashibing.algorithm.graph;

import java.util.*;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月12日 22:24
 * @version V1.0
 */
public class TopologySort {
	public List<Node> sortedTopology(Graph graph) {
		// key 某个节点   value 剩余的入度
		Map<Node, Integer> inMap = new HashMap<>();
		// 剩余入度为0的点,才进
		Queue<Node> zeroInQueue = new LinkedList<>();
		for (Node node : graph.nodes.values()) {
			inMap.put(node, node.in);
			if (node.in == 0) {
				zeroInQueue.add(node);
			}
		}
		List<Node> result = new ArrayList<>();
		while (!zeroInQueue.isEmpty()) {
			Node cur = zeroInQueue.poll();
			result.add(cur);
			for (Node next : cur.nexts) {
				inMap.put(next, inMap.get(next) - 1);
				if (inMap.get(next) == 0) {
					zeroInQueue.add(next);
				}
			}
		}
		return result;
	}
}
