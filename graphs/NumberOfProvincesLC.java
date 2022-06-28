/*
 * Question:
 * https://leetcode.com/problems/number-of-provinces/
 */

/*
 * There are n cities. Some of them are connected, while some are not.
 * If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
 *
 * Return the total number of provinces.
 *
 * Example 1:
 * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * Output: 2
 *
 * Example 2:
 * Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * Output: 3
 *
 * Solution Approach Used:
 * The solution to the question is to count the number of disconnected graphs.
 *
 * The adjacency matrix will be a 2D array(List of Lists) input of size n * n where n is the number of vertices
 * Each entry in the matrix M[i][j] would be 1 or 0 in case of unweighted graphs(commonly) or with some weights in case of weighted graph
 * In case of undirected graphs the matrix would be symmetrical as if vertices i and j are connected and weight[i][j] would be same as weight[j][i]
 * In case of directed graphs weight[i][j] need not be same as weight[j][i] and one can be inifinty if the edge between them is directed towards one side only
 * 
 * The question here has unweighted undirected graph.
 *
 * In the main function we will use a boolean array to keep track of visited vertices and strat iterating for all the vertices in the adjacency matrix.
 * If the vertex i is not already visited, we will update the count of provinces and traverse all the connected vertices of i and update the vistied array for the connected vertices as well.
 * For this we will use a helper function to traverse the grapgh in dfs way
 * In the helper function we will accept params as adjacency matrix, number of vertices, the current vertex index from which we will start the dfs and visited array
 * We will first update the visited array for the current vertex and set it to true(to mark as visited)
 * The we iterate for the vertices and check in the adjacency matrix if Matrix[currentVertex][vertexI] is 1(if they are connected) and then perform the dfs starting from that vetex index recursively until all the connected vertices are visited)
 * 
 * After recursively iterating the vertices in dfs way, we go back to the main function and check for the next unvisited node and do dfs for the graph starting with that index and increment the count
 * After the loop in main method we would have covered all the nodes in the graph and also have the count of disconnected graphs
 *
 * Time Complexity: O(v ^ 2), Space Complexity: O(v) where v is the number of vertices
 *
 * The question is similar to number of provinces question in geeks for geeks, solution uploaded by athul. The input comes as a List of Lists in that.
 * graphs/number_of_provinces_adj_matrix.cpp
 *
 * Additional reference
 * https://leetcode.com/problems/number-of-provinces/discuss/1296723/simple-dfs-adjacency-matrix
 */

class Solution {
    public int findCircleNum(int[][] isConnected) {
        // n = number of vertices
        int n = isConnected.length;
        
        // Count variable to store result
        int count = 0;
        
        // boolean visited array to keep track of visited vertices
        boolean[] visited = new boolean[n];
        
        // Iterating over the vertices in main method
        for(int i = 0; i < n; i++) {
            
            // If the vertex at i is not already visited, increment the count of disconnected graphs
            // Also start a dfs traversal with vertex at i as the starting node
            if(! visited[i]) {
                dfs(isConnected, n, i, visited);
                count++;
            }
        }
        
        // Return the count variable
        return count;
    }
    
    public void dfs(int[][] isConnected, int length, int vertexIndex, boolean[] visited) {
        
        // Set the visited flag to true for the vertex for which we will start the dfs traversal
        visited[vertexIndex] = true;
         
        // Iterate over the vertices and find the connected vertices to the current vertex and perform dfs recursively for those vertices also if it is not already vistied
        for(int i = 0; i < length; i++) {
            
            // Check if vertex from dfs function call and vertex from for loop are connected
            if(isConnected[vertexIndex][i] == 1) {
                
                // Skip the vertex if it is already visited
                if(! visited[i]) {
                  
                    // Performing dfs recursively starting from the connected vertex if it is not already visited
                    dfs(isConnected, length, i, visited);
                }
            }
        }
    }
}
