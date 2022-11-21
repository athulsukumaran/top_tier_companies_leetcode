/*
 * Question:
 * https://leetcode.com/problems/majority-element
 */

/*
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array. 
 *
 * Example 1:
 * Input: nums = [3,2,3]
 * Output: 3
 *
 * Example 2:
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 *
 * Constraints:
 * n == nums.length
 * 1 <= n <= 5 * 104
 * -10^9 <= nums[i] <= 10^9
 
 * Solution Approach Used:
 * Approach 1 - Brute Force
 * The brute force way is to find the count of each numbers in the array using hashmap and finding the most occurring one.
 * This is technically correct and solves the problem in O(n) time and O(n) space.
 * But since we are given the condition that, one element occurs more than n / 2 time and we are to find this majority element, as a follow up, we might be asked to optimize the solution to reduce space complexity to O(1)
 * In that case we can use the Boyer Moore Algorithm as in approach 2 to reduce the space complexity
 * 
 * Approach 2 - Boyer Moore Algorithm
 * Here we can safely conclude that the count of majority elements minus that of remaining nums in array is atleast 1.
 * This is because we are guaranteed so in the question.
 * Hence we can assume an element in array to be the result, say the first num. Then we can keep the count of the result we assigned.
 * While iterating through the array, if the num at index i and result variable value are equal, increment the result num count.
 * If they are not equal, decrement the count of result number count.
 * If at any point of time, the result number count becomes less than zero, reassign it to the number at current index i, and reassign the result number count again to 1.
 * As the loop finishes, result variable will have the majority element.
 * The result number count is not exactly the majority elements count here, but the count by which it is more than remaining numbers in array taken together
 * Time Complexity: O(n), Space Complexity: O(1) where n is length of array
 *
 * Check the youtube link below for solution
 * https://www.youtube.com/watch?v=7pnhv842keE
 *
 */
 
 class Solution {
    public int majorityElement(int[] nums) {
//         //--------------Brute Force Approach-------------------
        
//         Map<Integer, Integer> numCountMap = new HashMap<>();
//         int maxCount = 0, res = -100000001;
        
//         for(int i = 0; i < nums.length; i++) {
//             int num = nums[i];
//             if(numCountMap.get(num) != null) {
//                 numCountMap.put(num, numCountMap.get(num) + 1);
//             } else {
//                 numCountMap.put(num, 1);
//             }         
            
//             if(maxCount < numCountMap.get(num)) {
//                 res = num;
//                 maxCount = numCountMap.get(num);
//             }
//         }
        
//         //--------------Brute Force Approach End----------------
        
        
        // Initializing count to 0 and res value to value out of input constraints
        int resNumCount = 0, res = -100000001;
        
        // Iterating over the input array
        for(int i = 0; i < nums.length; i++) {
            int num = nums[i];
            
            // If res and num at curr index is same, then increment the count of res
            // Else decrement it
            if(res == num) {
                resNumCount++;
            } else {
                resNumCount--;
            }
            
            // If the count of res is less than zero, reassign res to num and count of result to 1
            if(resNumCount < 0) {
                res = num;
                resNumCount = 1;
            }
        }
        
        // Return result
        return res;       
    }
}
