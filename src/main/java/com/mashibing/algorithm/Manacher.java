package com.mashibing.algorithm;

/**
 * @Description: 马拉车算法，求最长回文子串
 * @author : david
 * @date Date : 2021年02月13日 21:30
 * @version V1.0
 */
public class Manacher {
	public static int manacher(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		// 添加"#"符号
		char[] strArr = manacherString(str);
		// 回文半径的大小
		int[] pArr = new int[strArr.length];
		int C = -1;
		// 最右且扩展成功的下一个位置
		int R = -1;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i != strArr.length; i++) {
			// i位置扩展出来的答案，i位置扩展的区域，至少是多大
			// 对称点2 * C - i
			// i在R外，至少有一个不用验证
			// i在R内，i对称点i'扩展区域在R对称点右侧，则pArr[2 * C - i]区域不用验证，
			// i对称点i'扩展区域在R对称点左侧或与它重合，则R-i区域不用验证，
			pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
			while (i + pArr[i] < strArr.length && i - pArr[i] > -1) { // 两侧不越界
				if (strArr[i + pArr[i]] == strArr[i - pArr[i]]) { // 要扩展
					pArr[i]++;
				}
				else { // 越界停止扩展
					break;
				}
			}
			if (i + pArr[i] > R) {
				R = i + pArr[i];
				C = i;
			}
			max = Math.max(max, pArr[i]);
		}
		return max - 1;
	}

	public static char[] manacherString(String str) {
		char[] chars = str.toCharArray();
		char[] res = new char[str.length() * 2 + 1];
		int index = 0;
		for (int i = 0; i != res.length; i++) {
			res[i] = (i & 1) == 0 ? '#' : chars[index++];
		}
		return res;
	}

	public static void main(String[] args) {
		String str = "gabc12321cbaf";
		System.out.println(manacher(str));
	}
}
