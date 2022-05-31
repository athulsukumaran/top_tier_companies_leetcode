/* Link
    https://leetcode.com/problems/longest-palindromic-substring
*/

/* Approach
    - For each char in the string use the function extendPalindrome
    - What extendPalindrome does is, it starts two pointer from that 
      particular char in the string and moves each to the left and 
      right respectively. 
    - A palindrome will be symmetrical on both sides. So until the chars
      at these pointers are not matching we advance these pointers to 
      get the largest palindrome.
    - To handle the case with palindrome having even number of chars,
      we call extendPalindrome once again.
    - Youtube link for a better understanding
        https://www.youtube.com/watch?v=XYQecbcd6_c
*/

/* Complexity
   Space - O(1)
   Time - O(n^2)
*/

class Solution {
public:
    //maxLen to track longest palindrome found till now
    int maxLen=0;
    //startPos of the longestPalindrome
    int startPos = 0;
    
    string longestPalindrome(string s) {
        
        //Handle case when length of string is 0 or 1
        int len = s.length();
	    if (len < 2)
		    return s;
        
        // for each char in the string, call extendPalindrome
        for(int i=0; i<len-1; i++)
        {
            // Pass i,i as the search starts from the middle
            extendPalindrome(s, i, i); //assume odd length, try to extend Palindrome as possible
            // We need to check for even length palindromes as well
            extendPalindrome(s, i, i+1); //assume even length.
        }
        
        return s.substr(startPos, maxLen);
    }
    
    void extendPalindrome(string s, int j, int k)
    {
        //advance j to left till 0th index and k to right till size
        while(j>=0 && k<s.length() && s[j] == s[k])
        {
            j--;
            k++;
        }
        //compare maxLen to current substring len and update maxLen
        //Note: maxLen = k-j-1, since j and k is already advanced to
        //a point where the substring is not a palindrome. So, need to
        //decrement 1 from the difference b/w them 
        if(maxLen < k-j-1)
        {
            maxLen = k-j-1;
            startPos = j+1;
        }
    }
    
};