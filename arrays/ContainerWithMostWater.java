/*
    https://leetcode.com/problems/container-with-most-water
*/

/*
    Approach
        -   We will follow a 2 pointer approach here 
            -   a left pointer starting from 0th index
            -   a right pointer starting from size-1 th index
        -   Total water contained will be the product of minimum of value at left pointer
            and right pointer and the difference between the two pointer locations
        -   We increment left pointer or decrement right pointer depending on the value at which index is smaller
        -   We keep track of the max water contained till now and return it
*/

/*
    Complexity
        -   Time = O(n)
        -   Space = O(1)
*/

class Solution {
    public int maxArea(int[] height) {
        //left and right pointers
        int l=0, r=height.length-1;
        //this keeps track of the max water contained till now
        int max=0;
        
        //we need to iterate only till left pointer is less than right. Even left = right doesn't make sense.
        while(l<r)
        {
            //calculating and updating max water contained till now
            max = Math.max(max, Math.min(height[l], height[r]) * (r-l));
            
            //if at l we have shorter height value, we increment it so that there is a chance of a longer height value somewhere to the right
            if(height[l] < height[r])
                l++;
            //if at r we have shorter height value, we decrement it so that there is a chance of a longer height value somewhere to the left
            else
                r--;
        }
        
        return max;
    }
}