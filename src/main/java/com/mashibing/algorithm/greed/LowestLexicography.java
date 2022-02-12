package com.mashibing.algorithm.greed;

import com.mashibing.algorithm.util.Generator;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月12日 11:25
 * @version V1.0
 */
public class LowestLexicography {
	public String lowestString(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		Arrays.sort(strs, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));
		StringBuilder sb = new StringBuilder();
		for (String str : strs) {
			sb.append(str);
		}
		return sb.toString();
	}
	public String lowestStringBao(String[] strs) {
		return process(strs).first();
	}
	public TreeSet<String> process(String[] strs) {
		TreeSet<String> ans = new TreeSet<>();
		if (strs.length == 0) {
			ans.add("");
			return ans;
		}
		for (int i = 0; i < strs.length; i++) {
			String first = strs[i];
			String[] nexts = removeIndexString(strs, i);
			TreeSet<String> next = process(nexts);
			for (String cur : next) {
				ans.add(first + cur);
			}
		}
		return ans;
	}

	private String[] removeIndexString(String[] strs, int index) {
		String[] ans = new String[strs.length - 1];
		int ansIndex = 0;
		for (int i = 0; i < strs.length; i++) {
			if (i != index) {
				ans[ansIndex++] = strs[i];
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		LowestLexicography solution = new LowestLexicography();
		int arrLen = 6;
		int strLen = 5;
		int testTimes = 10000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			String[] arr1 = Generator.generateRandomStringArray(arrLen, strLen);
			String[] arr2 = Generator.copyStringArray(arr1);
			String ans1 = solution.lowestString(arr1);
			String ans2 = solution.lowestStringBao(arr2);
			if (!ans1.equals(ans2)) {
				for (String str : arr1) {
					System.out.print(str + ",");
				}
				System.out.println();
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}
}
