package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 424. 替换后的最长重复字符  -- 1004 1208 1493
 * https://leetcode-cn.com/problems/longest-repeating-character-replacement/
 */
public class LongestRepeatingCharacterReplacement {
	public int characterReplacement(String s, int k) {
		int len = s.length();
		if (len < 2) {
			return len;
		}
		char[] charArray = s.toCharArray();
		Map<Character, Integer> freq = new HashMap<>();
		int max = 0;

		int left = 0;
		int right = 0;
		// [left, right)
		while (right < len) {
			freq.put(charArray[right], freq.getOrDefault(charArray[right], 0) + 1);
			max = Math.max(max, freq.get(charArray[right]));
			right++;
			if (right - left > max + k) {
				freq.put(charArray[left], freq.getOrDefault(charArray[left], 0) - 1);
				left++;
			}
		}
		return right - left;
	}
}
