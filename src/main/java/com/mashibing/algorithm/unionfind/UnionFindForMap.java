package com.mashibing.algorithm.unionfind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月12日 17:55
 * @version V1.0
 */
public class UnionFindForMap<V> {
	Map<V, Node<V>> nodes;
	Map<Node<V>, Node<V>> parents;
	Map<Node<V>, Integer> sizeMap;

	public UnionFindForMap(List<V> values) {
		nodes = new HashMap<>();
		parents = new HashMap<>();
		sizeMap = new HashMap<>();
		for (V cur : values) {
			Node<V> node = new Node<>(cur);
			nodes.put(cur, node);
			parents.put(node, node);
			sizeMap.put(node, 1);
		}
	}

	private Node<V> findFather(Node<V> cur) {
		Stack<Node<V>> path = new Stack<>();
		while (cur != parents.get(cur)) {
			path.push(cur);
			cur = parents.get(cur);
		}
		// 把路径上的所有元素直接挂到根节点, 下次查找优化复杂度
		while (!path.isEmpty()) {
			parents.put(path.pop(), cur);
		}
		return cur;
	}

	public boolean isSameSet(V a, V b) {
		return findFather(nodes.get(a)) == findFather(nodes.get(b));
	}

	public void union(V a, V b) {
		Node<V> aHead = findFather(nodes.get(a));
		Node<V> bHead = findFather(nodes.get(b));
		if (aHead != bHead) {
			int aSetSize = sizeMap.get(aHead);
			int bSetSize = sizeMap.get(bHead);
			Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
			Node<V> small = big == aHead ? bHead : aHead;
			parents.put(small, big);
			sizeMap.put(big, aSetSize + bSetSize);
			sizeMap.remove(small);
		}
	}

	static class Node<V> {
		V value;

		Node(V v) {
			this.value = v;
		}
	}

}
