/*
 * Question:
 * https://leetcode.com/problems/permutations
 */

/*
 * Solution Approach Used:
 * Write out in case of
 *      1. One Element eg nums = [1]: Permutations = [[1]] // Only one Permutation
 *      2. Two Elements eg nums = [1, 2]: Permutations = [[1, 2], [2, 1]] // Second element can be added on either sides of previous one element permutation [[1]]
 *      3. Three Elements eg nums = [1, 2, 3]: Permutations = [[1, 2, 3], [1, 3, 2], [3, 1, 2], [2, 1, 3], [2, 3, 1], [3, 2, 1]] // Third Element can be added anywhere in between the elements in permutations available for 2 elements [[1, 2], [2, 1]] 
 * Generally, for i th number, it can be inserted anywhere in i + 1 available spaces between elements for each permutations available for nums till i - 1
 * Time Complexity of this approach: O(n^2 * n!), Space Complexity: O(n!). This passes the leetcode submission. Have to check if it can be done in lesser time and space.
 */

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for(int i = 0; i < nums.length; i++) {
            int num = nums[i];
            List<List<Integer>> tempList = new ArrayList<>();
            for(int j = 0; j < result.size(); j++) {
                for(int k = 0; k <= i; k++) {
                    List<Integer> temp = new ArrayList<>(result.get(j));
                    temp.add(k, nums[i]);
                    tempList.add(temp);
                }
            }
            result.clear();
            result.addAll(tempList);
        }
        return result;
    }
}
