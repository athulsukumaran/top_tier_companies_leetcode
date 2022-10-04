/*
 * Question:
 * https://leetcode.com/problems/coin-change
 */

/*
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Example 1:
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * 
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1
 *
 * Example 3:
 * Input: coins = [1], amount = 0
 * Output: 0
 *
 * Solution Approach Used:
 * This cannot be considered as a straight forward greedy problem as in greedy way, if we try to subtract the largest change we have from the amount and tries to make sum to amount, then there could be another combinations where the total number of coins could be fewer.
 *
 * The brute force way to graphically solve this problem is to draw graphs with edges for each change and at the end of edge, we calculate the amount(balance - change).
 * We can skip starting new edges, if the balance - change is negative
 * Calculate the number of edges in paths that make to zero and find the minimum length path and return
 * But this approach too solves many repeated paths, which can be optimized using dp array.
 *
 * Here we will try to solve the problem using dynamic programming in bottom up approach(i.e, not starting from amount and trying to make it zero, but in the dp array we
 *        calculate the minimum number of coins to make the amount value equal to index and store it at index i in dpArray)
 *
 * The base case is that for making amount zero, we need Zero coins.
 * 
 * We can define a dp array and initialize this as the base case. For all other index, lets first assume that the amount cannot be given with the coins and initialize them as the maximum number, i have used 10001 as the amount can be anthing in range 0 to 10000.
 * 
 * Now we can check for index 1 to amount in dp array, if the value at index i can be given using coin denominations we have
 * For this we loop throught the coins array in an inner loop
 * Suppose the amount at index(i) is greater than current coin denomination => amount at index - coin value >=0,
 *        Then amount at index i can be obtained by using this coin denomination(1) and number of coins required to make (amount at index i - coin value), i.e dpArray[i - coins[j]]
 *
 * At the end of iterating both the loops, we'll have the minimum number of coins required to make the amount, But if this is 10001(initial value in dpArray) => the coins cannot make the exact amount.
 *        => return -1 in that case
 *
 *
 * A curious doubt is that what if amount at index I - coin Value >= 0, but amount cannot be formed using the coin denominations, will the dp array value at index i get effected.
 * The answer is No. The dp array initial value for the index wont change in that case. This is because the dpArray[i - coins[j]] will still have value 10001.
 *        => We update dpArray[i] with minimum of dp[i] and 1 + dp[i - coins[j]], which will be dpArray[i] only
 * Try writing out dp array values for say an odd target amount 7 and coins with even values like 2, 4, 6 to get it clear
 * 
 * Time Complexity: O(amount * n), Space Complexity: O(amount).
 * As we have to iterate through the coins array amount times.
 * Also we are using dp array of size amount + 1
 *
 * Youtube link
 * https://www.youtube.com/watch?v=H9bfqozjoqs
 */

class Solution {
    public int coinChange(int[] coins, int amount) {
        
        // Declare dp array to store the minimum coins required to make amount at index i
        int[] minCoinsForSumDPArray = new int[amount + 1];
        
        // Initialize the base case, say to make amount 0, we need minimum 0 coins
        minCoinsForSumDPArray[0] = 0;
        
        // Lets first assume that no other amount can be made using the coin denominations
        // We can therefore initialize them with the maximum value i.e. 10001
        // Instead of 10001, we can also use Amount + 1
        // We cannot use constant Integer.MAX_VALUE, as when we add 1 to Integer.MAX_VALUE it becomes Integer.MIN_VALUE(32 bit data type size).
        // Thus calculating minimum fails if we use Integer.MAX_VALUE
        for(int i = 1; i < amount + 1; i++) {
            minCoinsForSumDPArray[i] = 10001;
        }
            
        // Iterating over the dp array and trying to find the minimum coins required to make the amount value as index i
        for(int i = 1; i < amount + 1; i++) {
            
            // amount value at index i in dpArray = i
            int amountAtIndexI = i;
            
            // Iterating over the coins array and checking across all denominations
            for(int j = 0; j < coins.length; j++) {
                
                // current coin value = coins[j]
                int coinValue = coins[j];
                
                // Calculating targetAmount(amountAtIndexI) - coinValue
                int amountAtIndexIMinusCoinValue = amountAtIndexI - coinValue;
                
                // Checking if the calculated value is greater than zero
                // If its negative, then we cannot use this coin denomination to get target amount value i. Hence we can skip it.
                if(amountAtIndexIMinusCoinValue >= 0) {
                  
                    // At this step, we check if the current minimum coins to make target amount as I is greater than the minimum coins required to make amount - coin value + 1
                    // 1 is for including the current coin denomination, dpArray[amount i - coin value] will have the pre calculated value for minimum coins to make balance amount (amount - coin value)
                  
                    minCoinsForSumDPArray[amountAtIndexI] = Math.min(minCoinsForSumDPArray[amountAtIndexI], 1 + minCoinsForSumDPArray[amountAtIndexIMinusCoinValue]);
                }            
            }
        }
        
        // Checking if coin denminations could be used to get exact target amount or not and returning result
        if(minCoinsForSumDPArray[amount] == 10001) {
            return -1;
        } else {
            return minCoinsForSumDPArray[amount];
        }
    }
}
