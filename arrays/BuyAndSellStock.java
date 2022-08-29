/*
    https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
*/

/*
    Approach
        -   We will use sliding window technique here
        -   Use a left pointer and right pointer in this approach.
        -   Start by setting left as the 1st index of the array and right 
            as the immediate next element.
        -   We advance the right pointer to find the element with the biggest diff with left pointer
        -   If we find right pointer is less than left, we set right pointer as new left
            (since the diff with the greatest element on right will always be greater with the element at right pointer in this case)

*/

/*
    Complexity
        -   Time = O(n)
        -   Space = O(1)
*/

    
class Solution {
    public int maxProfit(int[] prices) {
        //this is the left pointer
        int min = prices[0];
        
        //if there is only one lement in the array, we can't buy and sell stocks, so return 0
        if(prices.length == 1)
            return 0;
        
        //this variable keeps track of max difference till this point
        int maxDiff = prices[1] - prices[0];
        
        //traverse from the immediate next element
        for(int i=1; i<prices.length; i++)
        {
            int diff = prices[i] - min;
            
            //update maxDiff if the new diff is greater
            if(diff > maxDiff)
                maxDiff = diff;
            //set current right pointer as left if the value at right pointer is less than that at left
            if(prices[i] < min)
                min = prices[i];              
        }
        
        //If the maxDiff was lesser, it means there was no profitable way to buy and sell stock
        if(maxDiff < 0)
            return 0;
        
        return maxDiff;
    }
}