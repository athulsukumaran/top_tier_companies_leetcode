/*
 * Question:
 * https://leetcode.com/problems/house-robber-ii/
 */

/*
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed.
 * All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one.
 * Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 *
 * Example 2:
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 *
 * Example 3:
 * Input: nums = [1,2,3]
 * Output: 3
 *
 * Solution Approach Used:
 * This can be considered as a dynamic programming question.
 * We will try to solve this using tabulation using a dp array.
 * Lets say the base cases, if only one house is there, it would be the maximum amount we can rob
 * Incase there are 2 houses, we have to choose one of the house which has a maximum amount
 *
 * If there are more than 2 houses, we can break the problem like, the max of (max amount he can rob in the sub array from index 0 to n - 2, max amount he can rob in the sub array from index 1 to n - 1)
 * This is because the first and last houses are neighbours in circluar arrangement.
 * We will use the same helper function as in the house robber 1 question
 * In the main method, we can call the helper method for both the sub arrays and find the maximum of both and return it
 *
 * Time Complexity: O(n), Space Complexity: O(n).
 * Space Complexity can be further reduced to O(1) as in house robber ii solutions uploaded by Athul.
 */

class Solution {
    // Helper function to calculate the maximum amount we can rob in the houses that are arranged in linear order
  
    public int maxAmountRobLinear(int[] nums, int start, int end) {
        
        // The length of the houses in the array or sub array
        int length = end - start + 1;
        
        // If length == 1, return the amount in house at start index
        // If length == 2, return max of amount in either of the houses 
        // Else helper method and dpArray to find the max amount he can have at each index and return the max amount he will have at the end index (index length - 1)
        if(length == 1) {
            return nums[start];
        } else if(length == 2) {
            return Math.max(nums[start], nums[start + 1]);
        } else {
      
            // Declare the dpArray
            // Declare the base cases
            int[] dpArray = new int[length];
            dpArray[0] = nums[start];
            dpArray[1] = Math.max(nums[start], nums[start + 1]);
          
            // Calculate the max amount he can have at each index
            for(int i = 2; i < length; i++) {
                dpArray[i] = Math.max(dpArray[i - 2] + nums[start + i], dpArray[i - 1]);
            }
          
            // Return the max amount he will have at last house
            return dpArray[length - 1];
        }
    }
    
    public int rob(int[] nums) {
        
        // The length of the houses in the array
        int length = nums.length;

        // If length == 1, return the amount in house at start index
        // If length == 2, return max of amount in either of the houses 
        // Else helper method to return the max amount he will have at the end index (index length - 1)
        
        if(length == 1) {
            return nums[0];
        } else if(length == 2) {
            return Math.max(nums[0], nums[1]);
        } else {
      
            // Call the helper function to get the maximum amount he can rob
            return Math.max(maxAmountRobLinear(nums, 0, nums.length - 2),
                            maxAmountRobLinear(nums, 1, nums.length - 1));
        }
    }
}
