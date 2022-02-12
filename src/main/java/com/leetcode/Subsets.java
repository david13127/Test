package com.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月01日 9:43
 * @version V1.0
 */
public class Subsets {
	public List<List<Integer>> subsets1(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		result.add(new ArrayList<>());
		for (int num : nums) {
			int n = result.size();
			for (int i = 0; i < n; i++) {
				List<Integer> subset = result.get(i);
				List<Integer> set = new ArrayList<>(subset);
				set.add(num);
				result.add(set);
			}
		}
		return result;
	}

	public List<List<Integer>> subsets(int[] nums) {
		List<Integer> t = new ArrayList<Integer>();
		List<List<Integer>> ans = new ArrayList<List<Integer>>();
		dfs(0, nums, t, ans);
		return ans;
	}

	public void dfs(int cur, int[] nums, List<Integer> t, List<List<Integer>> ans) {
		if (cur == nums.length) {
			ans.add(new ArrayList<Integer>(t));
			return;
		}
		t.add(nums[cur]);
		dfs(cur + 1, nums, t, ans);
		t.remove(t.size() - 1);
		dfs(cur + 1, nums, t, ans);
	}

	public static void main(String[] args) {
		Subsets solution = new Subsets();
		int[] nums = {1, 5, 6};
		List<List<Integer>> subsets = solution.subsets(nums);
		subsets.forEach(set -> System.out.println(set.stream().map(e -> e + "").collect(Collectors.joining(","))));
	}
}
