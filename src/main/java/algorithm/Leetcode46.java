package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/permutations/
 *
 * 全排列
 * 不用去重的全排列就直接使用回溯算法即可。
 * 使用一个表记录访问过的元素
 * 然后递归下去访问下一层，使用递归是为了能够返回当前层
 */
public class Leetcode46 {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        helper(ans, new ArrayList<>(), nums, visited);
        return ans;
    }

    public static void helper(List<List<Integer>> ans, List<Integer> tmp, int[] nums, boolean[] visited) {
        // base case
        if(tmp.size() == nums.length) {
            ans.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 访问过了
            if(visited[i])
                continue;
            visited[i] = true;
            tmp.add(nums[i]);
            helper(ans, tmp, nums, visited);
            // 恢复现场
            visited[i] = false;
            tmp.remove(tmp.size() - 1);
        }
    }


    public static void main(String[] args) {
        List<List<Integer>> permute = permute(new int[]{1, 2, 3});
        System.out.println(permute);
    }

}
