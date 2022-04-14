/* Approach
    - Start from 0th position of each linked list. 
    - Find the smallest among these (Say you found it out in linked list 2).
    - Add it to the resultant linked list. Advance pointer to next node in 
      linked list 2. Continue till all lists are empty 
*/

/* Complexity analyis

    - O(kN) time complexity - k is the number of linked list
    - O(N) space complexity 
    
*/

class Solution {
public:
    ListNode* mergeKLists(vector<ListNode*>& lists) {
        int min_index = 0;
        ListNode* head = new ListNode(0);
        ListNode* h = head;
        while (true) {
            bool isBreak = true; 
            int min = INT_MAX;
            //finding min element in all lists
            for (int i = 0; i < lists.size(); i++) {
                if (lists[i] != NULL) { 
                    if (lists[i]->val < min) {
                        min_index = i;
                        min = lists[i]->val;
                    } 
                    //this will be false till at least one list is non empty
                    isBreak = false;
                }

            }
            //all lists are empty, we can exit from the outer loop
            if (isBreak) {
                break;
            } 
            //create a new node with min element and point it accordingly
            ListNode *a = new ListNode(lists[min_index]->val);
            h->next = a;
            h = h->next; 
            
            //advance list where min node was found to next
            lists[min_index] = lists[min_index]->next;
        }
        
        return head->next;
    }
};