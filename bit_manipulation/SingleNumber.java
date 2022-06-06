/*
 * Question:
 * https://leetcode.com/problems/single-number
 */

/*
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 * Example 1:
 * Input: nums = [2,2,1]
 * Output: 1
 *
 * Example 2:
 * Input: nums = [4,1,2,1,2]
 * Output: 4
 *
 * Example 3:
 * Input: nums = [1]
 * Output: 1
 *
 * Solution Approach Used:
 * We know that bitwise xor of any number to zero is the number itself and bitwise xor of any number to itself is zero.
 * We will initialize a result variable and then do the cummulative xor for the result variable with nums in the array.
 * Since all elements that occurs twice cancels their out their bits, the bits for element occuring only once is left behind in the result after the loop.
 * Return the result variable
 * Time Complexity: O(n), Space Complexity: O(1) where n is length of array
 *
 * Check solution in https://www.youtube.com/watch?v=krgK0UlfNYY
 */

class Solution {
    public int singleNumber(int[] nums) {
      
        // Result variable
        int result = 0;
        
        // For loop to calculate cummulative xor for the numbers in array
        for(int i = 0; i < nums.length; i++) {
            result = result ^ nums[i];
        }
        
        // Return result 
        return result;
    }
}
