/*
    https://leetcode.com/problems/majority-element
*/

/*
    Approach
        -   The first approach that comes to our mind is tracking the count using a map.
        -   We will solve it here in an optimized manner to save space. (The follow up question)
        -   We will traverse the array till the end keeping track of the number that we expect is the answer till that point and also the count of occurence of this particular number.
        -   If the number we thought would be the answer changed in the next iteration, we decrement the count of this number, for example in [1,2,2,2,3], we would have considered result to be 2 (and count to be 3) till the index 3. But in 4th index, when we find 3, we decrement the count to 2.
        -   If we see the count reaches -1, we know the result we thought as the answer is not the actual one we are looking for. So, we change the result to the number at this position and set count to 1.
        -   Once we reach the end of the array, we will have the required result.
        -   Please refer to this beautiful explanation in case of any clarity
            https://www.youtube.com/watch?v=7pnhv842keE
*/

/*
    Complexity
    
        Time  = O(n)
        Space = O(1)
*/

class Solution {
public:
    int majorityElement(vector<int>& nums) {
        //track potential result in res
        int res= nums[0];
        //track count of this res here
        int count = 0;
        
        for(int i=0; i<nums.size(); i++)
        {
            //increment counter for each occurence of res, else decrement
            if(nums[i] == res)
                count++;
            else
                count--;
                
            //if count reaches -1, change the result to the current element and set count to 1
            if(count < 0)
            {
                res = nums[i];
                count = 1;
            }
        }
        
        return res;
    }
};