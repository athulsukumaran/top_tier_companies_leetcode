/*
 * Question:
 * https://leetcode.com/problems/minimum-rounds-to-complete-all-tasks/
 */

/*
 * You are given a 0-indexed integer array tasks, where tasks[i] represents the difficulty level of a task. 
 * In each round, you can complete either 2 or 3 tasks of the same difficulty level.
 * Return the minimum rounds required to complete all the tasks, or -1 if it is not possible to complete all the tasks.
 *
 * Solution Approach Used:
 * We will use a helper method to check the number of rounds required to complete a given count of task of some difficulty level.
 * In the helper method, We will accept the count of a task of some difficulty level, if the count is 2 we return 1 as it is the minimum round to complete 2 tasks.
 * Else if the count is greater then or equal to 3, then the count can be expressed as 3q + m where q can be 1, 2... (quotient) and m can be either 1 or 2 (reminder/ modulus) 
 * If count is a perfect multiple of 3 we'll require minimum x rounds to finish all tasks for that diffficulty level => total q rounds
 * Else if count is of 3q + 1 format, we'll require q - 1 rounds of doings 3 tasks and 2 rounds of doing 2 tasks to finish the tasks => total q + 1 rounds
 * Else if count is of 3q + 2 format, we'll require q rounds of doing 3 tasks and 1 round of doing 2 tasks to finish the tasks => total q + 1 rounds
 *
 * In the main method, we will first sort the array, so that tasks of same difficulty levels can be grouped together.
 * Storing the count of tasks based on difficulty levels using arrays/ hashmaps can be costly considering the length of array and range of difficulty levels
 * Initialize currentTask as task[0] and its occurrence as 1.
 * Iterate the loop from i = 1 to tasks.length and check if task[i] == prevTask and increment the countPrevTask if they are same.
 * Else check if count of prevTask is 1 and return -1, Since it cannot be finished(we can do in rounds of 2 or 3 only) 
 * Else get the rounds required for finishing previous task and add it to the total rounds.
 * Also update previous task to tasks[i] and count of previous tasks as 1.
 * 
 * Incase the loop is completed, check if count of last task(tasks[n - 1]) is 1 and return -1, Else get rounds required to finish last task and return total rounds.
 *
 * Time Complexity: O(nlogn), Space Complexity: O(1) where n is length of tasks array
 */

class Solution {
  
    // Helper method which returns number of rounds to complete given count of tasks
    private int getRoundsFor(int count) {
      
        // If count == 1 condition is handled in the main method as we return -1 there
        // If count == 2 => 1 round. 
        if(count == 2) {
            return 1;
        } else {
          
            // Get the modulus and quotient values
            int mod = count % 3;
            int quo = count / 3;
          
            // If count is perfect multiple of 3
            if(mod == 0) {
                return quo;
            } else {
              
                // Else either ((quo - 1) + 2) rounds or (quo + 1) rounds ==> both are quo + 1 effectively
                return quo + 1;
            }
        }
    }
    
    public int minimumRounds(int[] tasks) {
      
        // Sorting array to group tasks at same difficulty levels
        Arrays.sort(tasks);
      
        // Total rounds variable initialized to 0
        int rounds = 0;
      
        // Previous task and count of previous task initialized to first element and 1 repectively
        int prevTask = tasks[0];
        int countPrevTaskOccurrences = 1;        
      
        // Iterate from i = 1 to n      
        for(int i = 1; i < tasks.length; i++) {
          
            // If prev task is equal to current task, increment count of previous task
            if(prevTask == tasks[i]) {
                countPrevTaskOccurrences++;
            } else {
              
                // If count of prev task is 1 and return -1
                if(countPrevTaskOccurrences == 1) {
                    return -1;
                }
              
                // If prev task count is not 1, then get number of rounds required for finishing prev task and add it to total rounds so far 
                rounds = rounds + getRoundsFor(countPrevTaskOccurrences);
              
                // Update prev task and prev task count to current task and 1 respectively
                prevTask = tasks[i];
                countPrevTaskOccurrences = 1;                
            }
        }
      
        // If count of last task is 1 and return -1
        if(countPrevTaskOccurrences == 1) {
            return -1;
        }
      
        // If last task count is not 1, then get number of rounds required for finishing last task and add it to total rounds so far
        rounds = rounds + getRoundsFor(countPrevTaskOccurrences);
        return rounds;      
    }
}
