/*
 * Question
 * https://leetcode.com/problems/reverse-linked-list-ii/description/
 */
 
/* 
 * Given the head of a singly linked list and two integers left and right where left <= right,
 * reverse the nodes of the list from position left to position right, and return the reversed list.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * 
 * Example 2:
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 * 
 * Constraints:
 * The number of nodes in the list is n.
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 *
 * Solution Approach Used:
 * This is a medium level question.
 * NB: Here the index position starts from 0 to list length - 1.
 * So the left in question would be index left - 1 and right in question would be index right - 1.
 *
 * If we picturize the list or draw and dry run it, it becomes easier.
 * We can split the question into 3 parts and add the corner case check
 *
 * We can use ListNodes curr, prev to store the current and previous nodes.
 * We can use ListNodes leftNode, prevOfLeftNode to store the node at left index and previous node of left.
 * We can use a temporary ListNode to store the next node of current node.
 *
 * In the first part, we can iterate from index i = 0 to left - 1 in a loop and update the prev and curr ListNodes respectively.
 * After the iteration, curr will point to the node at left - 1 index.
 * Store the curr and prev nodes in leftNode and prevOfLeftNode objects.
 *
 * In the second part, we can iterate from index i = left to right in a loop.
 * For this
 *        We'll use the temp object nextOfCurrentNode to point to the next of curr node.
 *        Then update the next pointer of curr to point to prev
 *        Update curr to nextOfCurrentNode(temp object) to continue with next node in original list in next iteration
 * 
 * After the iterations, curr will have node at right index and prev will have node at right - 1 index.
 * 
 * In the third part, we have to update the next pointers of leftNode and prevOfLeftNode.
 * For this
 *        We'll update the next of LeftNode to point to the node at right index(i.e. curr)
 *        We'll have to update the next of prevOfLeftNode to point to node at right - 1 index(i.e. prev)
 *
 * The corner case is if left is 1, then left - 1 would be 0 and leftNode will still be head and prevOfLeftNode is null.
 * In that case, we need to update the head to point to right - 1 index(i.e. prev)
 *
 * Time Complexity: O(n), Space Complexity: O(1).
 * As we have to iterate through all nodes once, time complexity is O(n).
 * As we use 5 ListNode variables, the space complexity is O(1)
 *
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        
        // ListNode Objects. Initially curr is set to head
        ListNode curr = head;
        ListNode prev = null;
        ListNode leftNode = null;      
        ListNode prevOfLeftNode = null;
        ListNode nextOfCurrNode = null;

        // index 
        int i = 0;
        
        // First part
        // Iterate from 0 to left - 1
        for(; i < left - 1; i++) {
            prev = curr;
            curr = curr.next;
        }

        // After reaching index left - 1
        // Store the node at left - 1 index and its previous node
        leftNode = curr;
        prevOfLeftNode = prev;

        // Second part
        // Iterate from left to right
        for(; i < right; i++) {
            // Assign next of curr node to temp obj
            nextOfCurrNode = curr.next;
            
            // Point the next of curr node to prev node
            curr.next = prev;
          
            // Update the prev node to point the curr node for next iteration
            prev = curr;
          
            // Update the curr node to point to the temp object(original curr.next) for next iteration
            curr = nextOfCurrNode;
        }

        // Third part
        // Assign the next pointer of leftNode to point to node at right index(i.e. curr)
        leftNode.next = curr;

        // Handling corner case, if left = 1
        if(prevOfLeftNode == null) {
            // If left = 1, we need to update head with the node at right - 1 index
            head = prev;
        } else {
          // Assign the next pointer of prevOfLeftNode to point to node at right - 1 index(i.e. prev)
            prevOfLeftNode.next = prev;
        }

        return head;
    }
}
