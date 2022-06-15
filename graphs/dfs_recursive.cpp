/* DFS of a graph - recursive
    https://practice.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph
*/

/* Approach
    - Recursion can be used for DFS because DFS follows a stack based approach
    - We need to loop through each vertex and then perform recursive operation on 
      each, since there can be vertices that are not reachable from the starting point, say 0.
    - DFSUtil takes care of recursion. You can  compare it to stack approach of
      DFS and see the similarity in code.
*/

/* Complexity
    - Space = O(v) + stack complexity due to recursion (O(v)) ~= O(v)
        *Please correct me if I am wrong
    - Time = O(V+E)
*/

class Solution {
  public:
  //global vector to take care of visited nodes
  vector<bool> visited = {false}; 
    vector<int> dfsOfGraph(int V, vector<int> adj[]) {
        //result vector
        vector<int> res;
         
        //traverse through each vertex and call dfsUtil 
        for(int i=0; i<V; i++)
        {
            if(!visited[i])
            {
                dfsUtil(i, adj, res);
            }
        }
        return res;
    }
    
    void dfsUtil(int v, vector<int> adj[], vector<int> &res)
    {
        //set viited to true and push it to result vector that is passed by reference
        visited[v] = true;
        res.push_back(v);
        for(int i=0; i<adj[v].size(); i++)
        {
            if(!visited[adj[v][i]])
                dfsUtil(adj[v][i], adj, res);
        }
    }
};