package com.mashibing.algorithm.class01;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Description: 找数组中某个位置左右最近比它小的数
 * @author : david
 * @date Date : 2021年02月16日 10:37
 * @version V1.0
 */
public class MonotonousStack {
	public static int[][] getNearLessNoRepeat(int[] arr) {
		int[][] res = new int[arr.length][2];
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
				int popIndex = stack.pop();
				int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
				res[popIndex][0] = leftLessIndex;
				res[popIndex][1] = i;
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int popIndex = stack.pop();
			int leftLessIndex = stack.isEmpty() ? -1 : stack.pop();
			res[popIndex][0] = leftLessIndex;
			res[popIndex][1] = -1;
		}
		return res;
	}

	public static int[][] getNearLess(int[] arr) {
		int[][] res = new int[arr.length][2];
		// stack放的是位置，同样的值，放到一个list里面
		// stack从底到顶，由小到大
		Stack<List<Integer>> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++) {
			while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
				List<Integer> popIs = stack.pop();
				// 取位于下面位置的列表中，最晚加到list里面的
				int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
				for (Integer popi : popIs) {
					res[popi][0] = leftLessIndex;
					res[popi][1] = i;
				}
			}
			// 相等的，比你小的
			if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
				stack.peek().add(i);
			}
			else {
				ArrayList<Integer> list = new ArrayList<>();
				list.add(i);
				stack.push(list);
			}
		}
		while (!stack.isEmpty()) {
			List<Integer> popIs = stack.pop();
			// 取位于下面位置的列表中，最晚加到list里面的
			int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
			for (Integer popi : popIs) {
				res[popi][0] = leftLessIndex;
				res[popi][1] = -1;
			}
		}
		return res;
	}
}
