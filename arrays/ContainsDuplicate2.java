/*
    https://leetcode.com/problems/contains-duplicate-ii/
*/

/*
    Approach
        -   This is a sliding window based problem.
        -   The window is increased till the size goes beyond k
        -   Once the window size has crossed k, we have to increment the left pointer.
            Here it means simply removing that particular element from the set.
        -   At the same time we have to add the element at the right pointer to the set. 
            If it already exists in the set, it means we have an element repeated in the
            window of size k. We can safely return true in this case.
            Else, we add it to the set.
*/

/*
    Complexity
        -   Time = O(n)
        -   Space = O(k)
*/

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        //Set that stores all elements within the window
        HashSet<Integer> s = new HashSet();
        
        //right pointer of the window is i
        for(int i=0; i<nums.length; i++)
        {
            //if the right pointer index value crossed k, we need to reduce the window size from left. So we remove that element from the HashSet
            if(i > k)
                s.remove(nums[i-k-1]);
            //If the number is already present in the set, it means in the window under consideration we already have that number
            if(!s.add(nums[i]))
                return true;
        }
        
        return false;
    }
}