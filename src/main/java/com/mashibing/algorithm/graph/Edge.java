package com.mashibing.algorithm.graph;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月12日 21:57
 * @version V1.0
 */
public class Edge {
	public int weight;
	public Node from;
	public Node to;

	public Edge(int weight, Node from, Node to) {
		this.weight = weight;
		this.from = from;
		this.to = to;
	}
}
