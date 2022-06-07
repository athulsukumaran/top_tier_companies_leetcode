/*
 * Question:
 * https://leetcode.com/problems/find-unique-binary-string/
 */

/*
 * Given an array of strings nums containing n unique binary strings each of length n, return a binary string of length n that does not appear in nums. 
 * If there are multiple answers, you may return any of them.
 * Example 1:
 * Input: nums = ["01","10"]
 * Output: "11"
 * Explanation: "11" does not appear in nums. "00" would also be correct.
 *
 * Solution Approach Used:
 * We will use a char array to store the result binary value.
 * We will iterate over the input string array and we will flip the ith bit in string at index i.
 * The idea is that this way any string in the array will have atleast 1 bit different from the result char array.
 *
 * The solution is found from discuss section
 * https://leetcode.com/problems/find-unique-binary-string/discuss/2106902/O(N)-simple-no-recursion-0-ms
 *
 * Time complexity: O(n), Space complexity: O(n) 
 */
 
 class Solution {
    public String findDifferentBinaryString(String[] nums) {
    
        // Getting length of string nums array and declaring result char array
        int n = nums.length;
        char[] resultBinaryArray = new char[n];
        
        // Iterating over the string nums array
        for(int i = 0; i < n; i++) {
        
            // Flipping ith bit in nums[i]
            // This ensures that result char array and nums[i] differs in the bit at index i (atleast)
            if(nums[i].charAt(i) == '0') {
                resultBinaryArray[i] = '1';
            } else {
                resultBinaryArray[i] = '0';
            }
        }
        
        // Returning the result
        return String.valueOf(resultBinaryArray);
    }
}
