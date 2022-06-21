/*
    https://practice.geeksforgeeks.org/problems/number-of-provinces/1
*/

/* Approach
    - The question is basically to find the number of disconnected graphs. 
    - NOTE: We are given adjacency matrix as input and not an adjacency list
    - We iterate through the vertices and perform a DFS ( and also update the count of graphs)
      only when the vertex is unvisited. This means, the vertex was not part of any graph that
      was traversed till now.
*/

/* Complexity
    - Time = O(V+E)
    - Space = O(V)
*/

class Solution {
  public:
  vector<int> visited;
  
    // this is the main function
    int numProvinces(vector<vector<int>> adj, int V) {
        int count=0;
        
        //iterate through each vertex
        for(int i=0; i<V; i++)
        {
            //if the vertex was not already traversed as part of previous dfs, only then
            //perform a dfs and also increment the count since we are sure there is another
            //disconnected graph
            if(find(visited.begin(), visited.end(), i) == visited.end())
            {
                dfs(adj, i);
                count++;
            }
        }
        
        return count;
    }
    
    void dfs(vector<vector<int>> adj, int i)
    {
        /* The following check is not required as i is never repeated - its passed 
           as a parameter from the calling function */
        
        // if(find(visited.begin(), visited.end(), i ) == visited.end())
        // {
        //     visited.push_back(i);
        // }
        
        //this is how dfs is done using adjacency matrix
        for(int j=0; j<adj[i].size(); j++)
        {
            if(adj[i][j] == 1 && (find(visited.begin(), visited.end(), j ) == visited.end()))
            {
                visited.push_back(j);
                dfs(adj, j);
            }
        }
    }
};
