/*
 * Question:
 * https://leetcode.com/problems/longest-common-prefix/
 */

/*
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 * 
 * Example 1:
 *
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 *
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 *
 * Solution Approach Used:
 * We will use a helper method to get the longest common prefix for 2 strings
 * In the helper method, We will iterate a for loop from 0 till the condition that i less than string 1 length AND i less than string 2 length
 * 
 * In the main method, we will do the greedy iteration
 * Initialize the longest common prefix to be the first string in the array of strings
 * Iterate the for loop from i = 1 to i < array length
 * longest common prefix for strings upto i would be the (longest common prefix for string upto i - 1) AND (string at index i)
 * After the loop return the longest common prefix
 *
 * Time Complexity: O(n*m), Space Complexity: O(1) where n is length of strings array and m is the max length of common prefix
 */

class Solution {
  
    // Helper method to find longest common prefix for 2 strings
    private StringBuilder longestCommonPrefixForTwoStrings(StringBuilder a, String b) {
      
        // Longest common prefix variable declared
        StringBuilder longestCommonPrefix = new StringBuilder();
      
        // Iterate from i = 0 to i < string 1 length AND i < string 2 length
        for(int i = 0; i < a.length() && i < b.length(); i++) {
          
            // If char at index i not same for both the strings then break the loop
            if(a.charAt(i) != b.charAt(i)) {
                break;
            }
          
            // Append the char at index i to longest common prefix variable if they are same in both strings
            longestCommonPrefix.append(a.charAt(i));
        }
      
        // Return the result
        return longestCommonPrefix;
    }
    
    // Main method
    public String longestCommonPrefix(String[] strs) {
      
        // Longest common prefix variable declared for result
        StringBuilder longestCommonPrefix = new StringBuilder();
      
        // Initialize the longest common prefix to first string in the array
        longestCommonPrefix.append(strs[0]);
      
        for(int i = 1; i < strs.length; i++) {
          
            // Check the longest common prefix for string at index i and longest common prefix so far
            StringBuilder newLongestCommonPrefix = longestCommonPrefixForTwoStrings(longestCommonPrefix, strs[i]);
          
            // If new longest common prefix is empty then return empty string and exit
            if(newLongestCommonPrefix.length() == 0) {
                return "";
            }      
          
            // Reseting the longest common prefix to result variable
            longestCommonPrefix.setLength(0);
            longestCommonPrefix.append(newLongestCommonPrefix);
        }
      
        // Return the longest common prefix value after the loop
        return longestCommonPrefix.toString();
    }
}
