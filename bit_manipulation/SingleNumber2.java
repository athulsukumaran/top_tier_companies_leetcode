/*
 * Question:
 * https://leetcode.com/problems/single-number-ii/
 */

/*
 * Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 * Example 1:
 * Input: nums = [2,2,3,2]
 * Output: 3
 *
 * Example 2:
 * Input: nums = [0,1,0,1,0,1,99]
 * Output: 99
 *
 * Solution Approach Used:
 * Method 1. 
 * Using 2 sets to store distinct numbers in set 1 and repeating numbers in set 2. This will work in generic case irrespective of number of times other numbers are repeated.
 *
 * Method 2.
 * This is an extension of single number problem.
 * We will use two variables ones and twos to keep track of occurrences of numbers in the array and start iterating from the start.
 * Idea is that if the number that is repeated once is stored in ones and if the number is repeated twice is stored in twos and if the number is repeated thrice is not at all stored in either ones or twos.
 * Return the ones variable at the end of loop
 * Time Complexity: O(n), Space Complexity: O(1) where n is length of array
 *
 * Check solution in https://www.youtube.com/watch?v=cOFAmaMBVps
 * Also check stack overflow link answers section for explanation of the logic.
 * https://stackoverflow.com/questions/21297067/single-number-ii-from-leetcode
 * 
 * Method 3
 * This is also using bit representations.
 * Idea is that the count of 1 bit at an index will be either 3x or 3x + 1 where (x = 0, 1,...).
 * This is because since all other numbers except the distinct number occurs thrice, the count of 1 bit at that index will be 3x if the distinct number doesnt have 1 at that index or 3x + 1 if the distinct number has 1 at that index 
 * We 
 */


class Solution {
    public int singleNumber(int[] nums) {      
//         // Method 1    
//      
//         // Declaring 2 sets 
//         Set<Integer> set1 = new HashSet<>();
//         Set<Integer> set2 = new HashSet<>();
//
//         // Iterating in the loop
//         for(int i = 0; i < nums.length; i++) {
//
//             // Checking if number is already present in set 2(repeating) and adding to set 1 and set 2 if its not present. In case number is in set 2, remove the element from set 1.     
//             if(! set2.contains(nums[i])) {
//                 set1.add(nums[i]);
//                 set2.add(nums[i]);
//             } else if(set1.contains(nums[i])) {
//                 set1.remove(nums[i]);  
//             }
//         }
//         
//         Return first(only) number in set 1(unique numbers)
//         Iterator setIterator = set1.iterator();
//         return (int) setIterator.next();
//---------------------------------------------------------------------------------------------------------------------------------------------------------
//      
//         // Method 2
//
//         // Initializing ones and twos with 0      
//         int ones = 0;
//         int twos = 0;
//
//         // Implementation of the logic      
//         for(int i = 0; i < nums.length; i++) {
//             ones = (ones ^ nums[i]) & (~ twos);
//             twos = (twos ^ nums[i]) & (~ ones);
//         }
//
//         Returning the result      
//         return ones;        
//------------------------------------------------------------------------------------------------------------------------------------------------------------
      
        // Method 3
      
        // Initializing an bit array of size 32 and result variable
        int[] resultBinaryArray = new int[32];
        int result = 0;
        
        // Iterating over the 32 bit indexes
        for(int i = 0; i < 32; i++) {
          
            // Initializing count of ones at the index to zero at the begining
            int countOnes = 0;
          
            // Iterating over all the numbers in nums array
            for(int j = 0; j < nums.length; j++) {
                
                // Fetching the bit at index i for nums[j]
                // Using right shift operator and getting the bit at the index i
                int bitAtIForNumsJ = (nums[j] >> 31 - i) & 1;
              
                // Updating the count of ones if the bit is 1 
                if(bitAtIForNumsJ == 1) {
                    countOnes++;
                }
            }
          
            // Storing the count % 3 at index i for result binary array
            resultBinaryArray[i] = countOnes % 3;
        }
        
        // At this point we have the result in binary array
      
        // Now converting binary array to result int value
        
        // Iterating over the 32 bits
        for(int i = 0; i < 32; i++) {
          
            // The bit at index i has to be multiplied with 2 power 31 - i (Atleast for positive numbers when converting from binary to int)
            // The corresponding bit operation to multiply with power of 2 is using left shift operator
          
            // The product of bit and power of 2 also has to be calculated and added for each index
            // Hence we use a bitwise OR          
            result = result | (resultBinaryArray[i] << 31 - i);
        }
        
        // Return result
        return result;
    }
}
