package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Z 字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * https://leetcode-cn.com/problems/zigzag-conversion/
 * @author : david
 * @date Date : 2021年03月14日 13:19
 * @version V1.0
 */
public class ZigzagConversion {
	public String convert(String s, int numRows) {
		List<Character>[] matrix = new ArrayList[numRows];
		for (int i = 0; i < numRows; i++) {
			matrix[i] = new ArrayList();
		}
		char[] chars = s.toCharArray();
		int cur = 0;
		for (int i = 0; i < chars.length; i++) {
			int temp = i;
			if (numRows > 1 && i >= numRows * 2 - 2) {
				while (temp - numRows > 0) {
					temp-= numRows * 2 - 2;
					if (temp < numRows * 2 - 2) {
						break;
					}
				}
			}
			int num = temp % numRows;
			if (temp >= numRows && temp < 2 * numRows - 2) {
				num = 2 * numRows - 2 - temp;
			}
			matrix[num].add(chars[i]);
		}
		String ans = "";
		for (int i = 0; i < matrix.length; i++) {
			String line = "";
			for (int j = 0; j < matrix[i].size(); j++) {
				ans += matrix[i].get(j) + "";
				line += matrix[i].get(j) + "";
			}
			System.out.println(line);
		}
		return ans;
	}

	public static void main(String[] args) {
		String s = "Apalindromeisaword,phrase,number,orothersequenceofunitsthatcanbereadthesamewayineitherdirection,withgeneralallowancesforadjustmentstopunctuationandworddividers.";
		System.out.println(new ZigzagConversion().convert(s, 5));
	}
}
