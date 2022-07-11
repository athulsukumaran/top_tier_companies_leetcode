/*
    https://leetcode.com/problems/course-schedule-ii
*/

/*
    Approach
        -   Create an adjacency list from the prerequisite pair vector.
        -   DFS traverse and check for cycles. If cycle is not encountered, add
            parent node to the stack. This is basically topological sorting (dependent nodes             will be shown only after showing the dependee unlike DFS).
        -   Pop stack one by one and add it to the resultant vector.
*/

/*
    Complexity
        - Space = O(V+E) (Adjacency list DS)
        - Time = O(V+E)
*/

class Solution {
    /*Following colors to identify
        WHITE - init state of a course node. This means the node is not yet traversed.
        GRAY - the node is being traversed recursively from another node.
        BLACK - all child nodes of a particular node has been added (In this
                case, dependent course). 
    */    
    const int WHITE=1;
    const int GRAY=2;
    const int BLACK=3;
    
    /*since we are performing topological sorting (dependent nodes will be shown only
      after showing the dependee unlike DFS), we need to add BLACK nodes to stack*/
    stack<int> s;
    
    // A map that tracks the color of each node
    unordered_map<int, int> color;
    
    //There is a possibility if cycle in this graph. We need this to track that case
    bool isPossible=true;
    
public:
    vector<int> findOrder(int numCourses, vector<vector<int>>& prerequisites) {
        
        //we need to create an adjacency list first
        vector<int> adj[numCourses];
        //we need to pop from strack and add to this result vector at the very end
        vector<int> res;
        
        //initialise all nodes to WHITE since they are not yet traversed
        for (int i = 0; i < numCourses; i++) {
            color.insert({i, WHITE});
        }
        
        //construct adjacency list
        for(vector<vector<int>>::iterator it=prerequisites.begin(); it!= prerequisites.end(); it++)
        {
            /* note that the key of adjacency list is the second value in prerequisite
               map since the input is like that. Please refer to the question again in
               case of any doubts */
            
            int first = it->at(1);
            int second = it->at(0);

            adj[first].push_back(second);
        }
        
        /* perform dfs for each of the keys in adjacency list provided they are 
           not traversed yet(Marked by WHITE) */
        
        for(int i=0; i<numCourses; i++) 
        {
            if(color[i] == WHITE)
            {
                dfs(i, adj);
            }
        }
        
        //We detected cycle in the graph. Hence there is no order of completion of courses.
        if(!isPossible)
        {
            return vector<int>();
        }
        
        //Pop stack items one by one and add it to the resultant aray
        while(!s.empty())
        {
            res.push_back(s.top());
            s.pop();
        }
        
        return res;
    }  
    
    void dfs(int v,  vector<int> adj[]) {
        //while the node and its child nodes are getting DFSed, mark it as gray
        color[v] = GRAY;
        
        //iterate through adjacent nodes of vertices
        for(vector<int>::iterator i= adj[v].begin(); i!= adj[v].end(); i++)
        {
            //if WHITE, it is not traversed yer, so recursively DFS for its child nodes
            if(color.at(*i) == WHITE)
            {
                dfs(*i, adj);
            }
            //this means in this traversal (some node in the recursive tree), this node was
            //hit. So there is a cycle. Note we only consider GRAY and not BLACK
            else if(color.at(*i) == GRAY)
            {
                isPossible=false;
            }
        }
        
        //add parent node to the stack and mark it as BLACK.
        color[v] = BLACK;
        s.push(v);
    }
};