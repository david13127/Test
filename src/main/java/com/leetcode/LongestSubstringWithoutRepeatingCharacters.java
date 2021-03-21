package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 无重复字符的最长子串
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * @author : david
 * @date Date : 2021年03月13日 10:13
 * @version V1.0
 */
public class LongestSubstringWithoutRepeatingCharacters {
	public int lengthOfLongestSubstring(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		Map<Character, Integer> map = new HashMap<>();
		int max = 0;
		int left = 0;
		for (int right = 0; right < s.length(); right++) {
			if (map.containsKey(s.charAt(right))) {
				left = Math.max(left, map.get(s.charAt(right)) + 1);
			}
			map.put(s.charAt(right), right);
			max = Math.max(max, right - left + 1);
		}
		return max;
	}

	public static void main(String[] args) {
		LongestSubstringWithoutRepeatingCharacters solution = new LongestSubstringWithoutRepeatingCharacters();
		String str = "pwwkew";
		int length = solution.lengthOfLongestSubstring(str);
		System.out.println(length);
	}
}
