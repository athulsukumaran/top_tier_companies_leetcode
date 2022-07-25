/*
    https://leetcode.com/problems/climbing-stairs
*/

/*
    Approach
        -   The number of ways to reach a particular step (n) will be the sum
            of total number of ways to reach the step n-1 added to the total
            number of ways to reach the step n-2
        -   The number of ways to reach step 1 will be just 1 and step 2 will
            be 2
            1+1 or 2 (Example is given in the problem statement is itself).
            From here, to reach step 3, there are 1+2 = 3 number of ways
            (Again, example is given in the problem statement)
        -   We use dynamic programming to solve this by tabulating n-1 and
            n-2 total ways at each position n and adding these two values
*/


/*
    Complexity
        -   Time = O(n)
        -   Space = O(1)
        
*/


class Solution {
public:
    int climbStairs(int n) {
        //Handle base cases
        if(n <= 0) return 0;
        else if(n == 1) return 1;
        else if(n == 2) return 2;
        
        //below 2 variables help in keeping track of the number of ways to reach n-1 th and 
        //n-2 th steps respectively
        
        int one_step_before = 2;
        int two_steps_before = 1;
        
        //this variable keeps track of the total number of ways to reach nth step
        int total_ways = 0;
        
        for(int i=3; i<=n; i++)
        {
            //total number of ways to reach nth step is basically the sum of the other 2
            total_ways = one_step_before+two_steps_before;
            two_steps_before = one_step_before;
            one_step_before = total_ways;
        }
        
        return total_ways;
    }
};