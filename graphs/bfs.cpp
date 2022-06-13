/*
    https://practice.geeksforgeeks.org/problems/bfs-traversal-of-graph/
*/

/* Approach
    - BFS uses a queue approach
    - We are given an adjacency list which looks something like
        0 -> 1 2 3
        1 -> 
        2 -> 4

        for the graph,

            0
           / \ \
          1  2  3
             |
             4

        The above looks like a tree, but note tree is also a graph

    - Inorder to prevent loops, we need to keep track of visited nodes.
    - Start from 0 (since this is the first element in adjacency list)
    - Add each adjacenct element to the back of the q and mark it as visited.
    - Keep popping from the front of the q till the q is empty. 

*/
 
/*
    Time complexity - O(E+V), E = No of edges, V = No of vertices
    Space Complexity - O(V), V= No of vertices
*/

class Solution {
  public:
    // Function to return Breadth First Traversal of given graph.
    vector<int> bfsOfGraph(int V, vector<int> adj[]) {
        
        queue<int> q;
        vector<int> res;
        bool visited[V]={false};
        
        q.push(0);
        visited[0]=true;
        
        /*print adjacency list
        
         for(int i=0; i<V; i++)
         {
             cout<<i<<"-> ";
             for(int j=0; j<adj[i].size(); j++)
             {
                 cout<<adj[i][j]<<" ";
             }
             cout<<endl;
         }
        */
        
        while(!q.empty())
        {
            int v = q.front();
            res.push_back(v);
            q.pop();
            
            for(int i=0; i<adj[v].size(); i++)
            {
                if(!visited[adj[v][i]])
                {
                    q.push(adj[v][i]);
                    visited[adj[v][i]] = true;
                }
            }    
        }
        return res;
    }
};