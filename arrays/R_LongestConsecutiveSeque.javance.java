/*
 * Question:
 * https://leetcode.com/problems/longest-consecutive-sequence
 */

/*
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * You must write an algorithm that runs in O(n) time.
 *
 * Example 1:
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * 
 * Example 2:
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 *
 * Solution Approach Used:
 * Approach 1
 * The brute force way is to find the maximum length of the consecutive sequence starting from a given number in an array.
 * For this the time complexity would be O(n ^ 2) for checking for all numbers in the array O(n) to check if a consecutive number exists in array. Hence total time complexity for brute force would be O(n ^ 3) 
 * 
 * Approach 2
 * Here we can do a sort first so that array is sorted. Time Complexity would be O(nlogn) for sorting
 * Then we can iterate the loop from index 1 to length of array and check if the number at current index is equal to (number at (index - 1)) + 1
 * If they are equal, we update the current streak length and else we check if the strak length so far is greater than the longest streak found and update it
 * Though space complexity is O(1), the time complexity is O(nlogn)
 *
 * Approach 3
 * The appraoch is similar to brute force.
 * In brute force we check length of consecutive sequence for all numbers in the array. Also checking in array if number exists using linear search takes O(n) time
 * The search can be optimized by using set, where the search can be done in O(1) time
 * Also the checking code can be optimized to check for sequences starting with number at any index only if the set doesnt contain (number at that index) - 1
 * This is because, if the set already contains (number at index) - 1, then there would be a longer sequence starting with (number at the index) - 1
 * Example, if set contains elements 2, 4, 1, 3
 * The inner while loop to get the count of longest sequence starting with number at any index will not get executed for 2, 4 and 3 as their predecessor already exists in set
 * Hence the inner loop to get the length of streak starting with number at index will be executed for only number 1 in this case
 *
 * The question is same as uploaded by Athul
 * Solutions Approaches available in leetcode
 * Time Complexity: O(n), Space Complexity: O(n) where n is length of array
 */

class Solution {
    public int longestConsecutive(int[] nums) {
        // Variable to store max length of consecutive sequence
        int maxLength = 0;
        
        // Set to store elements in array and for checking if a number is present in array in O(1) time
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        
        // Iterate the array from i = 0 to array length
        for(int i = 0; i < nums.length; i++) {
          
            // Get the number at index i
            int num = nums[i];
          
            // Check if the array contains (number at index i) - 1
            // If yes, then this number would be a part of a longer consecutive sequence containing (number at index i) - 1
            // Hence we can continue checking for next element in array
            // Else check the longest consecutive sequence starting with number at index i
            if(! set.contains(num - 1)) {
                int lengthConsecutiveSeqStartingWithNumsAtI = 0;
              
                // Checking if num is present in array using set contains (O(1) time) and incrementing the length of consecutive sequence starting from number at index i
                while(set.contains(num)) {
                    lengthConsecutiveSeqStartingWithNumsAtI++;
                    num++;
                }
              
                // Update the max length, if the current window length is greater than max window length we got so far 
                maxLength = Math.max(maxLength, lengthConsecutiveSeqStartingWithNumsAtI);
            }
        }
        
        // Return result max window length
        return maxLength;
    }
}
