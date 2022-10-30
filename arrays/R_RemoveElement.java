/*
 * Question:
 * https://leetcode.com/problems/remove-element
 */

/*
 * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
 * The relative order of the elements may be changed.
 * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums.
 * More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result.
 * It does not matter what you leave beyond the first k elements.
 *
 * Return k after placing the final result in the first k slots of nums.
 *
 * Example 1:
 * Input: nums = [3,2,2,3], val = 3
 * Output: 2, nums = [2,2,_,_]
 * Explanation: Your function should return k = 2, with the first two elements of nums being 2.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 *
 * Example 2:
 * Input: nums = [0,1,2,2,3,0,4,2], val = 2
 * Output: 5, nums = [0,1,4,0,3,_,_,_]
 * Explanation: Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4.
 * Note that the five elements can be returned in any order.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 * 
 * Solution Approach Used:
 * Though the question when read seems tricky, this is tagged as an easy question.
 * However, when seen for the first time, it might be little confusing, considering we need to solve in linear time and time bound interviews.
 * But when you read it in calm way or picturize after watching the solution or explanation, it becomes very easy.
 *
 * We can use the two pointer approach for solving this problem in linear time.
 * The intention is to put all elements other than val to left of array and return the count of elements other than val.
 *
 * For iterating the array, we can use a right pointer, that iterates from index 0 to length of array.
 *
 * We can use a left pointer with initial value of 0(index 0) to insert any element we come across in the array while iterating the array.
 * When we insert an element at left, we can increment the left pointer, so that any new element not equal to val comes, it will be inserted in new left position.
 *
 * At the end of the loop, the left pointer will have the number of elements not equal to val and we can return it.
 *
 * Time Complexity: O(n), Space Complexity: O(1) where n is length of array
 * Check solution in youtube for further explanation
 * https://www.youtube.com/watch?v=Pcd1ii9P9ZI
 *
 */
class Solution {
    public int removeElement(int[] nums, int val) {
      
        // Left pointer value initialized to 0.
        // At this point, we dont know if theres any element not equal to val in array
        int left = 0;
      
        // Iterate over the array, using right pointer from 0 to array length
        for(int right = 0; right < nums.length; right++) {
          
            // If the element is not equal to val
            // We know this element can be inserted at the left position
            // We also see that, at this point, we have previous value of left + 1 elements other than val
            // Also, after inserting the element at left, the left pointer has to be incremented, so that in coming iterations, if an element other than val comes, it can be inserted at new left position
            if(nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
        }
      
        // Returning number of elements other than val in array as left pointer will have the count of number of times we have swapped elements in array
        return left;
    }
}
