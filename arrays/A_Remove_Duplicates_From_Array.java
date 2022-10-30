/*
    https://leetcode.com/problems/remove-duplicates-from-sorted-array/
*/

/*
    Approach
        -   Since we are updating the same array, we will return a variable i till which index the result needs to be considered (as elements to the right of i might contain some values)  
        - We create a variable i that keeps track of which index to update in the next iteration.
        - For the 0th index, we need to push the value at 0th index in the array.
        - For other indices, we check if that number is greater than the value at i-1 th index. In that case, we replace ith position with this number and increment i
*/

/*
    Complexity
        -   Time = O(n)
        -   Space = O(1)
*/
          

class Solution {
    public int removeDuplicates(int[] nums) {
        //this variable keeps track of where to insert the next element
        int i = 0;
        
        for (int n : nums)
        {
            //while traversing the array, check if the value at this index is greater than the previous element in the array and update the value at index at i and increment i.
            //We need to do the same operation for 0th index as an exception
            if (i == 0 || n > nums[i-1])
            {
                nums[i] = n;
                i++;
            }
            
            //If you wish to see how the array looks like uncomment below
            /*
            System.out.println("updated nums iteration = "+i);
        
            for(int m:nums)
            {
                System.out.print(m+",");
            }
            */
        }
        return i;
    }
}