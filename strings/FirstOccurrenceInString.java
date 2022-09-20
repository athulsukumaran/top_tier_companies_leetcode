/*
 * Question:
 * https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/
 */

/*
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * 
 * Example 1:
 * Input: haystack = "sadbutsad", needle = "sad"
 * Output: 0
 * Explanation: "sad" occurs at index 0 and 6.
 *The first occurrence is at index 0, so we return 0.
 * 
 * Example 2:
 * Input: haystack = "leetcode", needle = "leeto"
 * Output: -1
 * Explanation: "leeto" did not occur in "leetcode", so we return -1.
 * 
 * Solution Approach Used:
 * This is a straight forward sliding window question.
 * We are interested in finding the minimum value of index(first occurrence index) such that sub string (window) and needle are equal.
 * 
 * We can use a for loop to iterate from i = 0 to i <= (haystack length - needle length).
 * This is because the substring function in java, the second argument is the length of substring starting from the first index.
 * Hence we dont want if in the last window, the index goes beyond length of haystack.
 *
 * So in the loop, for each iteration, get the substring from i to i + needle length.
 * Check if this window is equal to needle. Return the first occurence index.
 *
 * If no matches are found, return -1 at the end of loop
 *
 * Time Complexity: O(n*m), Space Complexity: O(m) where n is length of haystack array and m is the length of needle.
 */

class Solution {
    public int strStr(String haystack, String needle) {
      
        // Get the length of haystack and needle
        int needleLength = needle.length();
        int haystackLenght = haystack.length(); 
      
        // Iterate from i = 0 to i <= haystack length - needle length (O(n))
        for(int i = 0; i <= haystackLenght - needleLength; i++) {
          
            // Get the substring window of needle length starting from i 
            String window = haystack.substring(i, i + needleLength);
          
            // Check if window equals needle (O(m))
            if(window.equals(needle)) {
                return i;
            }
        }
      
        // If no window found in haystack matching needle, Return -1
        return -1;
    }
}
