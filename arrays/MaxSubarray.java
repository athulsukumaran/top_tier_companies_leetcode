/*
    https://leetcode.com/problems/maximum-subarray
*/

/*
    Approach
        -   We use Kadane's algorithm here
        -   Basically a sliding window algorithm.
        -   Updated the max till now with maximum sum at each index
        -   Also, take note, if the value at an index is negative and the sum ending there too is less than the sum till previous index, then there is no point considering the previous indices in the window. So, we set the new window starting from this index.
*/

/*
    Complexity
        -   Time = O(n)
        -   Space = O(1)
*/

class Solution {
    public int maxSubArray(int[] nums) {
        //set max till now to INT_MIN
        int maxTillNow=Integer.MIN_VALUE;
        //this is the variable we use to find the sum till the current index
        int maxEndingHere = 0;
        
        for(int num: nums)
        {
            maxEndingHere += num;

            //handling negative integer cases with this
            if(maxEndingHere < num)
                maxEndingHere = num;
                
            //update max till now if max ending here is greater than max till now                
            if(maxEndingHere > maxTillNow)
                maxTillNow = maxEndingHere;
            
        }
        return maxTillNow;
    }
}