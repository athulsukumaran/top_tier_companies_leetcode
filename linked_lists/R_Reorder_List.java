/*
 * Question
 * https://leetcode.com/problems/reorder-list/description/
 */

/* 
 * You are given the head of a singly linked-list. The list can be represented as:
 * L0 → L1 → … → Ln - 1 → Ln
 * 
 * Reorder the list to be on the following form:
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 *
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 *
 * Example 1:
 * Input: head = [1,2,3,4]
 * Output: [1,4,2,3]
 *
 * Example 2:
 * Input: head = [1,2,3,4,5]
 * Output: [1,5,2,4,3]
 *
 * Constraints:
 * The number of nodes in the list is in the range [1, 5 * 104].
 * 1 <= Node.val <= 1000
 *
 * Solution Approach Used:
 * This is a medium level question.
 *
 * If we picturize the list or draw and dry run it, it becomes easier.
 * We can split the question into 3 parts and add the corner case check
 *
 * We can use ListNodes leftList, rightList to store the left and right halves of the list respectively.
 * We can use ListNodes node to iterate over the list
 * We can use a temporary ListNodes nextOfLeftList and nextOfRightList to store the next of left node and right node while merging back
 *
 * We need a helper function to reverse a list as well
 *
 * First lets calculate the total length of the list.
 *
 * Now lets divide the question in three parts as earlier
 *
 * In the first part, assign leftList and rightList to head first and then we can iterate from index i = 0 to (length + 1) / 2. (I have added length + 1 so that, in case the length is odd, the left list will have one node more than the right list)
 *        In each iteration we'll assign node to rightList and rightList to next of right list.
 *
 * After the iterations, node will point to the last node in left half and rightList will point to the starting node in right half.
 * At this point we can set the next of node to null so that list ends here.
 *
 * In the second part, we can reassign rightList to the reverse of rightList.
 * This can be done using the reverse list helper function
 *
 * In the third part, we have to iterate over the left list and reversed right list and reassign the pointers such that
 *                                                                                                  next of left node points to right node
 *                                                                                                  next of right node points to original next of left node
 *
 * For this
 *        We'll iterate the left list and right list using while loop until both the lists are not null
 *        In each iteration we can use the temp ListNodes nextOfLeftList and nextOfRightList to store the original next of left and right lists.
 *        Update the next pointers of right list and left list accordingly
 *        Reassign leftList with nextOfLeftList and rightList with nextOfRightList for the next iteration
 *
 * After the while loop is complete, we'll have the reordered list.
 * There is no corner case as the left list will have length >= right list, rightList nodes point to the next left list node respectively.
 *
 * Time Complexity: O(n), Space Complexity: O(1).
 * As we have to iterate through all nodes lineraly multiple times, time complexity is O(n).
 * As we use ListNode variables to store the temp values, the space complexity is O(1)
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
    // Reverse list helper method
    private ListNode reverseList(ListNode node) {
        ListNode prev = null;
        while(node != null) {
            ListNode nextOfNode = node.next;
            node.next = prev;
            prev = node;
            node = nextOfNode;
        }
        return prev;
    }

    public void reorderList(ListNode head) {
      
        // Length variable
        int length = 0;
        
        // ListNode node to point to head and iterate using it
        ListNode node = head;

        // Calculate length of the input list
        while(node != null) {
            length++;
            node = node.next;
        }

        // If length is less than or equal to 2, no need to reorder the list
        if(length > 2) {
          
            // ListNode variables to store left and right halves of the input list
            ListNode leftList, rightList;

            // Initally leftList and rightList is assigned with node at head
            leftList = head;
            rightList = head;

            // Iterate till mid point
            // After iteration, node will then point to last node in leftList and rightList points the right half of the list 
            for(int i = 0; i < (length + 1) / 2; i++) {
                node = rightList;
                rightList = rightList.next;
            }
            
            // Assign next of node to null as left list ends here
            node.next = null;

            // Reverse the right list
            rightList = reverseList(rightList);

            // Merge the left list and reversed list
            // Iterate using while loop until both left list and right list is not null
            while(leftList != null && rightList != null) {
                // Store original next of leftlist and right lists in the temp ListNodes
                ListNode nextOfLeftListNode = leftList.next;
                ListNode nextOfRightListNode = rightList.next;
                
                // Assigning the pointers to reorder
                rightList.next = nextOfLeftListNode;
                leftList.next = rightList;
              
                // Assigning leftList and rightList to move pointers to original next of respective lists
                rightList = nextOfRightListNode;
                leftList = nextOfLeftListNode;
            }

        }
    }
}
