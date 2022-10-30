/*
 * Question:
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array
 */

/*
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once.
 * The relative order of the elements should be kept the same.
 * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums.
 * More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
 * Return k after placing the final result in the first k slots of nums.
 *
 * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
 *
 * Example 1:
 * Input: nums = [1,1,2]
 * Output: 2, nums = [1,2,_]
 * Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 *
 * Example 2:
 * Input: nums = [0,0,1,1,1,2,2,3,3,4]
 * Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
 * Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 *
 * Solution Approach Used:
 * We can use the two pointer approach for solving this problem in linear time.
 * The intention is to make the unique values in first k index and return k.
 * Since the min value of length of array is 1, and the array is sorted, we know that the first value in the output(after inplace replacement will be nums[0] itself).
 * We can assign this to prev element. Also increment the left pointer(as now we have number of unique elements found so far = 1)
 * Now start a right pointer from index 1 to length of array.
 * In each iteration check if the curr value is equal to previous value. If they are equal, implies the value is not unique.
 * When the current value is not equal to previous value, we have a new unique element that needs to be inserted at the left index.
 * Increment the left pointer(As we have left + 1 unique elements found so far)
 * 
 * At the end of the loop, the right pointer will have gone beyond length of array and left pointer will return the number of unique elements(k) that are placed in first k index.
 *
 * Time Complexity: O(n), Space Complexity: O(1) where n is length of array
 * Check solution in youtube for further explanation
 * https://www.youtube.com/watch?v=DEJAZBq0FDA
 *
 */
class Solution {
    public int removeDuplicates(int[] nums) {
        // Initializing left and prev variables
        int left = 0;
        int prev = nums[left];
      
        // At this point we have 1 unique element found so far
        // Hence we are incrementing left
        left++;
      
        // Iterating the loop from index 1 to length of array
        for(int right = 1; right < nums.length; right++) {
          
            // Value at current index
            int curr = nums[right];
          
            // Check if the curr value is equal to prev unique value
            // if not equal, we found a new unique element that needs to be replaced with element at index left
            if(curr != prev) {
                nums[left] = curr;
              
                // Re assign prev unique value to the latest unique value we found at curr index
                prev = curr;
              
                // Increment the left pointer as now we have one more unique element found so far
                left++;
            }
        }
      
        // Return result at the end of loop
        return left;
    }
}
