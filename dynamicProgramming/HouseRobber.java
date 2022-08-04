/*
 * Question:
 * https://leetcode.com/problems/house-robber
 */

/*
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected.
 * It will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 *
 * Example 2: 
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 *
 * Solution Approach Used:
 * This can be considered as a dynamic programming question.
 * We will try to solve this using tabulation using a dp array.
 * Lets say the base cases, if only one house is there, it would be the maximum amount we can rob
 * Incase there are 2 houses, we have to choose one of the house which has a maximum amount
 *
 * If there are more than 2 houses, we can break the problem like, the decision to rob a house or not is based on checking the max of(amount if he rob the house, if he skip the house).
 * Incase he decides to rob the house at i, the amount he can have at the index i will be (amount he had at house at index i - 2 + amount in house at index i)
 * Incase he decides to skip the house at i, the amount he will have at that index will be the amount he had in house at index i - 1.
 * 
 * The base cases for dpArray are
 * dpArray[0] = nums[start]
 * dpArray[1] = Math.max(nums[start], nums[start + 1]
 * 
 * For index 2 to length - 1, the dpArray value at i will be Math.max(dpArray[i - 2] + nums[start + i], dpArray[i - 1])
 *
 * The start and end can be ommitted for simplicity in the this question, but we will require them for the follow up question house robber ii, where the houses are in circular order.
 * That is the first and last indexes in the circular order will be considered as neighbours.
 *
 * Time Complexity: O(n), Space Complexity: O(n).
 * Space Complexity can be further reduced to O(1) as in house robber solutions uploaded by Athul.
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
            return maxAmountRobLinear(nums, 0, nums.length - 1);
        }
    }
}
