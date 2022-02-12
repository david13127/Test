package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2022年02月01日 9:56
 * @version V1.0
 */
public class SubsetsII {
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		Arrays.sort(nums);
		List<Integer> t = new ArrayList<>();
		List<List<Integer>> ans = new ArrayList<>();
		dfs(ans, t, false, 0, nums);
		return ans;
	}


	public void dfs(List<List<Integer>> ans, List<Integer> t, boolean choosePre,
			int cur, int[] nums) {
		if (cur == nums.length) {
			ans.add(new ArrayList<>(t));
			return;
		}
		dfs(ans, t, false, cur + 1, nums);
		if (!choosePre && cur > 0 && nums[cur - 1] == nums[cur]) {
			return;
		}
		t.add(nums[cur]);
		dfs(ans, t, true, cur + 1, nums);
		t.remove(t.size() - 1);
	}
}
