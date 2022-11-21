/*
 * Question:
 * https://leetcode.com/problems/reverse-vowels-of-a-string/
 */

/*
 * Given a string s, reverse only all the vowels in the string and return it.
 * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both cases.
 *
 * Example1:
 * Input: s = "leetcode"
 * Output: "leotcede"
 *
 * Solution Approach Used:
 * We will use a helper method to check if the character is a vowel or not and return a boolean value
 *
 * In the main method, we will initialize a char array for the string string.
 * We will use 2 pointers left and right to move in forward and backward directions along the char array.
 * Initialize left pointer as 0 to move forward and right as len(str) - 1 to move backwards.
 * Iterate the while loop inside main method till left is less than right
 * Inside the main while loop, start an inner while loop and increment the left till the character at left is a vowel and break when it reaches a vowel
 * Similarly start an inner while loop and decrement the right till the character at right is a vowel and break when it reaches a vowel
 * If left is less than right, we have found the vowels from start and end and their indexes. We now need to swap the characters at indexes left and right.
 * After the outer while loop, return the string value of updated character array
 * Time Complexity: O(n), Space Complexity: O(n) where n is length of string
 */

class Solution {
  
    // Helper method to check if c is a vowel
    private boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            return true;
        }
        return false;
    }  
  
    // Main method
    public String reverseVowels(String s) {
      
        // Initializing char array with value of string and left and right pointers
        char[] result = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;
      
        // Iterating till left less than right
        while(left < right) {
          
            // Inner loop incrementing left till the next vowel is found and breaking inner loop once it reaches a vowel
            while(left < right) {
                if(isVowel(result[left])) {
                    break;   
                }
                left++;
            }
          
            // Inner loop decrementing right till next vowel from back is found and breaking inner loop once it reaches a vowel
            while(left < right) {
                if(isVowel(result[right])) {
                    break;
                }
                right--;
            }
          
            // If left is less than right, then swap the characters at left and right and increment left and decrement right and proceed to outer while loop execution
            if(left < right) {
                char temp = result[left];
                result[left] = result[right];
                result[right] = temp;
                left++;
                right--;
            }
        }
      
        // Return the string value of result char array after outer loop is completed
        return String.valueOf(result);
    }
}
