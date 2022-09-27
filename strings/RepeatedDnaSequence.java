/*
    https://leetcode.com/problems/repeated-dna-sequences
*/

/*
    Approach
        -   Sliding window algorithm can be used here.
        -   l starts from 0th position. r is always at 10th index from l
        -   Every unique 10 character substrings, we need to store somewhere.
            Since it is unique, we can use a hashset.
        -   If, while advancing the sliding window, we find the substring matches an
            entry in the set, we can add it to the result set.
        -   Note, the result is also a set. Otherwise if we had used an array list, if we encountered the same substring more than once, we would have added it to the array list more than once.
        -   Finally convert the set to array list and return (since the function return value is an array list)
        
*/

/*
    Complexity
        -   Time = O(n)
        -   Space = O(n)
*/


class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        //The hashset where unique 10 char substrings will be stored
        HashSet<String> subStrSet = new HashSet<>();
        //The result hashset
        HashSet<String> res = new HashSet<>();
        
        //sliding window of size 10
        for(int i=0; i+9 < s.length(); i++)
        {
            String subst = s.substring(i, i+10);
            //check if this 10 char substring already exists in the hashset. If yes, add it to result
            if(subStrSet.contains(subst))
            {
               res.add(subst); 
            }
            //if not, add it to the hashset
            else
            {
               subStrSet.add(subst);        
            }
        }
               
        //convert to ArrayList and return
        return new ArrayList<>(res);
    }
}