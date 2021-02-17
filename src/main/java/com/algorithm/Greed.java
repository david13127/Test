package com.algorithm;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2020年04月09日 23:50
 * @version V1.0
 */
public class Greed {
	public static void main(String[] args) {
		Meeting setMeet = new Meeting();
		setMeet.init();
		setMeet.solve();
	}
}

class Meet implements Comparable<Meet>{
	int num;
	int beg;
	int end;

	Meet(int num, int beg, int end) {
		this.num = num;
		this.beg = beg;
		this.end = end;
	}

	@Override
	public int compareTo(Meet o) {
		if (this.end > o.end) {
			return 1;
		}
		return -1;
	}
}

class Meeting {
	private List<Meet> meetList;
	private int ans;

	public void init() {
		Meet meet1 = new Meet(1, 8, 10);
		Meet meet2 = new Meet(2, 8, 9);
		Meet meet3 = new Meet(3, 10, 12);
		Meet meet4 = new Meet(4, 8, 20);
		this.meetList = Arrays.asList(meet1, meet2, meet3, meet4);
	}

	void solve() {
		this.meetList.sort(null);
		System.out.println("排完序的会议时间如下：");
		System.out.println("num   beg    end");
		for (Meet meet : this.meetList) {
			System.out.println(meet.num + "    " + meet.beg + "    " + meet.end);
		}
		System.out.println("-----------------------------------");
		System.out.println("选择会议的过程：");
		this.ans = 0;
		int last = 0;
		for (int i = 0; i < this.meetList.size(); i++) {
			Meet me = this.meetList.get(i);
			if (me.beg >= last) {
				this.ans++;
				last = me.end;
				System.out.println("选择第" + this.meetList.get(i).num + "个会议");
			}
		}
		System.out.println("最多可安排" + ans + "个会议");
	}

}