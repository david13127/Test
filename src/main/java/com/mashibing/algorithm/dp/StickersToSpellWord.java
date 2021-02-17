package com.mashibing.algorithm.dp;

import java.util.HashMap;

/**
 * @Description: 纸牌拼单词，纸牌可以拆分，拼出单词最少需要多少张
 * @author : david
 * @date Date : 2021年02月16日 14:53
 * @version V1.0
 */
public class StickersToSpellWord {
	public static int minStickers1(String[] stickers, String target) {
		if (stickers == null || stickers.length == 0 || target == null || target.length() == 0) {
			return 0;
		}
		int min = Integer.MAX_VALUE;
		for (String first : stickers) {
			String rest = minus(target, first);
			if (rest.length() != target.length()) {
				min = Math.min(min, minStickers1(stickers, rest));
			}
		}
		return min + (min == Integer.MAX_VALUE ? 0 : 1);
	}

	private static String minus(String s1, String s2) {
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int[] count = new int[26];
		for (char cha : str1) {
			count[cha - 'a']++;
		}
		for (char cha : str2) {
			count[cha - 'a']--;
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 26; i++) {
			if (count[i] > 0) {
				for (int j = 0; j < count[i]; j++) {
					builder.append((char) (i + 'a'));
				}
			}
		}
		return builder.toString();
	}

	public static int minStickers2(String[] stickers, String target) {
		if (stickers == null || stickers.length == 0 || target == null || target.length() == 0) {
			return 0;
		}
		int n = stickers.length;
		int[][] counts = new int[n][26];
		for (int i = 0; i < n; i++) {
			char[] str = stickers[i].toCharArray();
			for (char cha : str) {
				counts[i][cha -'a']++;
			}
		}
		int ans = process2(counts, target);
		return ans == Integer.MAX_VALUE ? 0 : ans;
	}


	private static int process2(int[][] stickers, String t) {
		if (t.length() == 0) {
			return 0;
		}
		char[] target = t.toCharArray();
		int[] tCounts = new int[26];
		for (char cha : target) {
			tCounts[cha - 'a']++;
		}
		int min = Integer.MAX_VALUE;
		for (int[] sticker : stickers) {
			// 最关键的优化（重要的剪枝，这一步也是贪心）
			if (sticker[target[0] - 'a'] > 0) {
				StringBuilder builder = new StringBuilder();
				for (int j = 0; j < 26; j++) {
					if (tCounts[j] > 0) {
						int nums = tCounts[j] - sticker[j];
						for (int k = 0; k < nums; k++) {
							builder.append((char) (j + 'a'));
						}
					}
				}
				String rest = builder.toString();
				min = Math.min(min, process2(stickers, rest));
			}
		}
		return min + (min == Integer.MAX_VALUE ? 0 : 1);
	}

	public static int minStickersDp(String[] stickers, String target) {
		if (stickers == null || stickers.length == 0 || target == null || target.length() == 0) {
			return 0;
		}
		int n = stickers.length;
		int[][] counts = new int[n][26];
		for (int i = 0; i < n; i++) {
			char[] str = stickers[i].toCharArray();
			for (char cha : str) {
				counts[i][cha -'a']++;
			}
		}
		HashMap<String, Integer> dp = new HashMap<>();
		dp.put("", 0);
		int ans = process3(dp, counts, target);
		return ans == Integer.MAX_VALUE ? 0 : ans;
	}
	public static int process3( HashMap<String, Integer> dp, int[][] stickers, String t) {
		if (dp.containsKey(t)) {
			return dp.get(t);
		}
		char[] target = t.toCharArray();
		int[] tCounts = new int[26];
		for (char cha : target) {
			tCounts[cha - 'a']++;
		}
		int min = Integer.MAX_VALUE;
		for (int[] sticker : stickers) {
			if (sticker[target[0] - 'a'] > 0) {
				StringBuilder builder = new StringBuilder();
				for (int j = 0; j < 26; j++) {
					if (tCounts[j] > 0) {
						int nums = tCounts[j] - sticker[j];
						for (int k = 0; k < nums; k++) {
							builder.append((char) (j + 'a'));
						}
					}
				}
				String rest = builder.toString();
				min = Math.min(min, process3(dp, stickers, rest));
			}
		}
		int ans =  min + (min == Integer.MAX_VALUE ? 0 : 1);
		dp.put(t, ans);
		return ans;
	}
}
