/*
 * Question:
 * https://leetcode.com/problems/binary-tree-right-side-view/
 */

/*
 * Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 *               1
 *             /   \
 *            2     3
 *              \     \
 *               5     4
 *
 * Example 1:
 * Input: root = [1,2,3,null,5,null,4]
 * Output: [1,3,4]
 *
 * Solution Approach Used:
 * We will use the level order traversaral to traverse the levels in the tree (BFS).
 * For each level we start pushing the left child of the node and then the right. (This can be reversed to get the left view of the tree).
 * After completion of each level, that is if the queue size == 0, after polling, then add the node val to the result list
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
    public List<Integer> rightSideView(TreeNode root) {
      
        // Result list declared
        List<Integer> rightMostNodes = new ArrayList<>();
      
        if(root != null) {
          
            // Queue declared and initialized for binary tree level order traversal
            Queue<TreeNode> queue1 = new LinkedList<>();
            queue1.add(root);
          
            // Iterating till all levels are completed
            while(queue1.size() != 0) {
              
                // Secondry queue decalred for storing the child nodes of existing values in queue 1
                Queue<TreeNode> queue2 = new LinkedList<>();
              
                // While queue1 is not empty, poll the elements and then check if their left and right child nodes are null and add them to secondry queue queue2
                while(queue1.size() != 0) {
                    TreeNode nodeFromQueue1 = queue1.poll();
                    if(nodeFromQueue1.left != null) {
                        queue2.add(nodeFromQueue1.left);
                    }
                    if(nodeFromQueue1.right != null) {
                        queue2.add(nodeFromQueue1.right);
                    }
                  
                    // If the current polled node is the last node in queue1, then insert node val in the result list
                    if(queue1.size() == 0) {
                        rightMostNodes.add(nodeFromQueue1.val);
                    }
                }
              
                // Assign queue 1 with values in queue 2 to continue traversing next level
                queue1 = queue2;
            }
        }
      
        // Return result list
        return rightMostNodes;
    }
}
