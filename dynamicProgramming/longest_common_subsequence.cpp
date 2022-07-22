/*
    https://leetcode.com/problems/longest-common-subsequence
*/

/*
    Approach
        -   Create a 2D array with size (text1+1)*(text2+1)
        -   Initialise all cells to 0
        -   Start from 0,0. Update the diagonal cell with value as per the following
            condition,
                *   If the char at text1 and text2 at that particular cell are equal,
                    make this cell's value as the current cell value plus 1.
                *   Else, if they are not equal the value at the diagonal cell will 
                    be the max of (i+1, j) and (i, j+1)
        -   The intuition is we need to increment to the cell whenever the chars at text 1
            and text2 match, as this particular character contributes to the common subsequence.
        -   You can watch the following video to get more clarity even though the implementation is sightly different.
            https://www.youtube.com/watch?v=Ua0GhsJSlWM
*/

/*
    Complexity
        -   Time = O(mn) - m= size of text1+1 , n = size of tex2+1
        -   Space = O(mn)
*/
        

class Solution {
public:
    int longestCommonSubsequence(string text1, string text2) {
        //create a matrix of size (text1+1 * tex2+1)
        int m[1001][1001] = {0};
        //nested loops to traverse the matrix
        for (int i = 0; i < text1.size(); ++i)
            for (int j = 0; j < text2.size(); ++j)
                //update diagonal cell according to the condition specified in "Approach" section
                m[i + 1][j + 1] = text1[i] == text2[j] ? m[i][j] + 1 : max(m[i + 1][j], m[i][j + 1]);
        
        //the last cell in the matrix will have the value we need
        return m[text1.size()][text2.size()];        
    }
};