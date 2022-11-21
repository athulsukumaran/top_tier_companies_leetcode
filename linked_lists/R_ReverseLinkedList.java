/*
 * Question
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * 
 * Example 2:
 * Input: head = [1,2]
 * Output: [2,1]
 *
 * Example 3:
 * Input: head = []
 * Output: []
 *
 * Constraints:
 * The number of nodes in the list is the range [0, 5000].
 * -5000 <= Node.val <= 5000
 *
 *
 * Solution Approach Used:
 * This is a common straight forward LinkedList question.
 * We need 2 ListNode variables to store the current and previous ListNodes with initial values null.
 *
 * The idea is we need to iterate over the nodes in the linked list by changing head to head.next in a loop
 * In each iteration, assign curr to a new ListNode with val = head.val and next pointer to prev ListNode
 * After this step, assign prev to curr(so in next iteration, this curr ListNode would be treated as the prev)
 *
 * After the loop is complete, the curr will have the val of last node in the input LinkedList and it will be pointing to the sec last node in input LinkedList.
 * Similarly each node in input list will now be pointing to the previous node in the input LinkedList
 * And we have the reversed LinkedList
 * Assign head with curr and return head
 *
 * Time Complexity: O(n), Space Complexity: O(1).
 * As we have to iterate through all nodes once, time complexity is O(n).
 * As we use 2 ListNode variables, the space complexity is O(n)
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
    public ListNode reverseList(ListNode head) {
        ListNode curr = null, prev = null;
        
        while(head != null) {
            curr = new ListNode(head.val);
            curr.next = prev;
            prev = curr;
            head = head.next;
        }

        head = curr;

        return head;
    }
}
