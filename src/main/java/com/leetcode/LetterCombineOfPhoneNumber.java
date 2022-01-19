package com.leetcode;

import java.util.*;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2021年08月07日 20:06
 * @version V1.0
 */
public class LetterCombineOfPhoneNumber {
	private static List<String> letterCombinations(String digits) {
		List<String> result = new ArrayList<>();
		if (digits == null || digits.length() == 0) {
			return result;
		}
		Map<Character, List<Character>> map = new HashMap<>();
		map.put('2', Arrays.asList('a', 'b', 'c'));
		map.put('3', Arrays.asList('d', 'e', 'f'));
		map.put('4', Arrays.asList('g', 'h', 'i'));
		map.put('5', Arrays.asList('j', 'k', 'l'));
		map.put('6', Arrays.asList('m', 'n', 'o'));
		map.put('7', Arrays.asList('p', 'q', 'r', 's'));
		map.put('8', Arrays.asList('t', 'u', 'v'));
		map.put('9', Arrays.asList('w', 'x', 'y', 'z'));
		StringBuffer stack = new StringBuffer();
		dfs(result, stack, digits, 0, map);
		return result;
	}

	private static void dfs(List<String> result, StringBuffer stack, String digits, int index,
			Map<Character, List<Character>> map) {
		if (index < digits.length()) {
			Character num = digits.charAt(index);
			List<Character> characters = map.get(num);
			for (Character ch : characters) {
				stack.append(ch);
				dfs(result, stack, digits, index + 1, map);
				stack.deleteCharAt(stack.length() - 1);
			}
		} else {
			result.add(stack.toString());
		}
	}

	public static void main(String[] args) {
		String digits = "9";
		List<String> strings = letterCombinations(digits);
		System.out.println(String.join(",", strings));
	}
}
