/*
 * Question:
 * https://leetcode.com/problems/minimum-flips-to-make-a-or-b-equal-to-c
 */

/*
 * Solution Approach Used:
 * We have to find the bitwise OR of A and B and compare the bits to that in C at the corresponding positions.
 * We know 1 | 1 = 1, 1 | 0 = 1, 0 | 1 = 1, 0 | 0 = 0.
 
 * At a given position in binary representations, if (bit in A | bit in B) is not equal to bit in C, then we have 2 cases => bit at that position in C is either 1 or 0.
 * If the bit at that position in C is 1, we require one flip in A | B to make it equal as changing any one bit in A or B results in 1.
 * If the bit at that position in C is 0, we require two flips if both A and B are 1 or one flip if bit at that position is 0 in either A or B.
 
 * Eg A = 2(0010), B = 6(0110) and C = 5(0101).
 * No. of flips required = 2 in A to make (0001) and 1 in B to make (0100) so that A | B = 0101 = C.
 * Total No. of flips required = 3.
 
 * Time Complexity of this approach: Constant time complexity as we need to loop the xor in binary format ==> max = number of bits for int in prog language used, Space Complexity: Constant space complexity to store count etc.
 */

class Solution {
    public int minFlips(int a, int b, int c) {
      
        int count = 0;
        
        // Loop to iterate till a, b and c are all 0
        while(a > 0 || b > 0 || c > 0) {
          
            // Extracting lastbit in A, B and C
            int lastCharA = a & 1;
            int lastCharB = b & 1;
            int lastCharC = c & 1;
          
            // Comparing last bit in A | B) to that in C
            if((lastCharA | lastCharB) != lastCharC) {
                if(lastCharC == 1) {
                    count++;
                } else {
                    if((lastCharA ^ lastCharB) == 1) {
                        count++;
                    } else {
                        count+=2;
                    }
                }
            }
            a = a >> 1;
            b = b >> 1;
            c = c >> 1;
        }
        return count;
    }
}
