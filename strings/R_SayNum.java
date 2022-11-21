/*
 * Question:
 * https://leetcode.com/problems/count-and-say
 */

/*
 * CountAndSay(n) = say the number of repetition for each digit in countAndSay(n - 1)
 * Base case CountAndSay(1) = "1"
 *
 * Input: n = 4
 * Output: "1211"
 * Explanation:
 * countAndSay(1) = "1"
 * countAndSay(2) = say "1" = one 1 = "11"
 * countAndSay(3) = say "11" = two 1's = "21"
 * countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"
 *
 * Solution Approach Used:
 * We will use a helper method sayNum which accepts the num string and say the number of repetition for each digits in out put format.
 * In the helper method, we will keep a stringbuilder to append the results, a variable for prev char and count of curr char.
 * Iterate over the numstring from starting and increment the count if curr char = prev char.
 * If the curr char != prev char, append the count of prev char followed by prev char and reset count of curr char to 1.
 * After loop, append the count followed by prev char (this is done to add the count of last occurring digit). 
 * 
 * In the main method, make it recurssive, provide the base case for n = 1, else invoke sayNum helper for n - 1 
 *
 * Have to check time and space complexities for this
 */

class Solution {
    private String sayNum(String numString) {
        char prev = '-';
        int count = 0;
        StringBuilder sayNum = new StringBuilder();
        for(int i = 0; i < numString.length(); i++) {
            char curr = numString.charAt(i);
            if(curr == prev) {
                count++;
            } else {
                if(prev != '-') {
                    sayNum.append(count);
                    sayNum.append(prev);
                }
                count = 1;
            }
            prev = curr;
        }
        if(prev != '-') {
            sayNum.append(count);
            sayNum.append(prev);            
        }
        return sayNum.toString();
    }
    
    public String countAndSay(int n) {
        if(n == 1) {
            return "1";
        } else {
            return sayNum(countAndSay(n - 1));
        }
    }
}
