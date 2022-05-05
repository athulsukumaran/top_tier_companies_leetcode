/* Problem link - https://leetcode.com/problems/longest-repeating-character-replacement/

*/

/* Solution Approach
    - We use sliding window technique here
    - Traverse through the string and increment count map entry for that character
    - Max repeated char in the window would be the one having highest count entry.
        Lets say the count is n
    - k-n would be the number of other characters
    - if the (window size - n) exceeded k, we will increment left of sliding window (i)
    - Ans would be the max of sliding window size seen till the end
*/

/* Complexity Analysis
    - Time complexity = O(n)
    - Space complexity = O(m) , m= string size
*/


class Solution {
public:
    int characterReplacement(string s, int k) {
        // maxi - Most repeating char in the window
        int maxi=0;
        int i=0,j=0;
        
        // mp - To keep track and update most repeating char found till now 
        unordered_map<char, int> mp;
        int ans=0;
        
        while(j<s.length())
        {
            //increment the count of the char in the map
            mp[s[j]]++;
            
            //if the current char count is more than the current max repeated
            //element, set current char to most repeated element
            maxi = max(maxi, mp[s[j]]);
            
            //if the diff of window and the number of max repeating char has gone beyond k,
            //increment left pointer of the window and decrease count of that char
            if(j-i+1-maxi > k)
            {
                mp[s[i]]--;
                i++;
            }
            
            //ans will be the max of window we saw till now
            ans = max(ans, (j-i+1));
            j++;
        }
        return ans;
    }
};