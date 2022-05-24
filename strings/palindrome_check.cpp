/*
    https://leetcode.com/problems/valid-palindrome
*/

/* Approach
    - Traverse the string from both sides using two pointers.
    - Advance the pointer if the char at that pointer is not alphanumeric
    - Check tolower value of char at left and right pointers.
        If at any iteration they are not the same, retuen false
*/

/* Complexity
    - Space - O(1)
    - Time- O(n)
*/

class Solution {
public:
    bool isPalindrome(string s) {
       //Have i as the left pointr and j as the right pointer 
       for(int i=0, j=s.length()-1; i<j; i++,j--)
       {
           /*Its important to have i<j here as otherwise 
            there will be runtime error for strings without
            any characters, for example. */
           
           //advance i forward if non alphanumeric is encountered
           while(i<j && !isalnum(s[i]))
           {
               i++;
           }
           
           //advance j backward if non alpha numeric is encountered
           while(i<j && !isalnum(s[j]))
           {
               j--;
           }
           
           //compare the lower case version of chars at i and j
           if(tolower(s[i]) != tolower(s[j]))
           {
               return false;
           }
       }
       return true;  
    }
};