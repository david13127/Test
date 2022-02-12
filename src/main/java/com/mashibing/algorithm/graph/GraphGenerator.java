package com.mashibing.algorithm.graph;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月12日 22:13
 * @version V1.0
 */
public class GraphGenerator {
	// matrix 所有的边
	// N*3 的矩阵
	// [weight, from节点上面的值，to节点上面的值]
	//
	// [ 5 , 0 , 7]
	// [ 3 , 0,  1]
	//
	public static Graph createGraph(int[][] matrix) {
		Graph graph = new Graph();
		for (int[] ints : matrix) {
			// 拿到每一条边， matrix[i]
			int weight = ints[0];
			int from = ints[1];
			int to = ints[2];
			if (!graph.nodes.containsKey(from)) {
				graph.nodes.put(from, new Node(from));
			}
			if (!graph.nodes.containsKey(to)) {
				graph.nodes.put(to, new Node(to));
			}
			Node fromNode = graph.nodes.get(from);
			Node toNode = graph.nodes.get(to);
			Edge newEdge = new Edge(weight, fromNode, toNode);
			fromNode.nexts.add(toNode);
			fromNode.out++;
			toNode.in++;
			fromNode.edges.add(newEdge);
			graph.edges.add(newEdge);
		}
		return graph;
	}
}
