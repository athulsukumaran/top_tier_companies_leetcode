/*
  https://leetcode.com/problems/remove-element
*/

/*
    Approach
        -   Create a variable i that can be used to track where we need to insert the element in nums array while traversing the input array
        -   Return this same i at the end. It will have the required value of the array size under consideration after deleting every occurrence of the element we wanted to get removed.
        
*/

/*
    Complexity
        Time = O(n)
        Space = O(1)
*/

class Solution {
    public int removeElement(int[] nums, int val) {
        //i keeps track of the index at which we need to push num in nums array
        int i=0;
        for(int num: nums)
        {
            //push only if it is not equal to val
            if(num != val)
            {
                nums[i] = num;
                //increment i after pushing
                i++;
            }
        }
        //this i will have the size of the array we need to consider after updating the array
        return i;
    }
}