package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年01月23日 10:26
 * @version V1.0
 */
public class ProgrammableRobot {

	public boolean robot(String command, int[][] obstacles, int x, int y) {
		List<int[]> route = new ArrayList<>();
		route.add(new int[]{0, 0});
		int a = 0, b = 0;
		for (char c : command.toCharArray()) {
			if (c == 'U') {
				b++;
			} else {
				a++;
			}
			route.add(new int[]{a, b});
		}
		// 障碍点是否可达
		for (int[] obstacle : obstacles) {
			if (obstacle[0] > x || obstacle[1] > y) {
				continue;
			}
			if (canArrived(obstacle[0], obstacle[1], a, b, route)) {
				return false;
			}
		}
		// 终点是否可达
		if (!canArrived(x, y, a, b, route)) {
			return false;
		}
		return true;
	}

	private boolean canArrived(int x, int y, int a, int b, List<int[]> route) {
		// 二维取余
		int d = Math.min(x / a, y / b);
		x -= a * d;
		y -= b * d;
		for (int[] r : route) {
			if (r[0] == x && r[1] == y){
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		ProgrammableRobot solution = new ProgrammableRobot();
		String command = "URR";
		int[][] obstacles = new int[][]{{2, 2}};
		int x = 3;
		int y = 2;
		boolean robot = solution.robot(command, obstacles, x, y);
		System.out.println(robot);
	}
}
