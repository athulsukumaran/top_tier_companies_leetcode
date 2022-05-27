/*
 * Question:
 * https://leetcode.com/problems/valid-palindrome-ii/
 */

/*
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 *
 * Input: s = "abca"
 * Output: true
 * Explanation: You could delete the character 'c'.
 *
 * Solution Approach Used:
 * We will use a helper method to check if part of a string between left and right indexes is palindrome or not
 * In the helper method, We will use a while loop and iterate till left is less than right and return if the part of string between the indexes are palindrome.
 * 
 * In the main method, we will do the palindrome checking, but this time with a small modification.
 * Initialize left pointer as 0 to move forward and right as len(str) - 1 to move backwards.
 * Iterate the while loop inside main method till left is less than right
 * If character at left is equal to character at right then increment left pointer and decrement right pointer.
 * Else check if either part of string from left to right - 1 or part of string from left + 1 to right is palindrome and return the result.
 * Incase the loop is completed without returning false in main method, the string is palindrome without needing to remove any characters. Add return true statement outside the loop. 
 *
 * Time Complexity: O(n), Space Complexity: O(1) where n is length of string
 */

class Solution {
    private boolean isPalindrome(String s, int left, int right) {
      
        // Iterate the loop till left is less than right
        while(left < right) {
          
            // Check if character at left is not equal to character at right and return false as string between left and right is not palindrome
            if(s.charAt(left) != s.charAt(right)) {
                return false;
            }
          
            // Increment left, Decrement right if the characters are equal
            left++;
            right--;
        }
      
        // Return true if loop is completed
        return true;
    }
    
    public boolean validPalindrome(String s) {
      
        // Initialize left = 0 and right = string length - 1 to move forward and backward
        int left = 0, right = s.length() - 1; 
      
        // Iterate the loop till left is less than right
        while(left < right) {
          
            // Check if character at left is not equal to character at right
            if(s.charAt(left) != s.charAt(right)) {
              
                // If they are not equal, we can either remove character at left or character at right and check if the part of strings obtained is palindrome.
                // If one of them is palindrome, one of them will return true and we can return true as removing 1 character either at left or right, we are able to make string palindrome
                // If both of them are not palindrome, both of them will return false and we can return false as we cannot make input string palindrome by removing 1 character from either left or right
                return isPalindrome(s, left, right - 1) || isPalindrome(s, left + 1, right);
            }
          
            // Increment left, Decrement right if the characters are equal
            left++;
            right--;
        }
        return true;
    }
}
