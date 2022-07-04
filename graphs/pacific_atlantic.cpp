/*
    https://leetcode.com/problems/pacific-atlantic-water-flow/
*/

/*
    Approach
        -   We have a matrix here. We dfs through it similar to an adjacency matrix.
        -   We create two vectors
            1. Pacific (to store all nodes in the matrix reachable from the Pacific ocean)
            2. Atlantic (to store all nodes in the matrix reachable from the Atlanctic ocean)
        -   In DFS we check for the condition that the neighboring node has a value greater than the current node. This is because water flows from nodes in the matrix to the ocean. But we are DFSin from the ocean to the nodes.
        -   The intersection of Pacific and Atlantic nodes give us the desired results as these nodes are reachable from both the oceans.
        -   Youtube link
            https://www.youtube.com/watch?v=s-VkcjHqkGI
*/


/*
    Complexity
        -   Space = O(mn) - Visited matrix = pacific + atlantic
        -   Time = O(mn) - We traverse the whole matrix once
*/
        

class Solution {
public:
    vector<vector<int>> pacificAtlantic(vector<vector<int>>& heights) {
        vector<vector<int>> res;
        //Get the row size
        int m = heights.size();
        //Get the col size
        int n = heights[0].size();
        
        if(m==0 || n==0)
            return res;
        
        //create 2 different visited matrices for pacific and atlantic
        vector<vector<bool>> pacific(m, vector<bool>(n));
        vector<vector<bool>> atlantic(m, vector<bool>(n));
        
        //traverse thru row and call dfs for pacific - starting from col 0
        //                                   atlantic - starting from col n-1
        
        for(int i=0; i<m; i++)
        {
            dfs(heights, pacific, i, 0);
            dfs(heights, atlantic, i, n-1);
        }
  
        //traverse thru column and call dfs for pacific - starting from row 0
        //                                   atlantic - starting from row m-1
        
        for(int j=0; j<n; j++)
        {
            dfs(heights, pacific, 0, j);
            dfs(heights, atlantic, m-1, j);
        }
        
        for(int i=0; i<m; i++)
        {
            for(int j=0; j<n; j++)
            {
                //check for nodes in pacific and atlantic that are both traversed and add it to result
                if(pacific[i][j] && atlantic[i][j])
                    res.push_back(vector<int>{i, j});
            }
        }
        
        return res;
    }
    
    void dfs(vector<vector<int>>& heights, vector<vector<bool>>& visited, int i, int j)
    {
        
        if(visited[i][j] == true)
            return;
        
        int m = heights.size();
        int n = heights[0].size();
        
        //set visited to true
        visited[i][j] = true;
        
        //check values of i and j are not overflowing and recursively call neighboring nodes
        //if the value of neighboring node is greater than current node
        if(i-1 >= 0 && heights[i-1][j] >= heights[i][j])
            dfs(heights, visited, i-1, j);
        if(i+1 < m && heights[i+1][j] >= heights[i][j])
            dfs(heights, visited, i+1, j);
        if(j-1 >= 0 && heights[i][j-1] >= heights[i][j])
            dfs(heights, visited, i, j-1);
        if(j+1 < n && heights[i][j+1] >= heights[i][j])
            dfs(heights, visited, i, j+1);
        
    }
};