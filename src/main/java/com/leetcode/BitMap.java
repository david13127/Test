package com.leetcode;

/**
 * @Description: 位图
 * @author : david
 * @date Date : 2021年03月20日 21:39
 * @version V1.0
 */
public class BitMap {
	private long[] bits;

	public BitMap(int max) {
		bits = new long[(max + 64) >> 6];
	}

	public void add(int num) {
		bits[num >> 6] |= (1L << (num & 63));
	}

	public void delete(int num) {
		bits[num >> 6] &= (1L << (num & 63));
	}

	public boolean contains(int num) {
		return (bits[num >> 6] & (1L << (num & 63))) != 0;
	}

	public static void main(String[] args) {
		BitMap bitMap = new BitMap(128);
		bitMap.add(110);
		System.out.println(bitMap.contains(111));
	}
}
