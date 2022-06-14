/*
    https://practice.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/
*/

/* Approach
    - DFS uses a stack based approach.
    - Very similar to BFS except we use stack instead of q here.
    - Same youtube link
        - https://www.youtube.com/watch?v=tWVWeAqZ0WU&t=2530s
*/

/*
    Time complexity - O(E+V), E = No of edges, V = No of vertices
    Space Complexity - O(V), V= No of vertices
*/

class Solution {
  public:
    // Function to return a list containing the DFS traversal of the graph.
    vector<int> dfsOfGraph(int V, vector<int> adj[]) {
        // Code here
        stack<int> s;
        bool visited[V] = {false};
        vector<int> res;
        
        s.push(0);
        visited[0] = true;
         
        while(!s.empty())
        {
            int v = s.top();
            res.push_back(v);
            s.pop();
            
            for(int i=0; i<adj[v].size(); i++)
            {
                if(!visited[adj[v][i]])
                {
                    s.push(adj[v][i]);
                    visited[adj[v][i]] = true; 
                }
            }   
        }
        
        return res;
    }
};
