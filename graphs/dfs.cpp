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
        //we use this stack to traverse the graph
        stack<int> s;
        bool visited[V] = {false};

        //result vector
        vector<int> res;
        
        //push the first element
        s.push(0);
        visited[0] = true;
         
        while(!s.empty())
        {
            //get the element on top, pop it, add it to the result and find its neighbors in the next for loop
            int v = s.top();
            res.push_back(v);
            s.pop();
            
            // traverse through all neighbors of v in the adjacency list
            for(int i=0; i<adj[v].size(); i++)
            {
                //make sure the vertex is not traversed more than once
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
