/*
 * Question:
 * https://leetcode.com/problems/valid-palindrome/
 */

/*
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, 
 * it reads the same forward and backward. Alphanumeric characters include letters and numbers.
 *
 * Given a string s, return true if it is a palindrome, or false otherwise.
 * 
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 *
 * Solution Approach Used:
 * We will initialize two pointers - left = 0 to move forward from start and right = length(string) - 1 to move backwards from end
 * We will use a while loop and iterate till left is less than right
 * We will need to check if character at left and right are both letters or numbers and if yes we need to compare them after converting to lowercase and return false if they are different, else increment left pointer and decrement right pointer
 * Incase if the character at left is not a letter or number, increment the left pointer
 * Similarly incase if the character at right is not a letter or number, decrement the right pointer
 * Incase the loop is completed without returning false, the string is palindrome. Add return true statement outside the loop.
 * 
 * Time Complexity: O(n), Space Complexity: O(1) where n is length of string
 */

class Solution {
    public boolean isPalindrome(String s) {
      
        // Initializing left and right pointers
        int left = 0;
        int right = s.length() - 1;
      
        // Iterate the loop till left pointer is less than right pointer
        while(left < right) {
          
            // Get characters at left and right positions
            char c = s.charAt(left);
            char d = s.charAt(right);
          
            // Check if both characters at left and right positions are letters or numbers
            if(Character.isLetterOrDigit(c) && 
               Character.isLetterOrDigit(d)) {
              
                // Convert the characters to lower case as we need to ignore case
                if(Character.toLowerCase(c) != Character.toLowerCase(d)) {
                  
                    // return false if they are not equal
                    return false;
                }
              
                // Increment left, Decrement right if they are equal
                left++;
                right--;
            } else {
              
                // If character at left is not a letter or number, increment the left pointer
                if(! Character.isLetterOrDigit(c)) {
                    left++;
                }
              
                // Similarly if character at right is not a letter or number, decrement the right pointer
                if(! Character.isLetterOrDigit(d)) {
                    right--;
                }
            }
        }
      
        // Return true, if loop is completed and string is checked
        return true;
    }
}
