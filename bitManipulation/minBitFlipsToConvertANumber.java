/*
 * Question:
 * https://leetcode.com/problems/minimum-bit-flips-to-convert-number
 */

/*
 * Solution Approach Used:
 * Min Flips to convert a number A to number B is the count of positions at which bits in the numbers are different.
 * Say if we want to convert 10 (binary rep 1010) to 7 (binary rep 0111), number of flips required are 3 at positions 1, 2 and 4 from left.
 * Doing XOR of 1010 (10) and 0111 (7) gives 1101, which has 1 at positions where the bits are different and 0 at positions where bits are same in original numbers.me bits.
 * Time Complexity of this approach: Constant time complexity as we need to loop the xor in binary format ==> max = number of bits for int in prog language used, Space Complexity: Constant space complexity to store xor and count etc.
 */

class Solution {
    public int minBitFlips(int start, int goal) {
        int xor = start ^ goal;
        int count = 0;
      
        // Basic way to count the number of ones is to convert xor to binary string and checking bits at each position.
        // String binaryString = Integer.toBinaryString(xor);
        // for(int i = 0; i < binaryString.length(); i++) {
        //     if(binaryString.charAt(i) == '1') {
        //         count++;
        //     }
        // }
        
        // bit manipulation way
        while(xor > 0) {
          
            // bitwise & of any number and 1 will give the last bit in the number.
            // this is because bitwise & for all bits except last bit will be zero as binary of 1 is 00...01
            int lastChar = xor & 1;
          
            if(lastChar == 1) {
                count++;
            }
          
            // for positive numbers, right shifting by 1, removes the last bit from it and add 0 to the start.
            // 1101 (13) >> 1 = 0110 (6)
            xor = xor >> 1;
        }
      
        return count;
    }
}
