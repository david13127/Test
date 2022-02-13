package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月13日 22:35
 * @version V1.0
 */
public class LongestSubstringWithAtMostKDistinctCharacters {
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		Map<Character, Integer> latestIndexMap = new HashMap<>();
		Set<Character> set = new HashSet<>();
		int ans = 0;
		int left = 0;
		int right = 0;
		latestIndexMap.put(s.charAt(0), 0);
		set.add(s.charAt(0));
		while (left <= right && right < s.length()) {
			char chR = s.charAt(right);
			set.add(chR);
			latestIndexMap.put(s.charAt(right), right);
			if (set.size() <= k) {
				right++;
				ans = Math.max(ans, right - left);
				continue;
			}
			char chL = s.charAt(left);
			if (latestIndexMap.containsKey(chL)) {
				if (latestIndexMap.get(chL) == left) {
					set.remove(chL);
				}
			}
			left++;
		}
		return ans;
	}

	public static void main(String[] args) {
		LongestSubstringWithAtMostKDistinctCharacters solution = new LongestSubstringWithAtMostKDistinctCharacters();
		String s = "eceba";
		int k = 2;
		int ans = solution.lengthOfLongestSubstringKDistinct(s, k);
		System.out.println(ans);
	}
}
