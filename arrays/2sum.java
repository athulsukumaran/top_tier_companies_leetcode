/*
    https://leetcode.com/problems/two-sum/
*/

/*
    Approach 1 - Hash Map
        -   We can use a hashmap that stores key = value in nums array, val = index of this value
        -   While traversing the array if we find target-nums[i] in the map, it means we have found the pair that adds up to target. We can return the current index and the index stored in the map.
        -   If we don't find it, we add to map the value at i and the index i to the map
        
*/

/*
    Complexity
        -  Time = O(n)
        -   Space = O(n)
*/


class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> mp = new HashMap();
        int[] res = new int[2];
        
        for(int i=0; i<nums.length; i++)
        {
            int valToFind = target - nums[i];
            
            if(mp.containsKey(valToFind))
            {
                res[0] = mp.get(valToFind);
                res[1] = i;
                
                return res;
            }
            else
            {
                mp.put(nums[i], i);
            }
        }
        
        //returns [] if we didn't find a pair that adds up to target
        return res;
    }
}


/*
    Approach 2  - Two pointer  
        -   This wont work if we need to return index (or the solution would be complex). If we just needed to return the values adding up to target this would work.
    
*/

/*
    Complexity
        Time = O(nlogn)
        Space = O(1)
*/

/*
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        int l=0, r=nums.length-1;
        int[] res = new int[2];
        
        while(l < r)
        {
            int sum = nums[l]+nums[r];
            if(sum < target)
                l++;
            else if(sum > target)
                r--;
            else // sum = target
            {
                res[0] = nums[l];
                res[1] = nums[r];
                return res;
            } 
        }
        
        return res;
    }
}

*/