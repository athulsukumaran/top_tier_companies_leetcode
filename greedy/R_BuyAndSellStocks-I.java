/*
 * Question:
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock
 */

/*
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 * Example 1:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 *
 * Example 2:
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit = 0.
 *
 * Constraints:
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 104
 *
 * Solution Approach Used:
 * Approach 1 - Brute Force
 * The brute force way is to find the calculate profits for all possible pairs for start date and end date
 * This means, we'll have to calculate over (n - 1) * n / 2 cases. Hence Time complexity is O(n^2)
 * 
 * Approach 2 - Optimizing Time using Greedy Algorithm
 * We are not interested in the days on which the stock is bought or sell.
 * We need to find the maximum profit only. Only condition is that selling date should be after buying date.
 *
 * Lets assume that we buy stock on first day.
 * The max profit so far is 0 as we havent sold it yet at a higher price
 * The min stock price is the price on day 1 (index 0)
 *
 * Now if the stock price on a day is greater than that min price of stock so far, we have a candidate for max profit.
 * So we calculate the profit on selling on that day (prices[i] - min price) and update max profit if its greater than existing max profit value
 *
 * If the stock price on day is lesser than that of min price, we have new min price for stock
 *
 * After iterating over the loop, we get the maximum profit value 
 *
 * The base cases for solving are
 * maxProfit = 0
 * minStockPrice = prices[0]
 * 
 * Time Complexity: O(n), Space Complexity: O(1) where n is length of array
 */

class Solution {
    public int maxProfit(int[] prices) {
        // Base cases
        // The max profit so far is 0
        // The min stock price is the price at index 0 at this time
        int maxProfit = 0;
        int minStockPrice = prices[0];
        
        // Iterating for day 2 to n
        for(int i = 0; i < prices.length; i++) {
          
            // Suppose price at day i(prices[i]) is greater than min stock price => we can sell stock for profit
            // Hence we need to compare the new profit and existing value of max profit and update the value for max profit accordingly
            maxProfit = Math.max(maxProfit, prices[i] - minStockPrice);
          
            // Suppose the price at day i(prices[i]) is less than existing value of min stock price, we need to update the min stock price
            minStockPrice = Math.min(minStockPrice, prices[i]);
        }
        
      // Return max profit
        return maxProfit;
    }
}
