/*
 * Question:
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/description/
 */

/*
 * Given an integer array nums, you need to find one continuous subarray that if you only sort this subarray in ascending order, 
 * then the whole array will be sorted in ascending order.
 *
 * Return the shortest such subarray and output its length. 
 *
 * Example 1:
 * Input: nums = [2,6,4,8,10,9,15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 * 
 * Example 2:
 * Input: nums = [1,2,3,4]
 * Output: 0
 * 
 * Example 3:
 * Input: nums = [1]
 * Output: 0
 *
 * Constraints:
 * 1 <= nums.length <= 104
 * -105 <= nums[i] <= 105
 *
 * Solution Approach Used:
 * This can be solved easily, if we consider the 2 scenarios for input
 *
 * 1. If the input array is already sorted in ascending order,
 *            - In this case, every element in input array is greater than its previous element. Hence return 0.
 *
 * 2. If the input array is not sorted, we need to then get the starting and ending indexes for the unsorted array and return the length.
 *      For this, we need to get the below 2 indexes.
 *            - index1 = which is the largest index such that element at this index is not greater than all its previous elements
 *            - index2 = which is the smallest index such that element at this index is not less than all its next elements
 *
 * We'll need helper variables int maxSoFar, maxSoFarIndex, index1 and boolean isSorted. Initialize values for these.
 * For checking if the input array is already sorted, we can iterate in the forward direction.
 * At each index, we'll compare the value at this index with the max of previous indexes.
 * 
 * If the value at this index is always greater than max of its previous indexes throughout the loop, the input array is already sorted. Return 0 in this case.
 *
 * Else set isSorted flag to false, assign the index i to index1 as this index would be a part of unsorted array.
 * We dont want to use Math.max to assign value of index1 as we are iterating in forward direction, in each iteration index1 would have a larger value if its assigned.
 * 
 * Next we need to similarly find an index index2 such that value at this index is not less than all its next element.
 * Similar to the first loop, we'll find index2, but this time we are iterating in backward direction and comparing value at each index with the minimum value of all its next elements so far.
 *
 * After the loop iteration, we'll have the index 1 and index 2. The length of the unsorted array would be the diff of the indexes + 1.
 * Return this length.
 * 
 * Time Complexity: O(n), Space Complexity: O(1) where n is length of array
 *
 */
class Solution {
    public int findUnsortedSubarray(int[] nums) {
      
        // Helper variables to keep track of max value so far and its index and index1 if the array is unsorted.
        int maxSoFar = nums[0];
        int maxSoFarIndex = 0;
        int index1 = 0;

        // boolean flag to check if array is sorted or not
        boolean isSorted = true;

        // Iterating in forward direction
        // maxSoFar will be the max of previous indexes in the if condition first.
        // At this time, if the current element is less than maxSoFar, we need to set the isSorted flag to false and also assign this index to index1.
        // Else, the maxSoFar and its index will be updated with current index and element
      
        // At the end of this loop, index 1 will have the largest index such that it is not greater than all its previous elements
      
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] < maxSoFar) {
                index1 = i;
                isSorted = false;
            } else {
                maxSoFar = nums[i];
                maxSoFarIndex = i;
            }
        }

        // If the input array is not sorted
        if(! isSorted) {
            
            // Helper variables to keep track of min value so far and its index and index2
            int minSoFar = nums[nums.length - 1];
            int minSoFarIndex = nums.length - 1;
            int index2 = nums.length - 1;

            // Iterating in backward direction
            // minSoFar will be the min of next indexes in the if condition first.
            // At this time, if the current element is greater than minSoFar, we need to assign this index to index2.
            // Else, the minSoFar and its index will be updated with current index and element
          
          
            // At the end of this loop, index 2 will have the smallest index such that it is not lesser than all its next elements
            // While assigning index2, we are not using Math.min as we are iterating in backward direction, the value in each iteration will be smaller than its previous value
          
            for(int i = nums.length - 1; i >= 0; i--) {
                if(nums[i] > minSoFar) {
                    index2 = i;
                } else {
                    minSoFar = nums[i];
                    minSoFarIndex = i;
                }
            }
            
            int unsortedArrayLength = index1 - index2 + 1;

            return unsortedArrayLength ;
        }

        // if isSorted flag is true, input array is already sorted, hence return 0
        return 0;
    }
}
