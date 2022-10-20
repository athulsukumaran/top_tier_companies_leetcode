/*
 * Question:
 * https://leetcode.com/problems/minimum-path-sum
 */

/*
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time.
 * 
 * 1    3   1
 * 1    5   1
 * 4    2   1
 *
 * Example 1:
 * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 * Output: 7
 * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 *
 * Example 2:
 * Input: grid = [[1,2,3],[4,5,6]]
 * Output: 12
 *
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 *
 * Solution Approach Used:
 * This can be considered as a straight forward shortest distance graph problem and since we have only non negative values it is easy to use dijkstras algorithm
 *
 * Here we will try to use dynamic programming approach to calculate the shortest distances from top left index(0, 0) to each index.
 * 
 * Its easy if we picturize this as a graph and we are allowed to move only in right and down direction from each point
 * Then the idea would be that, if there is a corresponding lef or top cell
 *          => The shortest distance to reach cell at the current index would be =>
 *                                            Value at the current cell (grid[i][j]) + 
 *                                            Minimum of shortest distance to reach its left and top cell Math.min(shortestDist[i][j - 1], shortestDist[i - 1][j])
 *
 * This breaks down our problem to an easy 2D dynamic problem.
 * We now need to only account for border cases like if the cell doesnt have either left or top cells or even both
 *
 * So for top left cell, there exists no top or left cell, hence the shortest dist to reach here would the value at cell itself.(This is also our starting point)
 * 
 * If its the first row cell(i = 0), then the shortest distance to reach it from top would be infinity(Integer.MAX_VALUE)
 * Similarly, if its the first column cell(j = 0), then the shortest distance to reach it from left would be inifinity(Integer.MAX_VALUE)
 *
 * After iterating the loop, we will have the shortest distance to reach the bottom right index at dp Arrays [rows - 1][columns - 1] index.
 *
 * Time Complexity: O(m * n), Space Complexity: O(m * n).
 * As we have to iterate through all the rows and columns
 * Also we are using dp array of grids size (mn)
 *
 */

class Solution {
    public int minPathSum(int[][] grid) {
      
        // Get the number of rows and columns in grid
        int gridRows = grid.length;
        int gridColumns = grid[0].length;
      
        // Create a shortest distance 2D array, which will have the shortest distance to that index if we are coming from either left or top
        // This is because, in the problem statement, we are given that we will be starting from index 0, 0
        // And we are allowed to move one step either right or down to reach bottom right index (rows - 1, columns - 1) 
        int[][] shortestDistArray = new int[gridRows][gridColumns];
      
        // Iterate the grid from top left index (0, 0) to bottom right and calculate shortest distance to reach each index from top left
        for(int i = 0; i < gridRows; i++) {
            for(int j = 0; j < gridColumns; j++) {
              
                // Initialize the shortest distance to reach left cell and top cell as Integer MAX Values 
                // This is because for i == 0 or j == 0, there would not be any corresponding top or left cells
                int leftMinDist = Integer.MAX_VALUE; int topMinDist = Integer.MAX_VALUE;
              
                // If i >= 1, then they will have a corresponding top cell.
                // Hence we have to consider the shortest distance to reach the top cell also
                if(i >= 1) {
                    topMinDist = shortestDistArray[i - 1][j];
                }  
              
                // Similarly if j >= 1, then they will have a corresponding left cell.
                // Hence we have to consider the shortest distance to reach the left cell also
                if(j >= 1) {
                    leftMinDist = shortestDistArray[i][j - 1];
                }
              
                // If i and j are 0, they will not have any left or top cell
                // Hence the shortest distance to reach index i, j will be grid[0][0] value only
                if(i == 0 && j == 0) {
                    shortestDistArray[i][j] = grid[i][j];
                } else {
                  
                    // Else, there can be either a corresponding left cell or top cell or even both for cell at this index
                  
                    // Hence shortest distance to cell at i, j will be sum of value at this cell + Minimum of shortest distance to reach the top cell or left cell
                    shortestDistArray[i][j] = grid[i][j] + Math.min(leftMinDist, topMinDist);
                }
            }
        }
      
        // Return the shortest distance to bottom right index (rows - 1, columns - 1)
        return shortestDistArray[gridRows - 1][gridColumns - 1];
    }
}
