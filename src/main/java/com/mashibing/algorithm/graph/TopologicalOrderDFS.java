package com.mashibing.algorithm.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月12日 22:44
 * @version V1.0
 */
public class TopologicalOrderDFS {
	// 不要提交这个类
	public static class DirectedGraphNode {
		public int label;
		public ArrayList<DirectedGraphNode> neighbors;

		public DirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<DirectedGraphNode>();
		}
	}

	// 提交下面的
	public static class Record {
		public DirectedGraphNode node;
		public long nodes;

		public Record(DirectedGraphNode n, long o) {
			node = n;
			nodes = o;
		}
	}


	public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
		HashMap<DirectedGraphNode, Record> order = new HashMap<>();
		for (DirectedGraphNode cur : graph) {
			f(cur, order);
		}
		ArrayList<Record> recordArr = new ArrayList<>(order.values());
		recordArr.sort((o1, o2) -> Long.compare(o2.nodes, o1.nodes));
		ArrayList<DirectedGraphNode> ans = new ArrayList<DirectedGraphNode>();
		for (Record r : recordArr) {
			ans.add(r.node);
		}
		return ans;
	}

	// 当前来到cur点，请返回cur点所到之处，所有的点次！
	// 返回（cur，点次）
	// 缓存！！！！！order
	//  key : 某一个点的点次，之前算过了！
	//  value : 点次是多少
	public static Record f(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {
		if (order.containsKey(cur)) {
			return order.get(cur);
		}
		// cur的点次之前没算过！
		long nodes = 0;
		for (DirectedGraphNode next : cur.neighbors) {
			nodes += f(next, order).nodes;
		}
		Record ans = new Record(cur, nodes + 1);
		order.put(cur, ans);
		return ans;
	}
}
