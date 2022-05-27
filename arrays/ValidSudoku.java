/*
 * Question:
 * https://leetcode.com/problems/valid-sudoku/
 */

/*
 * Input: board = 
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * 
 * Output: true
 *
 * Solution Approach Used:
 * We will need to validate the count of numbers in each of rows, columns and inner boxes.
 * We will use a helper methods to validate each rows, columns and inner boxes.
 * In the main method, wel'll return the AND of all the 3 helper validations (returns true if all 3 validations succeeds, else false).
 * In each helper validation methods, we'll make use of an array of size 9 and store the count of number at index (number - 1). This is because numbers range from 1 to 9 and indexes start from 0.
 * If number already exists in the count array for validating row/column/inner box, the current count will be 1 and validation helper method returns false. 
 * 
 * Time Complexity: O(n ^ 2), Space Complexity: O(n) where n is 9 in case of the sudokku
 */


class Solution {
    private boolean isValidRows(char[][] board) {

        // This method checks if each row is valid
        // In the loops the validation would be done in the order of outer loop as
        // 1st Iteration checks row index 0 for column indexes 0 to 8
        // 2nd Iteration checks row index 1 for column indexes 0 to 8
        // 3rd Iteration checks row index 2 for column indexes 0 to 8
        // 4th Iteration checks row index 3 for column indexes 0 to 8
        // 5th Iteration checks row index 4 for column indexes 0 to 8
        // 6th Iteration checks row index 5 for column indexes 0 to 8
        // 7th Iteration checks row index 6 for column indexes 0 to 8
        // 8th Iteration checks row index 7 for column indexes 0 to 8
        // 9th Iteration checks row index 8 for column indexes 0 to 8 
      
        // Define a count array to store count of each numbers
        int[] count = new int[9];
          
        // Start iterating from row index 0 to 8
        for(int i = 0; i < 9; i++) {
          
            // Initialize count to 0 for each numbers in the count array
            Arrays.fill(count, 0);
          
            // Start iterating from column indexes 0 to 8
            for(int j = 0; j < 9; j++) {
              
                // If the char at position i, j is empty('.'), then skip and continue with next iteration in inner loop
                if(board[i][j] <= '0' || board[i][j] > '9') {
                    continue;
                }
              
                // If the count of number at (i, j) i.e, value at index (number - 1) in count array is 1, the number is already present in row and hence the row is not valid, return false
                if(count[board[i][j] - '0' - 1] == 1) {
                    return false;
                }
              
                // Increment the count of number at (i, j) and store it at index (number - 1) in count array
                count[board[i][j] - '0' - 1]++;
            }
        }
        return true;
    }

    private boolean isValidColumns(char[][] board) {
      
        // This method checks if each row is valid
        // In the loops the validation would be done in the order of outer loop as
        // 1st Iteration checks column index 0 for row indexes 0 to 8
        // 2nd Iteration checks column index 1 for row indexes 0 to 8
        // 3rd Iteration checks column index 2 for row indexes 0 to 8
        // 4th Iteration checks column index 3 for row indexes 0 to 8
        // 5th Iteration checks column index 4 for row indexes 0 to 8
        // 6th Iteration checks column index 5 for row indexes 0 to 8
        // 7th Iteration checks column index 6 for row indexes 0 to 8
        // 8th Iteration checks column index 7 for row indexes 0 to 8
        // 9th Iteration checks column index 8 for row indexes 0 to 8 
      
        // Define a count array to store count of each numbers      
        int[] count = new int[9];
      
        // Start iterating from column index 0 to 8
        for(int i = 0; i < 9; i++) {
          
            // Initialize count to 0 for each numbers in the count array
            Arrays.fill(count, 0);
          
            // Start iterating from row indexes 0 to 8
            for(int j = 0; j < 9; j++) {
              
                // If the char at position j, i is empty('.'), then skip and continue with next iteration in inner loop
                if(board[j][i] <= '0' || board[j][i] > '9') {
                    continue;
                }                
              
                // If the count of number at (j, i) i.e, value at index (number - 1) in count array is 1, the number is already present in row and hence the row is not valid, return false
                if(count[board[j][i] - '0' - 1] == 1) {
                    return false;
                }
              
                // Increment the count of number at (j, i) and store it at index (number - 1) in count array
                count[board[j][i] - '0' - 1]++;
            }
        }
        return true;
    }    
    
    private boolean isValidBox(char[][] board, int stratI, int startJ) {
      
        // Define a count array to store count of each numbers   
        int[] count = new int[9];
      
        // Initialize count to 0 for each numbers in the count array. Here initialization of count array is outside loop as we were checking counts across each row or columns earlier.
        // Here we need the same count array even if row/ column is incremented
        Arrays.fill(count, 0);
      
      
        // Start iterating from start row index to start row index + 3
        for(int i = stratI; i < stratI + 3; i++) {
          
            // Start iterating from start column index to start column index + 3
            for(int j = startJ; j < startJ + 3; j++) {
              
                // If the char at position j, i is empty('.'), then skip and continue with next iteration in inner loop
                if(board[i][j] <= '0' || board[i][j] > '9') {
                    continue;
                }                
              
                // If the count of number at (i, j) i.e, value at index (number - 1) in count array is 1, the number is already present in row and hence the row is not valid, return false
                if(count[board[i][j] - '0' - 1] == 1) {
                    return false;
                }
              
                // Increment the count of number at (i, j) and store it at index (number - 1) in count array
                count[board[i][j] - '0' - 1]++;
            }
        }
        return true;
    }
    
    private boolean isValidBoxes(char[][] board) {
      
        // This method checks if each box is valid using helper method above.
        // The helper method accepts input as board, start row index, start column index
        // In the loops the isValidBox would be called in the order)
        // 1st Iteration isValidBox(board, 0, 0)
        // 2nd Iteration isValidBox(board, 0, 3)
        // 3rd Iteration isValidBox(board, 0, 6)
        // 4th Iteration isValidBox(board, 3, 0)
        // 5th Iteration isValidBox(board, 3, 3)
        // 6th Iteration isValidBox(board, 3, 6)
        // 7th Iteration isValidBox(board, 6, 0)
        // 8th Iteration isValidBox(board, 6, 3)
        // 9th Iteration isValidBox(board, 6, 6)
      
        // Each Box starts at rows 0, 3 and 6
        for(int i = 0; i < 9; i+= 3) {
          
            // Each Box starts at columns 0, 3, 6
            for(int j = 0; j < 9; j+= 3) {
              
                // Checks if the inner box starting at row i and column j is valid and returns false if inner box is not valid
                if(! isValidBox(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean isValidSudoku(char[][] board) {
        // return AND of helper methods
        return isValidRows(board) && isValidColumns(board) && isValidBoxes(board);
    }
}
