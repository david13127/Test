package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月19日 21:28
 * @version V1.0
 */
public class MinimumWindowSubstring {
	public String minWindow(String s, String t) {
		char[] charArrayS = s.toCharArray();
		char[] charArrayT = t.toCharArray();
		// 统计T字段串中字符的个数
		Map<Character, Integer> tFreqMap = new HashMap<>();
		for (char ch : charArrayT) {
			tFreqMap.put(ch, tFreqMap.getOrDefault(ch, 0) + 1);
		}
		long distance = tFreqMap.values().stream().filter(e -> e > 0).map(e -> 1).count();
		Map<Character, Integer> sFreqMap = new HashMap<>();
		int left = 0;
		int right = 0;
		int match = 0;
		int start = 0;
		int minLen = s.length() + 1;
		while (right < s.length()) {
			char ch = charArrayS[right];
			if (tFreqMap.getOrDefault(ch, 0) > 0) {
				sFreqMap.put(ch, sFreqMap.getOrDefault(ch, 0) + 1);
				if (sFreqMap.getOrDefault(ch, 0).equals(tFreqMap.getOrDefault(ch, 0))) {
					match++;
				}
			}
			right++;

			while (match == distance) {
				if (right - left < minLen) {
					start = left;
					minLen = right - left;
				}
				// left右移
				char leftChar = charArrayS[left];
				if (tFreqMap.getOrDefault(leftChar, 0) > 0) {
					sFreqMap.put(leftChar, sFreqMap.getOrDefault(leftChar, 0) - 1);
					if (sFreqMap.getOrDefault(leftChar, 0) < tFreqMap.getOrDefault(leftChar, 0)) {
						match--;
					}
				}
				left++;
			}
		}
		return minLen == s.length() + 1 ? "" : s.substring(start, start + minLen);
	}
}
