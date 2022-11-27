
/*
 * Question:
 * https://leetcode.com/problems/subsets
 */

/*
 * Solution Approach Used:
 * Write out in case of
 *      1. One Element eg nums = [1]: Subsets = [[], [1]] // 2 ^ 1 subsets
 *      2. Two Elements eg nums = [1, 2]: Subsets = [[], [1]] = [[], [1], [2], [1, 2]] // Second element can be added to both the subsets of one element array subsets => 2 ^ 2 subsets
 *      3. Three Elements eg nums = [1, 2, 3]: Subsets = [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]] // Third Element can be added to all 4 subsets of two element array subsets => 2 ^ 3 subsets
 * Generally, for i th number, the subsets will include all subsets without the ith number (subsets till i - 1) as well as subsets with ith number (ith number inserted in all the subsets available for nums till i - 1) => The total size of subsets would be 2 * (size of subsets till i - 1) = 2 ^ i.
 * Time Complexity of this approach: O(n * 2 ^ (n - 1)), Space Complexity: O(n * 2 ^ (n - 1)). This passes the leetcode submission. There is a backtracking method where time complexity is same, but space complexity is O(n).
 */

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for(int i = 0; i < nums.length; i++) {
            List<List<Integer>> tempSubsets = new ArrayList<>();
            for(int j = 0; j < result.size(); j++) {
                List<Integer> temp = new ArrayList<>(result.get(j));
                temp.add(nums[i]);
                tempSubsets.add(temp);
            }
            result.addAll(tempSubsets);
            tempSubsets.clear();
        }
        return result;
    }
}
