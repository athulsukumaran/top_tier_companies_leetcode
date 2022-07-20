/*
    https://leetcode.com/problems/coin-change
*/

/*
    Approach
        -   Create a DP array starting from index 0 to 'amount'
        -   Initialise 0th index value to 0 and rest everything to amount+1
        -   In a loop, iterate through each and every index and populate i+coin[j] th
            index of array as the min of current val at that index of array
            and value at i+1 ( 1 since we need the total count as output. We increment
            everytime the coin[j] satisifies the requirement)
        -   Youtube - https://www.youtube.com/watch?v=oBt53YbR9Kk&t=14329s
            You can find tabulation methods for similar questions in the above video
            (Last section)
*/

/*
    Complexity
        -   Time = O(mn), m= amount, n= number of coins
        -   Space = O(m), m = amount
*/

class Solution {
public:
    int coinChange(vector<int>& coins, int amount) {
        //Handle base case
        if(amount == 0)
            return 0;
        
        //the DP array must start from 0 till amount and also
        //initialise this to amount+1
        vector<int> sum(amount+1, amount+1);
        
        //first element is set to 0
        sum[0] = 0;
        
        //iterating thru each amount ( from 0 till amount)
        for(int i=0; i<=amount; i++)
        {
            //iterate thu each coin
            for(int j=0; j<coins.size(); j++)
            {
                //value of this particular coin
                int coinVal = coins[j];
                
                //this is the main logic. Compare the value of ith index +1 
                //and that of the value present at index i+ value of that particular coin
                // and update the value at index i+value of that particular coin
                if((float)i+coinVal <= amount)
                {
                    if(sum[i+coinVal] > sum[i]+1)
                        sum[i+coinVal] = sum[i]+1;   
                }
            }
        }
        
        //if the amount was not updated from the initial amount+1 value,
        //it means its not possible to get this sum from these set of coins.
        //So return 1
        return (sum[amount]>amount)?-1:sum[amount];
    }
};