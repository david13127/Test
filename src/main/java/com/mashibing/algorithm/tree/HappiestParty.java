package com.mashibing.algorithm.tree;

import java.util.List;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月12日 10:46
 * @version V1.0
 */
public class HappiestParty {
	public int maxHappy(Employee head) {
		Info allInfo = process(head);
		return Math.max(allInfo.no, allInfo.yes);
	}

	private Info process(Employee x) {
		if (x == null) {
			return new Info(0, 0);
		}
		int no = 0;
		int yes = x.happy;
		for (Employee next : x.children) {
			Info nextInfo = process(next);
			no += Math.max(nextInfo.no, nextInfo.yes);
			yes += nextInfo.no;
		}
		return new Info(no, yes);
	}

	static class Info {
		int no;
		int yes;

		Info(int n, int y) {
			this.no = n;
			this.yes = y;
		}
	}
	static class Employee {
		int happy;
		List<Employee> children;
	}
}

