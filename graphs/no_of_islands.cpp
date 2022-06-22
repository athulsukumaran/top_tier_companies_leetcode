/*
    https://leetcode.com/problems/number-of-islands/submissions/
*/

/* Approach
    - Traverse through the matrix using nested loops.
    - We call dfs when we encounter a '1' while traversing the matrix.
    - This dfs function recursively makes sure all neighboring 1s are also visited.
    - We have marked visited cells as #.
    - Similar to number of provinces problem, we need to dfs a particular cell only if 
      it has value '1' and not '0' or '#'
*/

/* Complexity
    - Time = O(rc) , r= number of rows, c= number of columns
    - Space = O(1), since we don't have a visited vector here. We just mark it as '#'
                    once visited
*/

class Solution {
public:
    int numIslands(vector<vector<char>>& grid) {
        int count=0;
        
        for(int i=0; i<grid.size(); i++)
        {
            for(int j=0; j<grid[i].size(); j++)
            {
                //Call dfs and increment count only if we encounter '1'. i.e not water and not visited
                if(grid[i][j] == '1')
                {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    
    void dfs(vector<vector<char>>& grid, int i, int j)
    {
        //since we are recursively calling neighboring cells in line no 46-50,
        //we need to make sure we don't go out of bounds.
        //Also, we can skip water
        
        if(i <0 || j<0 || i>= grid.size() || j>= grid[i].size() || grid[i][j] != '1')
            return;
        
        //Mark as '#' once visited and recursively dfs neighboring cells
        grid[i][j] = '#';
        dfs(grid, i+1, j);
        dfs(grid, i-1, j);
        dfs(grid, i, j+1);
        dfs(grid, i, j-1);
    }
    
};