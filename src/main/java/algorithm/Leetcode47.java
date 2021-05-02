package algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://leetcode-cn.com/problems/permutations-ii/
 *
 * 47. 全排列 II
 * 去重
 */

public class Leetcode47 {

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        helper(ans, nums, visited, new ArrayList<>());
        return ans;
    }


    public static void helper(List<List<Integer>> ans, int[] nums, boolean[] visited, List<Integer> tmp) {
        if(tmp.size() == nums.length) {
            ans.add(new ArrayList<>(tmp));
            return;
        }
        // 每调用一次函数，都是用新的set，防止在此层中重复使用该元素排前面
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if(visited[i] || set.contains(nums[i]))
                continue;
            set.add(nums[i]);
            visited[i] = true;
            tmp.add(nums[i]);
            helper(ans, nums, visited, tmp);
            visited[i] = false;
            tmp.remove(tmp.size()-1);
        }
    }


    public static void main(String[] args) {
        List<List<Integer>> ans = permuteUnique(new int[]{1, 1, 2});
        System.out.println(ans);
        System.out.println(ans.size());
    }

}
