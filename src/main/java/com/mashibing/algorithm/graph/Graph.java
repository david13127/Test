package com.mashibing.algorithm.graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月12日 21:57
 * @version V1.0
 */
public class Graph {
	public HashMap<Integer, Node> nodes;
	public HashSet<Edge> edges;

	public Graph() {
		nodes = new HashMap<>();
		edges = new HashSet<>();
	}
}
