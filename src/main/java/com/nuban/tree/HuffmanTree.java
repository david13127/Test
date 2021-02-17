package com.nuban.tree;

import java.util.*;

/**
 * @Description: HuffmanTree
 * @author : david
 * @date Date : 2021年02月10日 22:44
 * @version V1.0
 */
public class HuffmanTree {
	private HuffmanNode root;
	private List<HuffmanNode> leaves = new ArrayList<>();
	private Map<String, Integer> weightMap;

	private Map<String, String> code() {
		Map<String, String> map = new HashMap<>();
		this.leaves.forEach(node -> {
			String value = (String) node.getData();
			StringBuilder code = new StringBuilder();
			HuffmanNode curNode = node;
			do {
				if (curNode.getParent() != null) {
					if (curNode.getParent().getLeft() == curNode) {
						code.insert(0, "0");
					} else {
						code.insert(0, "1");
					}
					curNode = curNode.getParent();
				}
			} while (curNode.getParent() != null);
			map.put(value, code.toString());
		});
		return map;
	}

	public HuffmanTree(Map<String, Integer> weightMap) {
		this.weightMap = weightMap;
	}

	private void createTree() {
		PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
		weightMap.forEach((key, value) -> {
			HuffmanNode node = new HuffmanNode(key, value, null, null);
			priorityQueue.add(node);
			this.leaves.add(node);
		});

		int len = priorityQueue.size();
		for (int i = 0; i < len - 1; i++) {
			HuffmanNode node1 = priorityQueue.poll();
			HuffmanNode node2 = priorityQueue.poll();
			assert node1 != null;
			assert node2 != null;
			int weight = node1.getWeight() + node2.getWeight();
			HuffmanNode parentNode = new HuffmanNode(null, weight, node1, node2);
			node1.setParent(parentNode);
			node2.setParent(parentNode);
			priorityQueue.add(parentNode);
		}
		root = priorityQueue.poll();
	}

	public static void main(String[] args) {
		Map<String, Integer> weightMap = new HashMap<>();
		weightMap.put("A", 7);
		weightMap.put("B", 5);
		weightMap.put("C", 2);
		weightMap.put("D", 4);

		HuffmanTree huffmanTree = new HuffmanTree(weightMap);
		huffmanTree.createTree();
		Map<String, String> codeMap = huffmanTree.code();
		System.out.println("A:" + codeMap.get("A"));
		System.out.println("B:" + codeMap.get("B"));
		System.out.println("C:" + codeMap.get("C"));
		System.out.println("D:" + codeMap.get("D"));

		System.out.println("AB的密文:" + (codeMap.get("A") + codeMap.get("B")));
	}
}

class HuffmanNode<T extends Comparable<T>> extends Node{
	private HuffmanNode parent;
	private int weight;

	HuffmanNode(T data, int weight, Node left, Node right) {
		super(data, left, right);
		this.weight = weight;
	}

	public HuffmanNode getParent() {
		return parent;
	}

	public void setParent(HuffmanNode parent) {
		this.parent = parent;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {
		HuffmanNode node = (HuffmanNode) o;
		return this.weight - node.weight;
	}
}