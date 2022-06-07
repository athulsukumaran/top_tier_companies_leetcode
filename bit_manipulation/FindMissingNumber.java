/*
 * Question:
 * https://leetcode.com/problems/missing-number/
 */

/*
 * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
 *
 * Example 1:
 * Input: nums = [3,0,1]
 * Output: 2
 * Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
 *
 * Solution Approach Used:
 * Method 1. 
 * Using sum to N terms and cum sum of array.
 * This method uses the formula for sum to n terms and calculates the cummulative sum of numbers in the input array.
 * The difference in the sums would give the missing number.
 * This method works well in case of small numbers but can cause sum overflow incase of large numbers in the array.
 * This can be optimized again as in geeks for geeks, but bit approach is easy
 * https://www.geeksforgeeks.org/find-the-missing-number/
 * Time Complexity: O(n), Space Complexity: O(1) where n is length of array
 *
 * Method 2.
 * This is an extension of single number problem.
 * Idea is that any number xor itself is zero.
 * Find cummulative XOR for numbers 0 to n
 * Now do cummulative XOR for the result with the numbers in the input array.
 * This will result in doing xor for all numbers except missing number twice and cancelling out them and finally with the value of missing number in the input array
 * Time Complexity: O(n), Space Complexity: O(1) where n is length of array
 *
 * Check solution in https://www.youtube.com/watch?v=6KHW7ZQwtCA
 */

class Solution {
    public int missingNumber(int[] nums) {
//         // Method 1
//      
//         // Declaring variables to store array length, array sum and sum to n terms      
//         int n = nums.length;
//         int sumToN = (n * (n + 1)) / 2;
//         int arraySum = 0;
//        
//         // Calculating cummulative sum of numbers in the array
//         for(int i = 0; i < n; i++) {
//             arraySum = arraySum + nums[i];
//         }
//
//         // Returning the difference of sum to n terms and array sum (difference is the missing number)      
//         return sumToN - arraySum;
------------------------------------------------
  
        // Method 2
  
        // Declaring variables to store the array length and xor value
        int n = nums.length;
        int xor = 0;
        
        // Cummulatively calculating xor for i = 0 to n
        for(int i = 0; i <= n; i++) {
            xor = xor ^ i;
        }
        
        // Cummulatively calculating xor for numbers in the array
        // This will cancel out all numbers in the array from the cummulative xor as they are already in the expression and result in the missing number after the loop
        for(int i = 0; i < n; i++) {
            xor = xor ^ nums[i];
        }
        
        // Return the final value of xor and this is the missing number in input array as all other numbers are cancelled out in the expression
        return xor;
    }
}
