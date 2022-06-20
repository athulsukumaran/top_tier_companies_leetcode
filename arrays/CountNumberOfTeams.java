/*
 * Question:
 * https://leetcode.com/problems/count-number-of-teams/
 */

/*
 * There are n soldiers standing in a line. Each soldier is assigned a unique rating value.
 *
 * You have to form a team of 3 soldiers amongst them under the following rules:
 *
 * Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
 * A team is valid if: (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
 * Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).
 * 
 * Example 1:
 * Input: rating = [2,5,3,4,1]
 * Output: 3
 * Explanation: We can form three teams given the conditions. (2,3,4), (5,4,1), (5,3,1). 
 *
 * Example 2:
 * Input: rating = [2,1,3]
 * Output: 0
 * Explanation: We can't form any team given the conditions.
 *
 * Example 3:
 * Input: rating = [1,2,3,4]
 * Output: 4
 *
 * Solution Approach Used:
 * The brute force approach is to use nested loops for index i = 0 to n, j = i + 1 to n and k = j + 1 to n (n = length of input array) and check the conditions and increment the count. 
 * This will need O(n ^ 3) time to execute.
 * But if we consider the elements except the ones at starting (index 0) and ending (index n - 1) to be the middle element in either increasing order or reverse order, It becomes easier
 * Suppose there are X elements to the left of index i which is less than element at index i and Y elements to right of index i which is greater than element at i, Then we can form X * Y teams with element at index i at middle in increasing order 
 * Similarly we can calculate count of teams with element at index i at middle position in decreasing order.
 * Add the calculated counts to the result and return after iterating for all possible values for middle element.
 *
 * Time Complexity: O(n ^ 2), Space Complexity: O(1) where n is length of input array
 *
 * Check solution in https://www.youtube.com/watch?v=ZgzqQFd8EC4
 */

class Solution {
    public int numTeams(int[] rating) {
      
        // Initializing count and length variables
        int count = 0;
        int n = rating.length;
        
        // Brute force approach 
        // for(int i = 0; i < n; i++) {
        //     for(int j = i + 1; j < n; j++) {
        //         for(int k = j + 1; k < n; k++) {
        //             if(rating[i] > rating[j] && rating[j] > rating[k]) {
        //                 count++;
        //             }
        //             if(rating[i] < rating[j] && rating[j] < rating[k]) {
        //                 count++;
        //             }
        //         }
        //     }
        // } 
      
        // Iterating i from index 1 to n - 2 (Possible values of middle elements in teams)  
        for(int i = 1; i < n - 1; i++) {
          
            // Initializing count of elements less and greater than element at i towards both left and right side of i
            int countLeftValsLessThanI = 0;
            int countLeftValsGreaterThanI = 0;
            int countRightValsLessThanI = 0;
            int countRightValsGreaterThanI = 0;
            
            // Checks elements from j = 0 to i (left side of index i) 
            for(int j = 0; j < i; j++) {
              
                // Increment count of elements which are less than element at i in left side If rating[j] < rating[i]
                // Else increment count of elements which are greater than element at i in left side
              
                if(rating[j] < rating[i]) {
                    countLeftValsLessThanI++;
                } else {
                    countLeftValsGreaterThanI++;
                }
            }
            
            // Checks elements from k = j + 1 to n (right side of index i)
            for(int k = i + 1; k < n; k++) {
              
                // Increment count of elements which are greater than element at i in right side If rating[i] < rating[k]
                // Else increment count of elements which are less than element at i in right side
              
                if(rating[i] < rating[k]) {
                    countRightValsGreaterThanI++;
                } else {
                    countRightValsLessThanI++;
                }
            }            
            
            // Add the possible combinations of teams with element at i in the middle position to the result count
            count = count + (countLeftValsLessThanI * countRightValsGreaterThanI) + (countLeftValsGreaterThanI * countRightValsLessThanI);
        }
        
        // Return count
        return count;
    }
}
