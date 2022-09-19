/*
 * Question:
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal
 */

/*
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
 * (i.e., from left to right, then right to left for the next level and alternate between).
 *
 *               3
 *             /   \
 *            9     20
 *                /    \
 *               15     7
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[20,9],[15,7]]
 *
 * Example 2:
 * Input: root = [1]
 * Output: [[1]]
 * 
 * Example 3:
 * Input: root = []
 * Output: []
 *
 * Solution Approach Used:
 * Though the questions seems tricky, the solution is a normal level order traversal with 2 queues along with an extra condition.
 * In a normal level order traversal, we are used to printing values from left to right always.
 * From the question and examples, we can see that the outer list is a list of nodes at same levels.
 * Only in the inner list, i.e. nodes at a particular level, we have to reverse the order if they are at level 1, 3, 5 etc (odd levels)
 * So we will use the level order traversaral to traverse the levels in the tree (BFS).
 * For each level we start pushing the left child of the node and then the right as usual.
 * When polling the nodes at a level, push them to an inner arraylist, which we will then add it to our result(list of list)
 * While adding the inner arraylist to our result(list of list), we can add the condition to reverse the inner arraylist if they are at odd levels
 * Time Complexity: O(n), Space Complexity: O(n) for the queues and list
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    
    // BFS ZigZag helper method
    public List<List<Integer>> bfsZigZag(TreeNode root) {
        
        // Primary Queue queue1
        Queue<TreeNode> queue1 = new LinkedList<>();
      
        // List of List result
        List<List<Integer>> result = new ArrayList<>();
        
        // If root is null return the empty list of list
        if(root == null) {
            return result;
        }
        
        queue1.add(root);
        
        // Level variable to store the current level
        int level = 0;
        
        while(! queue1.isEmpty()) {
            
            // Secondry queue queue2
            Queue<TreeNode> queue2 = new LinkedList<>();
          
            // Arraylist to store node vals in a particular level
            List<Integer> levelNodes = new ArrayList<>();
            
          
            // Normal BFS inner loop to poll the first element from queue1 and add the left and right sub tree nodes if they are not null
            while(! queue1.isEmpty()) {
                
                TreeNode currNode = queue1.poll();
              
                // Add the node value to the inner arraylist
                levelNodes.add(currNode.val);

                if(currNode.left != null) {
                    queue2.add(currNode.left);
                }
                if(currNode.right != null) {
                    queue2.add(currNode.right);
                }
            }
            
            // If the current level is odd, then we reverse the node vals in inner arraylist
            if(level % 2 == 1) {
                Collections.reverse(levelNodes);
            }
            
            // After finishing a level adding inner arraylist to the result 
            result.add(levelNodes);
            
            // Reassigning queue1 with the queue2 for traversing next level
            queue1 = queue2;
            
            // Incrementing level
            level++;
        }
        
      
        // Return the result
        return result;
    }
    
    // Main method
    // Calls the BFS ZigZag helper method
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        return bfsZigZag(root);
    }
}
