/*
 * Question:
 * https://leetcode.com/problems/combination-sum-iv
 */

/*
 * Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.
 * The test cases are generated so that the answer can fit in a 32-bit integer.
 * 
 * Example 1:
 * Input: nums = [1,2,3], target = 4
 * Output: 7
 * Explanation:
 * The possible combination ways are:
 *        (1, 1, 1, 1)
 *        (1, 1, 2)
 *        (1, 2, 1)
 *        (1, 3)
 *        (2, 1, 1)
 *        (2, 2)
 *        (3, 1)
 * Note that different sequences are counted as different combinations.
 *
 * Example 2:
 * Input: nums = [9], target = 3
 * Output: 0
 *
 * Constraints:
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 1000
 * All the elements of nums are unique.
 * 1 <= target <= 1000
 *
 * Solution Approach Used:
 * This is similar to the coin change question, The difference is that, in coin change, we are looking for the minimum number of coins to make the target, here we are looking for the total ways to make the target.
 *
 * The brute force way to graphically solve this problem is to draw decision tree for each of the num in input array till we get the target and add the number of paths that gives us target.
 * Since the number in array is not negative, we can stop a path once we get the target.
 *
 * Calculate the number of paths that reaches target and number of such paths is our desired output.
 *
 * But this approach too solves many repeated sub problems, which can be optimized using dp array.
 * Say we have a number 3 in our nums array
 * There could be some other paths in different height such that its sum till that height is also 3.
 * Then we will be doing the decision tree for both these nodes at diff height repeatedly.
 * Instead of this we can make use of a dp array, from which we can avoid computing duplicate sub problems.
 *
 * Here we will try to solve the problem using dynamic programming in bottom up approach(i.e, not starting from target and trying to make it zero, but in the dp array we
 *        calculate the total ways to make the target value equal to index and store it at index i in dpArray)
 *
 * The base case is that for making target zero, we have 1 way, i.e, [].
 * 
 * We can define a dp array and initialize this as the base case. For all other index, lets first assume that target cannot be made and hence keep it as 0 default.
 *
 * Now we can check for index 1 to target in dp array, if the value at index i can be made using nums we have
 * For this we loop throught the nums array in an inner loop
 * Suppose the target at index(i) is greater than num in the nums array => current Target - nums at index j >=0,
 *        Then target may be obtained by using this num and number of ways in which we can reach curr target if we include this num is dpArray[currTarget - num].
 *        Hence we can add this to dpArray[currTarget]
 *
 * At the end of iterating both the loops, we'll have the ways to reach target at dpArray[target]
 *
 * Time Complexity: O(target * n), Space Complexity: O(target).
 *
 * Youtube link
 * https://www.youtube.com/watch?v=dw2nMCxG0ik
 */

class Solution {
    public int combinationSum4(int[] nums, int target) {
        
        // Declaring DP Array
        // Initializing Base Case
        int[] numWaysToGetTarget = new int[target + 1];
        numWaysToGetTarget[0] = 1;
        
        // Iterating over the dp array to calculate number of ways to reach sum for target value at index i
        for(int i = 1; i < target + 1; i++) {
            
            int currTarget = i;
            
            // Iterating over the nums array
            for(int j = 0; j < nums.length; j++) {
                
                int num = nums[j];
                
                // Checking if curr target is greater than num.
                // If curr target value is less than num, then we cannot reach curr target using this num
                // Else add the ways in which currTarget - num can be obtained to dpArray[currTarget]
                if(currTarget - num >= 0) {
                    numWaysToGetTarget[currTarget] += numWaysToGetTarget[currTarget - num];
                }

            }
        }
        
        // Return result
        return numWaysToGetTarget[target];
    }
}
