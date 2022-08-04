/*
 * Question:
 * https://leetcode.com/problems/longest-increasing-subsequence
 */

/*
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements.
 * For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 * 
 * Example 1:
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 *
 * Example 2:
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 *
 * Example 3:
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 *
 * Solution Approach Used:
 * This can be considered as a dynamic programming question.
 * We will try to solve this using tabulation using a dp array and a max variable to store the max length among all increasing sequences.
 * Lets say the base cases, we know that the minimum length of a longest increasing sequence would be 1 as a sequence of only 1 number will satisfy this condition. 
 * So we can declare a dp array to store the length of a longest increasing sequence ending at index i and initialize it with 1 assuming, no other elements before it is lesser than it.
 * 
 * Now we'll check for indexes 1 to nums.length - 1,
 *        weather there are numbers lesser than the number at index i
 *        if so, we'll check whether length of (longest increasing sequence ending at that index j) + 1(including the nums at i) is greater than the existing value we have in the dp array for index i.
 *
 * For this we will have to use 2 for loops, one for the outer condition to loop through 1 to nums.length - 1
 * Other is for the inner loop, for j = 0 to i.
 *
 * Time Complexity: O(n ^ 2), Space Complexity: O(n).
 * Here we are storing the length of longest sequence ending with number at index I in the dp Array.
 * I think Athul has uploaded a similar approach of storing the longest sequence starting from number at Index I in the dp Array.
 */

class Solution {
    public int lengthOfLIS(int[] nums) {
        // Declaring the dp Array and initializing the base values
        int[] lisLengthEndingAtI = new int[nums.length];
        Arrays.fill(lisLengthEndingAtI, 1);
        
        // global max length variable to store the maximum length amoung all possible increasing sequences.
        int maxLen = 1;
      
        // Outer loop to start from index 1 to nums.length - 1  
        for(int i = 1; i < nums.length; i++) {
          
            // Inner loop to check from index 0 to i.
            for(int j = 0; j < i; j++) {
              
                // If the number at index j is less than that at index i, then including number at index i can also form a increasing sequence.
                // => there is a chance that this new sequence length can be greater than the length value currently at index i in dpArray.
                if(nums[j] < nums[i]) {
                    lisLengthEndingAtI[i] = Math.max(lisLengthEndingAtI[i], lisLengthEndingAtI[j] + 1);
                }
            }
            
            // Updating the global max value if this sequence is the longest increasing sequence so far
            maxLen = Math.max(maxLen, lisLengthEndingAtI[i]);
        }
        
        // Return result at the end
        return maxLen;
    }
}
