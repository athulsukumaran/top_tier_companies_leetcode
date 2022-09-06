/*
    https://leetcode.com/problems/product-of-array-except-self
*/

/*
    Approach
        -   We create a product array that we need to return as result.
        -   Each element in this array stores product till that index from left in the first iteration that starts from 0th index till size of array.
        -   In the second iteration, i.e from right hand side, the value at each index in the product array is multiplied with the product of all it's right elements as well.
            So, now we have the product of all elements except the element at that index at each corresponding index of the product array.
        -   Youtube link for more clarity
            https://www.youtube.com/watch?v=bNvIQI2wAjk
*/

/*
    Complexity
        -   Time = O(n)
        -   Space = O(n)
*/

class Solution {
    public int[] productExceptSelf(int[] nums) {
        //let's create the product array
        int n = nums.length;
        int[] product = new int[n];
        
        //store the product till the left of this index
        int prod = 1;
    
        for(int i=0; i<nums.length; i++)
        {
            product[i] = prod;
            prod *= nums[i];
        }
        
        //multiply with elements to the right of each element at each index
        prod=1;
        
        for(int i=nums.length-1; i>=0; i--)
        {
            product[i] *=  prod;
            prod *= nums[i];
        }
        
        return product;
    }
}