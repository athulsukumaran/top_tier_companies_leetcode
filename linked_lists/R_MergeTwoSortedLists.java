
/*
 * Question
 * https://leetcode.com/problems/merge-two-sorted-lists/description/
 */

/* 
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.
 * Return the head of the merged linked list.
 *
 * Example 1: 
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * 
 * Example 2:
 * Input: list1 = [], list2 = []
 * Output: []
 *
 * Example 3:
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 *
 * Solution Approach Used:
 * If we picturize the list or draw and dry run it, it becomes easier.
 *
 * We can use ListNodes curr, prev to store the previous and current nodes.
 * We can use ListNode result to store the starting node of result.
 *
 * The idea is to iterate till both the lists are null. For this we can use the while loop with break condition list1 not null OR list2 not null
 * Inside the loop, there can be 3 possibilities and we can set the curr node based on these cases
 *        1. If Both the lists are not null:
 *                  In this case, we need to compare list1 val and list2 val and store the least in them to curr. Also update the corresponding list to its next position.
 * 
 *        2. Else List 1 is not null:
 *                  In this case, list 2 is null, so set curr node to list 1 and update the list 1 to its next position
 *
 *        3. Else List 2 is not null:
 *                  In this case, list 1 is null, so set curr node to list 2 and update the list 2 to its next position
 *
 * Before going to next iteration in while loop
 *        1. Check if prev node is not null and set next of prev node to curr node
 *        2. Assign prev node with value curr node
 *        3. Check if result node is assigned, if not assign result with value curr node
 *
 * After the while loop is complete, we'll have the merged list in result.
 *
 * Time Complexity: O(m + n), Space Complexity: O(1).
 * As we have to iterate through all nodes in both the lists once, the time complexity is O(m + n).
 * As we use ListNode variables to store the curr, prev and result nodes, the space complexity is O(1)
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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        
        ListNode result = null;
        ListNode curr = null;
        ListNode prev = null;

        while(list1 != null || list2 != null) {
            if(list1 != null && list2 != null) {
                if(list1.val < list2.val) {
                    curr = list1;
                    list1 = list1.next;
                } else {
                    curr = list2;
                    list2 = list2.next;
                }
            } else if(list1 != null) {
                curr = list1;
                list1 = list1.next;
            } else {
                curr = list2;
                list2 = list2.next;
            }
            if(prev != null) {
                prev.next = curr;
            }
            prev = curr;

            if(result == null) {
                result = curr;
            }
        }

        return result;
    }
}
