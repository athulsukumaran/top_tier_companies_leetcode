/*
 * Question:
 * https://leetcode.com/problems/minimum-size-subarray-sum
 */

/*
 * Given an array of positive integers nums and a positive integer target, 
 * return the minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr] of which the sum is greater than or equal to target.
 * If there is no such subarray, return 0 instead.
 *
 * Example 1: 
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 * 
 * Example 2:
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 * 
 * Example 3:
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 *
 * Constraints:
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 *
 * Solution Approach Used:
 * We can use the dynamic sliding window approach using left and right pointers and movind the pointers from both end to find the sub array size for which the sum is greater than or equal to target.
 * Initialize values for
 *      1. sub array minimum size to the input array length + 1
 *      2. window length and window sum to 0
 *      3. left and right pointers to index 0
 *
 * Iterate the array by moving the right pointer till array length in outer while loop
 * In each iteration add the num value at right to window sum
 * Increment the right pointer
 * Now check if the window sum is greater than or equal to target, If yes, we have found a window with sum >= target
 * Check if theres a smaller window with sum >= target, by removing num vals from left and reducing them from window sum in a inner while loop.
 * Once the inner while loop finishes execution, we have found that sub array window till left - 1 was having sum >= target
 * Get the window length and check if this window length is lesser than existing minimum sub array length in result value(greedy) and update it accordingly.
 * 
 * Time Complexity: O(n), Space Complexity: O(1) where n is length of array
 * Check solution in leetcode itself for further reading
 * https://leetcode.com/problems/minimum-size-subarray-sum/solution
 *
 */
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
      
        // Initializing values for Sub Array Min Size to Input Array Length + 1
        int noSubArrayLen = nums.length + 1;
        int subArrayMinSize = noSubArrayLen;
      
        // Initializing window sum and window size to 0 at starting
        int subArraySize = 0;
        int subArraySum = 0;
      
        // Left and Right Pointers
        int left = 0, right = 0;
        
        // Outer while loop to iterate by moving the right pointer
        while(right < nums.length) {
            
            // Adding num at right to sub array sum 
            subArraySum = subArraySum + nums[right];
          
            // Incrementing right pointer for outer loop iteration
            right++;
          
            // Before going to next iteration in the outer loop, check if the current window sum is greater than or equal to target
            // If yes we can try removing numbers from the left and reduce the window size and see if the window sum is still greater than or equal to target
            if(subArraySum >= target) {
              
                // Inner while loop to move left pointer to right side, update window sum and check if sub array sum >= target
                while(left <= right && subArraySum >= target) {
                    subArraySum = subArraySum - nums[left];
                    left++;
                }
              
                // Once the window sum < target or left > right, exit the inner loop
                // The window size would then be right - left + 1
                // Check if this window size is lesser than exiting minimum length we have till now
                subArraySize = right - left + 1;
                subArrayMinSize = Math.min(subArrayMinSize, subArraySize);
            }
        }
      
        // If no window is found with sum >= target, the window length would be the initial value of array length + 1
        // In that case we return 0, else we return the minimum window size found
        return subArrayMinSize == noSubArrayLen ? 0: subArrayMinSize;
    }
}
