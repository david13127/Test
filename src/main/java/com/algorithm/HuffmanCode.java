package com.algorithm;

/**
 * @Description: 哈夫曼编码
 * @author : david
 * @date Date : 2020年04月11日 11:35
 * @version V1.0
 */
public class HuffmanCode {
	private static int MAXLEAF = 30;
	private static int MAXNODE = 2 * MAXLEAF - 1;

	private static HNodeType[] huffNode;
	private static HCodeType[] huffCode;

	static {
		huffNode = new HNodeType[MAXNODE];
		huffCode = new HCodeType[MAXLEAF];
	}

	private static void huffTree(int n) {
		// 结点初始化，最多2n-1个结点
		init(n);

		// 构造Huffman树
		double weight1, weight2;
		int node1, node2;
		for (int i = 0; i < n - 1; i++) { // 执行n-1次合并
			// 找出所有结点中权值最小且无父结点的两个结点
			weight1 = weight2 = 1000;
			node1 = node2 = -1;
			for (int j = 0; j < n + i; j++) {
				if (huffNode[j].weight < weight1 && huffNode[j].parent == -1) {
					weight2 = weight1;
					node2 = node1;
					weight1 = huffNode[j].weight;
					node1 = j;
				} else if (huffNode[j].weight < weight2 && huffNode[j].parent == -1) {
					weight2 = huffNode[j].weight;
					node2 = j;
				}
			}

			// 设置找到的两个子结点x1, x2的父结点信息
			huffNode[node1].parent = n + i;
			huffNode[node2].parent = n + i;
			huffNode[n + i].weight = weight1 + weight2;
			huffNode[n + i].lChild = node1;
			huffNode[n + i].rChild = node2;
		}
	}

	private static void huffCode(int n) {
		int current, parent;
		for (int i = 0; i < n; i++) {
			HCodeType hCodeType = new HCodeType();
			hCodeType.start = n - 1;
			current = i;
			parent = huffNode[current].parent;
			while (parent != -1) {
				// 当前结点为左结点，编码为0
				if (huffNode[parent].lChild == current) {
					hCodeType.bit[hCodeType.start] = 0;
				}
				// 当前结点为右结点，编码为1
				if (huffNode[parent].rChild == current) {
					hCodeType.bit[hCodeType.start] = 1;
				}
				hCodeType.start--;
				current = parent;
				parent = huffNode[current].parent;
			}
			huffCode[i] = hCodeType;
		}
	}

	private static void init(int n) {
		for (int i = 0; i < 2 * n - 1; i++) {
			HNodeType hNodeType = new HNodeType();
			hNodeType.parent = -1;
			hNodeType.weight = 0;
			hNodeType.lChild = -1;
			hNodeType.rChild = -1;
			huffNode[i] = hNodeType;
		}
		huffNode[0].value = 'a';
		huffNode[0].weight = 5;
		huffNode[1].value = 'b';
		huffNode[1].weight = 32;
		huffNode[2].value = 'c';
		huffNode[2].weight = 18;
		huffNode[3].value = 'd';
		huffNode[3].weight = 7;
		huffNode[4].value = 'e';
		huffNode[4].weight = 25;
		huffNode[5].value = 'f';
		huffNode[5].weight = 13;
	}

	public static void main(String[] args) {
		int n = 6;
		huffTree(n);
		huffCode(n);
		for (int i = 0; i < n; i++) {
			System.out.println(huffNode[i].value + ": Huffman code is: ");
			for (int j = huffCode[i].start + 1; j < n; j++) {
				System.out.print(huffCode[i].bit[j]);
			}
			System.out.println();
		}
	}
}

class HNodeType {
	double weight;
	int parent;
	int lChild;
	int rChild;
	char value;
}

class HCodeType {
	int[] bit = new int[100];
	int start;
}