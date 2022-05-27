/*
 * Question:
 * https://leetcode.com/problems/group-anagrams/
 */

/*
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * Solution Approach Used:
 * We will use a helper method areAnagrams which accepts the char occurrences array for 2 strings and check if they are anagrams
 * In the main method, wel'll keep a tracking array and update value to 1 for index i if string at index i is already grouped in our result list. This will allow us to skip the strings from repeated checking.
 * We'll have an outer list with inner lists representing each group of anagrams
 * Iterate the input string array from size 0 to array length - 1 in outer loop
 * Check if the string is already grouped from the tracking array. If not then create a new inner list and add the string to inner list.
 * Start an inner loop from j = i + 1 to array length - 1 and check if String at index i and index j are anagrams with the helper method and add to inner list if they are anagrams
 * After inner loop execution before going to outer loop, add the inner list to outer list.
 * Finally return the outer list
 * Time Complexity: O(n ^ 2) in worst case, Space Complexity: O(n)
 */

class Solution {
    // Helper method to check if two strings are anagrams
    private boolean areAnagrams(String string1, String string2) {
        int[] charCountsArray = new int[26];
        if(string1.length() != string2.length()) {
            return false;
        }
        for(int i = 0; i < string1.length(); i++) {
            char c = string1.charAt(i);
            char d = string2.charAt(i);
            charCountsArray[c - 'a']++;
            charCountsArray[d - 'a']--;
        }
        for(int i = 0; i < 26; i++) {
            if(charCountsArray[i] != 0) {
                return false;
            }
        }
        return true;
    }
    
    // Main method
    public List<List<String>> groupAnagrams(String[] strs) {
      
        // List of Lists for storing the result
        List<List<String>> outerList = new ArrayList<>();
      
        // Tracking array of size strs.length. Update value at index i in tracking array if strs[i] is already grouped
        int[] stringAlreadyGrouped = new int[strs.length];
      
        // Start checking from 0 to strs.length - 1 in input string array
        for(int i = 0; i < strs.length; i++) {
          
            // Check if string at i not already grouped and added to result 
            if(stringAlreadyGrouped[i] != 1) {
              
                // Create an inner list and add string at i and all anagrams of it to inner list
                List<String> innerList = new ArrayList<>();
                innerList.add(strs[i]);
              
                // Iterate from j = i + 1 to strs.length - 1 and check if strs[i] and strs[j] are anagrams
                for(int j = i + 1; j < strs.length; j++) {
                    
                    // If string at j is already grouped, then no need to group again, All anagrams of it will be already be grouped
                    if(stringAlreadyGrouped[j] != 1) {
                      
                        // If strs[i] and strs[j] are angrams, then we can add the strs[j] to the inner list group and update the tracking array value to 1
                        if(areAnagrams(strs[i], strs[j])) {
                            innerList.add(strs[j]);
                            stringAlreadyGrouped[j] = 1;
                        }                
                    }
                }
              
                // Add new inner list created to outer list
                outerList.add(innerList);
            }
        }
        return outerList;
    }
}
