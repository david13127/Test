package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：edelweisskoko
 * 	链接：https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/solution/30-chuan-lian-suo-you-dan-ci-de-zi-chuan-bvy9/
 * 	来源：力扣（LeetCode）
 * 	著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class SubstringWithConcatenationOfAllWords {
	public List<Integer> findSubstring(String s, String[] words) {
		Map<String, Integer> allWords = new HashMap<>();
		for (String word : words) {
			allWords.put(word, allWords.getOrDefault(word, 0) + 1);
		}
		int wordNum = words.length, wordLen = words[0].length();
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < s.length() - wordNum * wordLen + 1; i++) {
			Map<String, Integer> subWords = new HashMap<>();
			int index = i;
			while (index < i + wordNum * wordLen) {
				String curWord = s.substring(index, index + wordLen);
				if (!allWords.containsKey(curWord) || subWords.get(curWord) == allWords.get(curWord)) {
					break;
				}
				subWords.put(curWord, subWords.getOrDefault(curWord, 0) + 1);
				index += wordLen;
			}
			if (index == i + wordNum * wordLen) {
				res.add(i);
			}
		}
		return res;
	}
}
