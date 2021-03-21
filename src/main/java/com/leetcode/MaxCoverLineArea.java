package com.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description: 有n条线段 [start, end], 求重复次数最多的区域, 并返回这个区域的重复线段数
 * @author : david
 * @date Date : 2021年03月21日 20:16
 * @version V1.0
 */
public class MaxCoverLineArea {
	public static class Line {
		private int start;
		private int end;
		public Line(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	public static class LineComparator implements Comparator<Line>{
		@Override
		public int compare(Line o1, Line o2) {
			return o1.start - o2.start;
		}
	}

	public static int maxCover(int[][] m) {
		Line[] lines = new Line[m.length];
		for (int i = 0; i < m.length; i++) {
			lines[i] = new Line(m[i][0], m[i][1]);
		}
		Arrays.sort(lines, new LineComparator());
		// 小根堆，每条线段结尾数值，使用默认的排序
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		int max = 0;
		for (Line curLine : lines) {
			// lines[i] -> curLine 在黑盒中，把<=cur.start的东西都弹出
			while (!heap.isEmpty() && heap.peek() <= curLine.start) {
				heap.poll();
			}
			heap.add(curLine.end);
			max = Math.max(max, heap.size());
		}
		return max;
	}

}
