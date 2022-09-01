/*
 * Question:
 * https://leetcode.com/problems/maximum-subarray
 */

/*
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * 
 * Example 2:
 * Input: nums = [1]
 * Output: 1
 *
 * Example 3:
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 *
 * Constraints:
 * 
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *
 * Solution Approach Used:
 * Approach 1 - Brute Force
 * The brute force way is to find the sum of all contigious subarrays. It takes O(n^2) time as there can be n * (n + 1) / 2 subarrays.
 * 
 * Approach 2 - Optimizing Time using Kdanes Algorithm
 * Kadane's Algorithm is an iterative dynamic programming algorithm.
 * It calculates the maximum sum subarray ending at a particular position by using the maximum sum subarray ending at the previous position.
 *
 * Suppose we have calculated the maximum sum of continuous subarray ending at an index i - 1
 *        then the maximum sum of continuous sub array ending at index i can be either
 *              the number at index i, or sum of (maximum sum of continuous sub array ending at index i - 1) and number at index i
 *        This is because, either the number at index i is positive and max sum of continuous subarray at index i - 1 can be negative, then max sum of continuous subarray ending at index i is the number at index i itself
 *
 * After calculating max sum of continuous array at an index, we have to compare it with the maximum sum so far and update it if the current maximum is less than the max sum of continuous sub array ending at index i
 *
 * The base cases for the contSumArray
 * contSumArray[0] = nums[0]
 * 
 * Time Complexity: O(n), Space Complexity: O(n) where n is length of array
 * Though the time complexity and space complexity is in O(n), the space complexity can be further optimized using sliding window approach
 *
 */
class Solution {
    public int maxSubArray(int[] nums) {
        // Length of input array
        int len = nums.length;
        
        // Array declared to store the max sum of continuous sub array ending at index i
        // Base case declared for index 0
        int[] contSumArray = new int[len];
        contSumArray[0] = nums[0];
        
        // Max sum variable to store the maximum sum so far
        // Base case declared as max sum = value at index 0
        int maxSum = contSumArray[0];
        
        // Iterating from index 1 to len
        for(int i = 1; i < len; i++) {
        
            // Compare sum of (max sum of continuous sub array till index i - 1 and number at index i) and (number at index i)
            // Store the maximum of them to the array at index i
            contSumArray[i] = Math.max(contSumArray[i - 1] + nums[i], nums[i]);
            
            // Update the max sum variable if the max sum of continuous subarray till index i is greater than current value of max sum
            maxSum = Math.max(maxSum, contSumArray[i]);
        }
        
        // Return result
        return maxSum;
    }
}
