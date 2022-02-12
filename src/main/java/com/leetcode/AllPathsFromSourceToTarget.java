package com.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月30日 19:34
 * @version V1.0
 */
public class AllPathsFromSourceToTarget {
	public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
		if (graph.length == 0 || graph[0].length == 0) {
			return new ArrayList<>();
		}
		List<List<Integer>> ans = new ArrayList<>();
		Stack<Integer> stack = new Stack<>();
		int begin = 0;
		int[] nextPoints = graph[begin];
		Stack<Integer> path = new Stack<>();
		path.add(0);
		Arrays.stream(nextPoints).sorted().filter(e -> e != 0).forEach(stack::add);
		while (!stack.isEmpty()) {
			Integer poll = stack.pop();
			path.add(poll);
			if (poll.equals(graph.length - 1)) {
				ans.add(new ArrayList<>(path));
				while (!stack.isEmpty() && path.peek() > stack.peek()) {
					path.pop();
				}
			}
			int[] nexts = graph[poll];
			Arrays.stream(nexts).sorted().filter(e -> e != 0).forEach(stack::add);
		}
		return ans;
	}

	public static void main(String[] args) {
		AllPathsFromSourceToTarget solution = new AllPathsFromSourceToTarget();
		int[][] gragh = {{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}};
		List<List<Integer>> lists = solution.allPathsSourceTarget(gragh);
		lists.forEach(list -> System.out.println(list.stream().map(e -> e + "").collect(Collectors.joining(","))));
	}
}
