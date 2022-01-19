package com.leetcode;

import java.util.*;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月15日 19:41
 * @version V1.0
 */
public class SmallestSubsequenceOfDistinctCharacters {
	public String smallestSubsequence(String s) {
		Map<Character, Integer> lastPosMap = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			lastPosMap.put(s.charAt(i), i);
		}
		Set<Character> visited = new HashSet<>();
		Deque<Character> stack = new ArrayDeque<>();
		for (int i = 0; i < s.length(); i++) {
			char curChar = s.charAt(i);
			if (!visited.contains(curChar)) {
				while (!stack.isEmpty() && stack.peek() > curChar && lastPosMap.get(stack.peek()) > i) {
					Character top = stack.pop();
					visited.remove(top);
				}
				stack.push(curChar);
				visited.add(curChar);
			}
		}
		StringBuilder stringBuilder = new StringBuilder();
		for (char c : stack) {
			stringBuilder.append(c);
		}
		return stringBuilder.reverse().toString();
	}

	public static void main(String[] args) {
		SmallestSubsequenceOfDistinctCharacters solution = new SmallestSubsequenceOfDistinctCharacters();
		String str = "bcabc";
		String ans = solution.smallestSubsequence(str);
		System.out.println(ans);
	}
}
