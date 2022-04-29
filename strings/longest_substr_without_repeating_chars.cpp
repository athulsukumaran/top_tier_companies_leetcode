/* Link
    https://leetcode.com/problems/longest-substring-without-repeating-characters/
*/

/* Approach
    - We use sliding window technique here using a left and a right pointer.
    - We use an array of 256 size to track characters used in the string, here the name is         "store".
    - We increment store[ascii_val_of_char_at_right] for each character we encounter.
    - If the value is greater than 1, it means the character was encountered more than once.
    - We will reduce the window size from left and decrement              store[ascii_val_of_char_at_left] till the repeated char is removed from the window.
    - Ans will be max of sliding window size we had till now
    - Explained in this video
        https://www.youtube.com/watch?v=wiGpQwVHdE0
*/

/* Complexity analyis
    - O(N) time complexity
    - O(M) space complexity - M is the number of chars = 256
    
*/

class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        int store[256] = {0};
        int l=0, r=0;
        int ans=0;
        
        while(r<s.length())
        {
            store[s[r]]++;
            while(store[s[r]]>1)
            {
                store[s[l]]--;
                l++;
            }
            
            ans = max(ans, r-l+1);
            r++;
        }
        
        return ans;
    }
};