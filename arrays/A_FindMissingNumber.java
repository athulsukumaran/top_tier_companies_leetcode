/*
   https://leetcode.com/problems/missing-number/
*/

/*
    Approach 1 - Binary Search
        -   Binary search is followed here.
        -   We will sort the array and follow binary search after that.
        -   Left will be incremented whenever the mid value is same as that of the mid index.
            Else right will be set as mid
        -   We stop iteration once left pointer is the same as that of right
        -   The index at left(same as that of right) will be the answer.
*/

/*
    Complexity
        -   Time = O(nlogn)
        -   Space = O(1)
*/

class Solution {
    public int missingNumber(int[] nums) {
        //sort the array
        Arrays.sort(nums);
        //left pointer at 0 and right pointer at last index
        int left=0, right=nums.length;
        
        //continue the loop till left is less than right
        while(left != right)
        {
            //calculate mid
            int mid = left+(right-left)/2;
            
            //if the value at mid th index is greater than mid th index,
            //it means the missing number is between left and the value at mid th
            
            if(nums[mid] > mid)
                //search till mid in the coming iterations as the value can be somewhere b/w left and mid
                right = mid;
            else
                //else search from mid+1 till right
                left = mid+1;
        }
        
        //return left or right as both will be pointing to the same location
        return left;
    }
}


/*
    Approach 2 - Calculating the sum
        -   Sum of first n natural numbers = n*(n+1)/2 is used to get the expected sum
            if the missing number was present.
        -   Now we iterate through the array and subtract value at each index
        -   We are left with the missing value
*/

/*
    Complexity
        -   Time = O(n)
        -   Space = O(1)
*/


class Solution {
    public int missingNumber(int[] nums) {
        int size = nums.length;
        int sum  = size*(size+1)/2;
        for(int num: nums)
        {
            sum -= num;
        }
        return sum;
    }
}


/*
    Approach 3 - XOR
        -   If we XOR a number by itself, the result is 0. If we XOR this with another number, the result is that number.
        -   So we XOR the index and value at index. 
        -   The value of XOR at the end is the number we are looking for
*/

/*
    Complexity
        -   Space = O(1)
        -   Time = O(n)
*/

class Solution {
    public int missingNumber(int[] nums) {
        int xor = 0;
        int size = nums.length;
        
        for(int i=0; i<size; i++)
        {
            xor ^= i ^ nums[i];
        }
        
        //we need to xor the last number as well whch happens to be the size
        //for ex: if we consider [3,0,1], the sie is 3, which too should be considered while XORing
        return xor ^ size;
    }
}

