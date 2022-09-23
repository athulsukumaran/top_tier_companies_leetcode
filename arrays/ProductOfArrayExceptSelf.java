/*
 * Question:
 * https://leetcode.com/problems/product-of-array-except-self
 */

/*
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * 
 * Example 2:
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 *
 * Constraints:
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * Solution Approach Used:
 * Approach 1 - Brute Force
 * The brute force way is to find the product of all numbers except self using 2 loops. It takes O(n^2) time.
 * 
 * Approach 2 - Optimizing Time using Extra Space
 * Here we are not allowed to do division operation. Hence we cannot compute the cummulative product and then divide by each number.
 * But instead we can use 2 extra arrays of length same as input array to find the cummulative products at index i from front and back direction.
 * The product of nums in array except self at an index  i will therefore be the cummulative product value at index i - 1 from front multiplied with cummulative product value at index i + 1 from back.
 *
 * The corner cases for the final result array are 
 * resultArray[0] = cummulativeProductFromBack[1]
 * resultArray[len - 1] = cummulativeProductFromFront[len - 1]
 * 
 * Time Complexity: O(n), Space Complexity: O(n) where n is length of array
 * Though the time complexity and space complexity is in O(n), the multiple loops(not nested, hence O(n) time) and multiple arrays to compute result could be further optimized.
 */
 
class Solution {
    public int[] productExceptSelf(int[] nums) { 
        // Length of input array
        int len = nums.length;
        
        // Declaring array to store cummulative product from front.
        // Initializing value at index 0 with nums[0]
        int[] cumProdArrayForward = new int[len];
        cumProdArrayForward[0] = nums[0];
        
        // Calculating cummulative product in forward direction
        for(int i = 1; i < len; i++) {
            cumProdArrayForward[i] = cumProdArrayForward[i - 1] * nums[i];
        }
        
        // Declaring array to store cummulative product from back.
        // Initializing value at index len - 1 with nums[len - 1]
        int[] cumProdArrayBackward = new int[len];
        cumProdArrayBackward[len - 1] = nums[len - 1];
        
        // Calculating cummulative product in backward direction
        for(int i = len - 2; i >= 0; i--) {
            cumProdArrayBackward[i] = cumProdArrayBackward[i + 1] * nums[i];
        }
        
        // Declaring result array to store result and return it
        int[] resultArray = new int[nums.length];
        
        // Corner cases
        resultArray[0] = cumProdArrayBackward[1];
        resultArray[len - 1] = cumProdArrayForward[len - 2];
        
        // Calculating values for remaining indexes
        for(int i = 1; i < len - 1; i++) {
            resultArray[i] = cumProdArrayForward[i - 1] * cumProdArrayBackward[i + 1];
        }
        
        // Returning result array
        return resultArray;
    }
}
