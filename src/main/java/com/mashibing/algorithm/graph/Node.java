package com.mashibing.algorithm.graph;

import java.util.ArrayList;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月12日 21:57
 * @version V1.0
 */
public class Node {
	public int value;
	public int in;
	public int out;
	public ArrayList<Node> nexts;
	public ArrayList<Edge> edges;

	public Node(int value) {
		this.value = value;
		in = 0;
		out = 0;
		nexts = new ArrayList<>();
		edges = new ArrayList<>();
	}
}
