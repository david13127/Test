package com.mashibing.algorithm;

/**
 * @Description: 前缀树
 * @author : david
 * @date Date : 2021年03月21日 22:45
 * @version V1.0
 */
public class TrieTree {
	public static class Node1 {
		private int pass;
		private int end;
		private Node1[] nexts;

		public Node1 () {
			pass = 0;
			end = 0;
			// 0   a
			// 1   b
			// .   .
			// 25  z
			// nexts[i] == null i方向的路不存在
			// nexts[i] != null i方向的路存在
			nexts = new Node1[26];
		}
	}

	public static class Trie1 {
		private Node1 root;
		public Trie1() {
			root = new Node1();
		}

		public void insert(String word) {
			if (word == null || word.length() == 0) {
				return;
			}
			char[] str = word.toCharArray();
			Node1 node = root;
			int path = 0;
			for (int i = 0; i < str.length; i++) {
				path = str[i] - 'a';
				if (node.nexts[path] == null) {
					node.nexts[path] = new Node1();
				}
				node = node.nexts[path];
				node.pass++;
			}
			node.end++;
		}

		public void delete(String word) {
			if (search(word) != 0) {
				char[] str = word.toCharArray();
				Node1 node = root;
				node.pass--;
				int path = 0;
				for (int i = 0; i < str.length; i++) {
					path = str[i] - 'a';
					if (--node.nexts[path].pass == 0) {
						node.nexts[path] = null;
						return;
					}
					node = node.nexts[path];
				}
				node.end--;
			}
		}

		public int search(String word) {
			if (word == null || word.length() == 0) {
				return 0;
			}
			char[] str = word.toCharArray();
			Node1 node = root;
			int index = 0;
			for (int i = 0; i < str.length; i++) {
				index = str[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			return node.end;
		}

		public int prefixNumber(String pre) {
			if (pre == null || pre.length() == 0) {
				return 0;
			}
			char[] str = pre.toCharArray();
			Node1 node = root;
			int index = 0;
			for (int i = 0; i < str.length; i++) {
				index = str[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			return node.pass;
		}
	}

	public static void main(String[] args) {
		System.out.println(1.1f -1.0f == 0.1f);
	}
}
