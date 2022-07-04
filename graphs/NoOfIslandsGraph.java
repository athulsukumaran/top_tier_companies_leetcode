/*
 * Question:
 * https://leetcode.com/problems/number-of-islands/
 */

/*
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 * Input: grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * Output: 1
 *
 * Example 2:
 * Input: grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * Output: 3
 *
 * Solution Approach Used:
 * The solution to the question is to count the number of disconnected graphs.
 *
 * In the grid, we have 1s(land) and 0s(water) and outside the grid is water.
 * The grid is not a adjacency matrix for the nodes, but a pictorial representation of land and water.
 * If value at i, j in grid is 1 and it has 1 in up, down, left or right direction, they are part of the same island.
 * Hence we can use bfs or dfs to traverse the graph formed by 1s in these directions
 * We can also use the same input grid and change the value at i, j to value other than 1 to mark the cell as already visited(I have used a # to mark the cell as visted)
 *
 * In the main function we will  use a counter to keep the count of islands and we will iterate over the grid starting at 0, 0.
 * Whenever we see a 1(land) which is not visited yet, we will update the counter and start a dfs from that cell to mark all the adjacent cells(in 4 directions) having 1 as visited and to make them as part of the same island.
 *
 * For this we will use a helper function to traverse the grapgh in dfs way
 * In the helper function we will accept params as grid, current cell row index, current cell column index, no of rows in grid, no of columns in grid
 * We will first update the cell value to # to mark it as visited
 * Then we will call the dfs recurssively for the four directions(up, down, left and right)
 * 
 * After recursively iterating the vertices in dfs way, we go back to the main function and check for the next unvisited node and do dfs for the graph starting with that cell and increment the count
 * After the loops in main method we would have covered all the nodes in the graph and also have the count of islands
 *
 * Time Complexity: O(n) worst case as we will only check a grid position twice, Space Complexity: O((m * n) * memory for each recurssive call) 
 * Not sure if grid is full of 0, the time complexity would be O(m * n). Have to confirm this
 *
 * Youtube Solution
 * https://www.youtube.com/watch?v=__98uL6wst8
 */

class Solution {
    // Helper DFS function, accepts the grid, the current cell row index, current cell col index, no of rows, no of cols
    private void dfs(char[][] grid, int i, int j, int m, int n) {
      
        // if the cell index is out of bound or if the cell value is not 1(either water(0) or is already visited(#)), then return from the function
        if(i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != '1') {
            return;
        }
        // Mark the cell as visited
        grid[i][j] = '#';
      
        // Traversing in all 4 directions
        dfs(grid, i - 1, j, m, n); // traversing in up direction in grid
        dfs(grid, i + 1, j, m, n); // traversing in down direction in grid
        dfs(grid, i, j - 1, m, n); // traversing in left direction in grid
        dfs(grid, i, j + 1, m, n); // traversing in right direction in grid
    }
    
    public int numIslands(char[][] grid) {
        // Initializing m and n for storing number of rows and columns in grid
        int m = grid.length;
        int n = grid[0].length;
        
        // Counter variable set to 0 initially
        int count = 0;
        
        // Iterating the grid starting at 0, 0 to m, n using 2 loops
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
              
                // If the cell value is 1(land) => not water(0) and not yet visited(#)
                // Start DFS from this cell and update the count
                if(grid[i][j] == '1') {
                  
                    // Starting a dfs traversal from this cell
                    dfs(grid, i, j, m, n);
                  
                    // Incrementing the count
                    count++;
                }
            }
        }
        
        // Return count from the main function
        return count;
    }
}
