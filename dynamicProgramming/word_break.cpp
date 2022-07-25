/*
    https://leetcode.com/problems/word-break
*/

/*
    Approach
        -   Create a dp array of size s.size() + 1
        -   Set 0th index of this array to true as an empty string can always
            be formed from any dict given.
        -   We set to true the indices which can be reached using any of the words in the wordDict from each index i.
        -   To get a clearer picture, refer to tabulation technique of canConstruct problem
            (Towards the end of the video)
            https://www.youtube.com/watch?v=oBt53YbR9Kk&t=16240s
*/

/*
    Complexity 
        -   Time = O(mnk), m = string size, n= number of words in the dict, k = length of word in dict, as we use substr operation in the nested loop
            Not max length of k is m.
            So time complexity = O(m*m*n)
        -   Space = O(m), m =string size + 1
*/


class Solution {
public:
    bool wordBreak(string s, vector<string>& wordDict) {
        //Create a vector with size = string size + 1
        vector<bool> dp(s.size()+1, false);
        //Set 0th index of dp vector to 0 as emoty string
        //can be always constructed
        dp[0] = true;
        
        //traverse through each index in the string and check if
        //we can reach any index i by using any of the words in the dict
        for(int i=0; i< s.size(); i++)
        {
            for(int j=0; j<wordDict.size(); j++)
            {
                string substring = s.substr(i, wordDict[j].size());
                
                //we need to do below operation only if dp array at ith position is
                //true, i.e., we were able to create the string till here using dict
                //word(s)
                
                if(dp[i] == true && substring.compare(wordDict[j]) == 0)
                {
                    //setting i+ length of word in dict to true as this string can be
                    //reached using this dict word
                    float pos = i+wordDict[j].size();
                    if(pos <= s.size())
                    {
                        dp[pos] = true;
                        //if we see s.size() index of dp array is set to true, we can                               //return true. No need to traverse further as we have concluded the                         //string can be created in some way
                        if(dp[s.size()] == true)
                            return true;
                    }
                }
            }
        }

        return false;
    }
};