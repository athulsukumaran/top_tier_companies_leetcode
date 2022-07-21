/*
    https://leetcode.com/problems/longest-increasing-subsequence
*/

/*
    Approach
        -   Create a DP array to find LIS at each position of the array
        -   A max variable to track the max LIS till now
        -   Iterate through each element in nums array
        -   In the inner loop traverse from i+1 till end of nums array
        -   Check if the ith element is less than jth. This means there is
            an increasing subsequence present.
        -   If the LIS value of j is less than i+1, i.e. the current subsequence in 
            consideration is longer that the subsequence till now at j,
            update the longest subsequence at j with i+1
        -   Also update value of max.
*/

/*
    Complexity
        -   Time = O(n*n)
        -   Space = O(n)
*/
        

class Solution {
public:
    int lengthOfLIS(vector<int>& nums) {
        //The DP array
        vector<int> lis(nums.size(), 1);
        //Keep track of max LIS till now
        int max = 1;
        
        for(int i=0; i<nums.size(); i++)
        {
            //inner loop starts from the position next to the index under consideration
            for(int j=i+1; j<nums.size(); j++)
            {
                //We need to check dp array values only if the ith elemnt is less than jth
                //Only in the above case, there is an increasing subsequence
                //Also, check if the LIS value at j is less than i+1
                if(nums[i] < nums[j] && lis[j] < lis[i]+1)
                {
                    //update lis at j with list at i+1
                    lis[j] = lis[i]+1;
                    //check if max can be updated
                    if(lis[j] > max) 
                        max = lis[j];
                }                            
            }
        }
                        
        return max;
    }
};