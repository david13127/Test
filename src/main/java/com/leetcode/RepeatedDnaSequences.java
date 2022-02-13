package com.leetcode;

import java.util.*;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月13日 19:36
 * @version V1.0
 */
public class RepeatedDnaSequences {
	public List<String> findRepeatedDnaSequences(String s) {
		int k = 10;
		if (s.length() <= k) {
			return new ArrayList<>();
		}
		Set<String> ans = new HashSet<>();
		int left = 0;
		Map<String, Integer> sizeMap = new HashMap<>();
		while (left + k <= s.length()) {
			String str = s.substring(left, left + k);
			sizeMap.put(str, sizeMap.getOrDefault(str, 0) + 1);
			if (sizeMap.get(str) > 1) {
				ans.add(str);
			}
			left++;
		}
		return new ArrayList<>(ans);
	}

	public static void main(String[] args) {
		RepeatedDnaSequences solution = new RepeatedDnaSequences();
		String s = "AAAAAAAAAAA";
		List<String> ans = solution.findRepeatedDnaSequences(s);
		ans.forEach(System.out::println);
	}
}
