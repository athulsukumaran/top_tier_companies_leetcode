/*
 * Question:
 * https://leetcode.com/problems/zigzag-conversion
 */

/*
 * Sample Input "PAYPALISHIRING", num of rows = 3
 *
 * row1: P     I    N
 * row2: A   L S  I G
 * row3: Y A   H R
 * row4: P     I
 *
 * Sample Output 
 * PINALSIGYAHRPI
 *
 * Solution Approach Used:
 * We will store the characters against each row and finally append the characters in each rows in ascending order and return the result.
 * To determine to which row a char goes to, we can make use of the helper function getRowIndex which accepts the num of rows and char index in string as its parameter.
 * Writing out the first few indexes to determine the formula
 * char Index | row Index | char Index SImplified (% 6)
 *          0 |         0 |                          0
 *          1 |         1 |                          1
 *          2 |         2 |                          2
 *          3 |         3 |                          3
 *          4 |         2 |                          4 
 *          5 |         1 |                          5
 *          6 |         0 |                          0
 *          7 |         1 |                          1
 *          8 |         2 |                          2 
 *          9 |         3 |                          3
 *         10 |         2 |                          4
 *         11 |         1 |                          5
 *         12 |         0 |                          0
 *
 *
 * We can see that after num of Rows + (num of Rows - 2), i.e. 2*(numRows - 1), the row index pattern repeats.
 * The char index can be simplified to char index  % (2*(numRows - 1))
 * If char index simplified is less than num of rows then row index = char index
 * Else row index = 2*(numRows - 1) - char index simplified
 *
 * In case if the number of rows is 1, then rowIndex is always 0 and output string is same as input string
 * 
 * Time Complexity of this approach: O(n), Space Complexity of this approach O(n)
 */

class Solution {
    private int getRowIndex(int numRows, int charIndex) {
        if(numRows == 1) {
            return 0;
        } else {
            charIndex = charIndex % (2*(numRows - 1));
            if(charIndex < numRows) {
                return charIndex;
            } else {
                return 2*(numRows - 1) - charIndex;
            }
        }
    } 
    
    public String convert(String s, int numRows) {
        List<StringBuilder> rowStrings = new ArrayList<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int rowIndex = getRowIndex(numRows, i);
            if(rowIndex < rowStrings.size() && rowStrings.get(rowIndex) != null) {
                rowStrings.set(rowIndex, ((rowStrings.get(rowIndex)).append(c)));
            } else {
                StringBuilder rowString = new StringBuilder();
                rowString.append(c);
                rowStrings.add(rowIndex, rowString);
            }
        }
        StringBuilder result = new StringBuilder();
        for(StringBuilder rowString : rowStrings) {
            result.append(rowString);
        }
        return result.toString();
    }
}
