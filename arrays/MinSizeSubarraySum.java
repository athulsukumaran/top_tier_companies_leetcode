/*
    https://leetcode.com/problems/minimum-size-subarray-sum
*/

/*
    Approach
        -   Will use sliding window algorithm here
        -   Will increment right pointer till we find an sub array with sum greater than
            or equal to the target
        -   Once we find the subarray, we increment left pointer till the reduced subarray
            sum is also greater than or equal to the target.
        -   We keep track of the minimum value in each iteration
*/
/*
    Complexity
        -  Time = O(n) - left and right pointer access the same index max twice
        -  Space = O(1)  
*/    

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        //this keeps track of sum of subarray at any point
        int sum = 0;
        //this keeps track of min sub array value that we got till that point
        int ans = Integer.MAX_VALUE;
        //left pointer
        int l=0;
        //iterate through the array using right pointer
        for(int r=0; r<nums.length; r++)
        {
            sum += nums[r];
            //till sum >= target, we need to shrink the window from left
            while(sum >= target)
            {
                //keep track of min window till now
                ans = Math.min(ans, r-l+1);
                sum -= nums[l];
                l++;
            }
        }
        
        return  ans == Integer.MAX_VALUE? 0:ans;
    }
}