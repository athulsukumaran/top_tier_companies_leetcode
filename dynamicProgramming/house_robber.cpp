/*
    https://leetcode.com/problems/house-robber
*/

/*
    Approach
        -   We need to find the max money made at each index of the nums array
        -   For this we can use 2 variables for max money made till i-1
            and max money made till i-2
        -   Max money made at i will be the max of (value at i+ max till i-2
            and max till i-1) as we can't rob two consecutive houses.
*/


/*
    Complexity
        -   Time = O(n)
        -   Space = O(1)
*/


class Solution {
public:
    int rob(vector<int>& nums) {
        //If empty then we can make 0 money
        if(nums.size() == 0)
            return 0;
        
        //Variables to track max till i-1 and i-2 respectively
        int max1 = 0;
        int max2 = 0;
        
        //Iterate thru each index and update max1 and max2
        for(int i=0; i<nums.size(); i++)
        {
            int tmp=max1;
            max1 = max(nums[i]+ max2, max1);
            max2 = tmp;
        }
    
        //max1 will have the maximum money that can be made
        return max1;
    }
};