/*
    https://leetcode.com/problems/combination-sum-iv
*/

/*
    Approach
        -   Create a dp array with size target+1 and initialise
            all cells to 0
        -   Set 0th index to 1 as 0 can always be achieved using []
        -   Iterate thru each index in the dp array. The value at
            i+[entry in nums] will be the sum of existing value at
            that index and the value at ith index of dp array
        -   Required value will be the value at target index of dp array
*/

/*
    Complexity
        -   Time = O(n*m), n= target, m= number of entries in the nums array
        -   Space = O(n), n= target+1
*/


class Solution {
public:
    int combinationSum4(vector<int>& nums, int target) {
        //This must be unsigned int. Else it throws out of bounds error
        vector<unsigned int> dp(target+1, 0);
        
        //Set 0th index dp to 1
        dp[0] = 1;
        
        //iterate from 0 thru target and populate the dp array as mentioned
        //under "Approach" section
        
        for(int i=0; i<target; i++)
        {
            for(int j=0; j< nums.size(); j++)
            {
                unsigned int pos = i+nums[j];
                if(pos <= target)
                    dp[pos] +=dp[i];
            }
        }
        
        //dp[target] will have the required value
        return dp[target];
    }
};