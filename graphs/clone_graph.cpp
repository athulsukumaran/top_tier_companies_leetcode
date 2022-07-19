/*
    https://leetcode.com/problems/clone-graph
*/

/*
    Approach
        DFS
            -   Create a hashmap that maps old node to new node
            -   This map does function something similar to visited vector that we generally
                use to track if a node is visited or not.
            -   I have provided with code the reason why using a visited vector won't fly here.
            -   Perform DFS and recursively populate neighbors of each node.
        
        BFS
            -   We follow BFS procedure itself. Only that we consider the map here.
*/

/*
    Complexity
        DFS - Space = O(V) - For the hashmap
              Time = O(V+E)
        BFS - Space = O(V) - Hashmap and queue
              Time = O(V+E)
*/

/*
// Definition for a Node.
class Node {
public:
    int val;
    vector<Node*> neighbors;
    Node() {
        val = 0;
        neighbors = vector<Node*>();
    }
    Node(int _val) {
        val = _val;
        neighbors = vector<Node*>();
    }
    Node(int _val, vector<Node*> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};
*/


// Working DFS Soultion

class Solution {
public:
    unordered_map<Node*, Node*> mp;
    
    Node* cloneGraph(Node* node) {
        
        //For input []
        if(node == NULL)
            return NULL;
        
        //Not present in the map. If present in the map, it is already traversed
        if(mp.find(node) == mp.end())
        {
            //Create a new Node and map it to the old node
            mp[node] = new Node(node->val);
            
            //traverse thru the neighbors recursively
            for(vector<Node*>::iterator it = node->neighbors.begin(); it!= node->neighbors.end(); it++)
            {
                mp[node]->neighbors.push_back(cloneGraph(*it));
            }
        }
        
        //If already present, return reference to the new node
        return mp[node];
    }
};


/*
    DFS Solution - with vector (doesn't work)
    I was curious behind the intuition to use maps and not a visited vector. I have
    commented the line that will cause the issue. This line will return the memory
    location of the old node since we add old nodes to visited array.
*/

class Solution {
public:
   
    vector<Node*> visited;
    
    Node* cloneGraph(Node* node) {
        
        //For input []
        if(node == NULL)
            return NULL;
        
        Node *newNode = NULL;
        vector<Node *>::iterator visitedIt = find(visited.begin(), visited.end(), node);
        
        //Not present in the vector. If present in the vector, it is already traversed
        if(visitedIt == visited.end())
        {
            //Create a new Node and add it to visited vector
            visited.push_back(node);
            newNode = new Node(node->val);
            
            //traverse thru the neighbors recursively
            for(vector<Node*>::iterator it = node->neighbors.begin(); it!= node->neighbors.end(); it++)
            {
                newNode->neighbors.push_back(cloneGraph(*it));
            }
        }
        else
        {
            //this line is the culprit. newNode now stores the memory of old node. There
            //is no way to track the corresponding new node of this old node. Hence we need 
            //a map. Phew! Found it finally!
            
            newNode = *visitedIt;
        }
        
        //return reference to the new node
        return newNode;
    }
};

/*
    BFS Solution  
*/

class Solution {
public:
    unordered_map<Node *, Node *> mp;
    
    Node* cloneGraph(Node* node) {
        if(node == NULL)
            return NULL;
        
        //Similar to BFS, creating a q and pushing first node
        queue<Node *>q;
        q.push(node);
        
        //add the first node to map
        mp[node] = new Node(node->val);
        
        while(!q.empty())
        {
            //Again, following general BFS procedure here
            Node *ele = q.front();
            q.pop();
            
            for(vector<Node *>::iterator it = ele->neighbors.begin(); it != ele->neighbors.end(); it++)
            {
                //If not found in map, we need to traverse it and also create an entry in the map
                if(mp.find(*it) == mp.end())
                {
                    mp[*it] = new Node((*it)->val);
                    q.push(*it);   
                }
                
                // add to the neighbor list of the main node that was dequeued
                mp[ele] -> neighbors.push_back(mp[*it]);
            }
        }
        
        return mp[node]; 
    }
};