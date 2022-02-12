package com.mashibing.algorithm.greed;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月12日 17:38
 * @version V1.0
 */
public class MinimumLight {
	public int minLight(String road) {
		char[] str = road.toCharArray();
		int i = 0;
		int light = 0;
		while (i < str.length) {
			if (str[i] == 'X') {
				i++;
			} else {
				light++;
				if (i + 1 == str.length) {
					break;
				} else {
					if (str[i + 1] == 'X') {
						i = i + 2;
					} else {
						i = i + 3;
					}
				}
			}
		}
		return light;
	}

	public static void main(String[] args) {
		String road = "X...X..X...X.X....X..";
		MinimumLight solution = new MinimumLight();
		int ans = solution.minLight(road);
		System.out.println(ans);
	}
}
