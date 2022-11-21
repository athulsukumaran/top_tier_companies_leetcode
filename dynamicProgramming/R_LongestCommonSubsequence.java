/*
 * Question:
 * https://leetcode.com/problems/longest-common-subsequence
 */

/*
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 * Example 1: 
 * Input: text1 = "abcde", text2 = "ace" 
 * Output: 3  
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 *
 * Example 2:
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 *
 * Example 3:
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 * Solution Approach Used:
 * The brute force approach to check each subsequence of one string in another would take O(m * (2^n)) time as there can be 2^n subsequences for a string of length n and m is the length of other string.
 * This can be reduced using dynamic programming to O(mn) time complexity using a 2D dp array of size mn, where m and n are the length of 2 strings.
 * We will try to solve this using bottom up approach and tabulation.
 * Lets say the base cases, we know that the minimum length of a longest common sequence of 2 empty strings would be zero.
 * This means that all entries in last column and last row in 2D dp Array is zero.
 * 
 * If suppose the characters at index i for text 1 and index j for text 2 are equal, then the entry dpArray[i][j] will be 1(as the chars are equal) + dpArray[i + 1][j + 1] (the diagonal cell value, as this is the longest common sequence for text1 and text2 when iterated from back without including chars at i and j for text1 and text2).
 * If the characters at i and j are not equal, then the longest common sequence would be the Max length of longest common sequence among 2 sub problems, either including i th character from text 1 and not including j th character from text2 or vice verca.
 *
 * So we can declare a 2D dp array to store the length of a longest common sequence and backtrack the array and calculate the values at each cell.
 * The result will be the value computed for index 0,0
 * 
 * Time Complexity: O(mn), Space Complexity: O(mn).
 *
 * Youtube link
 * https://www.youtube.com/watch?v=Ua0GhsJSlWM
 */

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
      
        // Stroing the length of input strings
        int n = text1.length(), m = text2.length();
      
        // Declaring the 2D dpArray
        int[][] lcsDPArray = new int[n + 1][m + 1];
        
        // Iterating over the first string from back
        for(int i = n - 1; i >= 0; i--) {
          
            // Iterating over the second string from back
            for(int j = m - 1; j >= 0; j--) {
              
                // If chars at index i and j for text1 and text2 are equal, we need to add 1 to the longest common sequence of text1.substring(i + 1) and text2.substring(j + 1)
                if(text1.charAt(i) == text2.charAt(j)) {
                  
                    // dpArray[i + 1][j + 1] will have the longest common sequence from back till index i and j for text1 and text2
                    lcsDPArray[i][j] = 1 + lcsDPArray[i + 1][j + 1];
                } else {
                  
                    // If the chars at i and j are not equal, we have 2 sub problems,
                    // The longest common sequence would be maximum of common sequence of text1.sustring(i) and text2.substring(j + 1) or common sequence of text1.substring(i + 1) and text2.substring(j)
                    lcsDPArray[i][j] = Math.max(lcsDPArray[i + 1][j], lcsDPArray[i][j + 1]);
                }
            }
        }
        
        // After the loops, we have calculated the longest common sequence for 2 strings starting from index 0 for both strings
        // Returning dpArray[0][0] as result
        return lcsDPArray[0][0];
        
    }
}
