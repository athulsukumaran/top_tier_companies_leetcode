/*
 * Question:
 * https://leetcode.com/problems/climbing-stairs/
 */

/*
 * You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top ?
 *
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 *    1. 1 step + 1 step
 *    2. 2 steps
 *
 *
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 *    1. 1 step + 1 step + 1 step
 *    2. 1 step + 2 steps
 *    3. 2 steps + 1 step
 *
 * Solution Approach Used:
 * This is an extension of fibonacci program using tabulation dynamic programming approach
 * We know that if person reaches n - 1 th step or n - 2 th step, he can reach top by climbing 1 or 2 step accordingly
 * This breaks the problem in finding the number of ways he can reach to n - 1 th step and n - 2 th step respectively ans summing them
 *
 * The base cases are if n = 1 => ways = 1, if n = 2 => ways = 2
 * 
 * For n > 2, create a dp array where the number of ways to reach number m will be stored in index m - 1
 * Define the base values for index 0 and 1 for n = 1 and 2 respectively and iterate the loop from n = 2 to n and store the values at each index accordingly
 * Return the value at index  n - 1 in the dpArray as result
 *
 * Time Complexity: O(n), Space Complexity: O(n)
 */

class Solution {
    public int climbStairs(int n) {
      
        // Handling base cases
        if(n == 1) {
            return 1;
        } else if(n == 2) {
            return 2;
        } else {
          
            // If n > 2
          
            // Initializing dpArray base cases
            int[] dpArray = new int[n];
            dpArray[0] = 1;
            dpArray[1] = 2;
          
            // Iterate from i = 2 to n and calculate values for each index as sum of values at previous 2 indexes.
            for(int i = 2; i < n; i++) {
                dpArray[i] = dpArray[i - 1] + dpArray[i - 2];
            }
          
            // Return value at index n - 1
            return dpArray[n - 1];
        }
    }
}
