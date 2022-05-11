/* Link
    https://leetcode.com/problems/valid-anagram
*/

/*
    Approach
    - Since s and t consists of only lowercase alphabets, we can create a count array of size 26
    - We traverse through the string and increment the count of char in s
    - At the same time we decrement the count of char in t
    - Finally we check if the count array has all 26 locations set to 0 or not
        (Since count of chars in s would cancel out with decremented count of char in t)
*/

/*
    Time complexity - O(n) where n is the size of string
    Space complexity - Constant size of 26
*/

class Solution {
public:
    bool isAnagram(string s, string t) {
        // Since arr is faster than maps and map is not exactly needed here,
        // we just use a char array of size 26
        char alphaArr[26] = {0};
        
        // If both strings are of different sizes, obviously its not an anagram
        if(s.length() != t.length())
        {
            return false;
        }
        
        //traverse thru both s and t. Increment char count in s, decrement that in t
        for(int i=0; i<s.length(); i++)
        {
            alphaArr[s[i] - 'a']++;
            alphaArr[t[i] - 'a']--;
        }
        
        //all indices in the array must have value 0
        for(int j=0; j<26; j++)
        {
            if(alphaArr[j] !=0 )
                return false;
        }
        
        return true;
    }
};