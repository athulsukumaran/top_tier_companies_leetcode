/* 
    https://practice.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/
*/

/*
    Approach
    -   Approach is similar to DFS except, we now keep track of the parent.
    -   If we find an adjacent node for a vertex, that is already visited, then
        - it can be the immediate parent node. This adjacency is expected since this
          is an undirected graph
        - it can be some other node and not the parent. It means we have a loop.
*/


/*
    Complexity
    -   Time = O(V+E)
    -   Space = O(V)
*/

/*
    Note: https://practice.geeksforgeeks.org/problems/prerequisite-tasks
    This problem too can be solved by detecting cyle in the graph.
    Its just that you need to create adjacency list yourself first
*/

class Solution {
  public:
    // Function to detect cycle in an undirected graph.
    bool isCycle(int V, vector<int> adj[]) {
        bool visited[V] = {false};
        for(int i=0; i<V; i++)
        {
            //dfs only if the vertex is not yet visited
            if(!visited[i])
                return dfs(i, adj, visited, -1);
        }
        return false;
    }
    
    bool dfs(int i, vector<int> adj[], bool visited[], int parent)
    {
        //set vertex visite to true and traverse the adjacent vertices below
        visited[i] = true;
        for(vector<int>::iterator it= adj[i].begin(); it != adj[i].end(); it++)
        {
            //if the adjacent node is not visited, call recursively so that its adjacent nodes 
            //can be traversed
            if(!visited[*it])
                return dfs(*it, adj, visited, i);
            //else if it is visited and it is not the parent node of i, return true as we
            //detected a loop here
            else if(*it != parent)
                return true;
        }
        //no loops encountered. return false.
        return false;
    }
};