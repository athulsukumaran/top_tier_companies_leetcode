/*
 * Question:
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 */

/*
 * CountAndSay(n) = say the number of repetition for each digit in countAndSay(n - 1)
 * Base case CountAndSay(1) = "1"
 *
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 *
 * Solution Approach Used:
 * We will use a helper method areAnagrams which accepts the char occurrences array for 2 strings and check if they are anagrams
 * In the main method, wel'll initialize a list of indexes and retrun empty list if p.length() > s.length()
 * Else first we'll count the occurrences of chars in p as well as count occurrences of chars in window of size p.length() from index 0 for s
 * if they are anagrams add index 0 to the result list
 * Iterate from i = 1 to s.length() - (p.length() - 1) and in each iteration decrement the count of i - 1 th char in s and increment the count of i + (p.length() - 1) th char 
 * Check if the new window is anagram and add to the result list accordingly
 * Time Complexity: O(n), Space Complexity: O(1)
 */

class Solution {
    private boolean areAnagrams(int[] string1CharCountArray, int[] string2CharCountArray) {
        for(int i = 0; i < 26; i++) {
            if(string1CharCountArray[i] != string2CharCountArray[i]) {
                return false;
            }
        }
        return true;
    }
    
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> indexList = new ArrayList<>();
      
        // Check if p.length() > s.length() and returning empty list if yes
        if(p.length() > s.length()) {
            return indexList;
        }        
      
        // Declaring arrays to count the occurrences of chars in p and window of s 
        int[] pCharCountArray = new int[26];
        int[] windowCharCountArray = new int[26];
      
        // Initial checking
        for(int i = 0; i < p.length(); i++) {
            pCharCountArray[p.charAt(i) - 'a']++;
            windowCharCountArray[s.charAt(i) - 'a']++;
        }
        if(areAnagrams(pCharCountArray, windowCharCountArray)) {
            indexList.add(0);
        }
      
        // Updating char occurrences count array for window of s from index 1 to s.length() - (p.length() - 1)
        for(int i = 1; i < s.length() - (p.length() - 1); i++) {
            windowCharCountArray[s.charAt(i - 1) - 'a']--;
            windowCharCountArray[s.charAt((p.length() - 1) + i) - 'a']++;
            if(areAnagrams(pCharCountArray, windowCharCountArray)) {
                indexList.add(i);
            }
        }
      
        return indexList;
    }
}
