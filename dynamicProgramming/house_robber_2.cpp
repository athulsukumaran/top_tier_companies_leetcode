/*
    https://leetcode.com/problems/house-robber-ii
*/

/*
    Approach
        -   This is a follow up of house robber problem.
        -   The constraint is the very first index and the very last index are 
            neighbors
        -   We can't solve it just by adding an if condition to check whether
            its the last index, then skip or anything like that, because this 
            is a dynamic programming solution. The max we have at the last index is the
            cumulative max starting from 0th index.
        -   So, we need to break this down into 2 house robber problems. 
            1.  From 0 till n-2
            2.  From 1 till n-1
        -   The required soultion will be the max of these 2.
*/

/*
    Complexity
        -   Time = O(n)
        -   Space = O(1)
*/


class Solution {
public:
    int rob(vector<int>& nums) {
        int n= nums.size();  
        //Handle base cases
        if(n == 0)
            return 0;
        else if(n == 1)
            return nums[0];
        
        //Max money we can rob is the max of 2 house robber
        //sub problems
        return max(maxMoney(0, n-2, nums), maxMoney(1, n-1, nums));
    }
    
    int maxMoney(int l, int r, vector<int>& nums)
    {
        //following is the exact same logic used in house robber
        //problem
        
        int max1=0;
        int max2=0;
        
        for(int i=l; i<=r; i++)
        {
            int tmp=max1;
            max1=max(max1, max2+nums[i]);
            max2=tmp;
        }
        return max1;
    }
};