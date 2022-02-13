package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月13日 18:06
 * @version V1.0
 */
public class LongestSubstringWithAtMostTwoDistinceCharacters {
	public int lengthOfLongestSubstringTwoDistinct(String s) {
		Map<Character, Integer> charMap = new HashMap<>();
		int left = 0;
		int right = 0;
		int ans = 0;
		charMap.put(s.charAt(0), 0);
		while (left <= right && right < s.length()) {
			// 左减右加
			while (right < s.length() && charMap.size() <= 2) {
				charMap.put(s.charAt(right), charMap.getOrDefault(s.charAt(right), 0) + 1);
				if (charMap.size() <= 2) {
					ans = Math.max(ans, right - left + 1);
				}
				right++;
			}
			Integer size1 = charMap.get(s.charAt(left));
			if (size1 <= 1) {
				charMap.remove(s.charAt(left));
			} else {
				charMap.put(s.charAt(left), charMap.get(s.charAt(left)) - 1);
			}
			left++;
		}
		return ans;
	}

	public static void main(String[] args) {
		LongestSubstringWithAtMostTwoDistinceCharacters solution = new LongestSubstringWithAtMostTwoDistinceCharacters();
		String s = "a";
		int ans = solution.lengthOfLongestSubstringTwoDistinct(s);
		System.out.println(ans);
	}
}
