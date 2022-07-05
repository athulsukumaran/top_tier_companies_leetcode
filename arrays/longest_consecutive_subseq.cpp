/*
    https://leetcode.com/problems/longest-consecutive-sequence
*/

/*
    Approach
        -  We can do this using sort. But it takes O(nlogn) time.
        -  Here, we first push all ements in the array to a set. Note find
           operation in set takes O(1) time.
        -  We iterate through each element in the array and only if there does not exist
           an element in the set that is n-1 (means this is the least number in the sequence), we check for the presence of next consecutive elements in the set.
        -  We keep track of longest streak for each num in the array that is the lowest in the sequence. And update max streak till now with this value. And finally return max streak.
        -  This takes O(n) and not O(n*n) even though we have a nested loop since, we only traverse the sequence starting from the lowest number in the sequence.
           For eg, if we had 1,2,3,4 in the array,
                             we iterate only 1,2,3,4 and not
                             2,3,4 or 3,4
         - Youtube link
            https://www.youtube.com/watch?v=P6RZZMu_maU
*/

/*
    Complexity
        -   Space = O(n) - For the set
        -   Time = O(n) - Explained the reason above
*/

class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        //this is the value that is returned. Max streak in the whole array
        int longestStreak=0;
        
        //we need this set for efficient find operation
        unordered_set<int> s(nums.begin(), nums.end());
               
        for(auto num: nums)
        {
            //go for inner iteration only if we have the previous element in the set
            if(s.find(num-1) == s.end())
            {
                int curNum = num;
                //to track current streak, compare gainst current max streak and update the latter if required
                int curStreak = 1;
                
                //find all consecutive integers in the set and increment streak
                while(s.find(curNum+1) != s.end())
                {
                    curNum++;
                    curStreak++;
                }
                
                //update longest streak if current streak is larger
                longestStreak = max(curStreak, longestStreak);
            }
        }
        
        return longestStreak;
    }
};