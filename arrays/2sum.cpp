
/*  
    https://leetcode.com/problems/two-sum/
*/

/* Short description of solution - test
*/

class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        unordered_map<int, int> m;
        vector<int> res;
        
        for(int i=0; i<nums.size(); i++)
        {
            unordered_map<int, int>::iterator it = m.find(target-nums[i]);
            //if found in map, add index to result vector, since 
            if(it != m.end())
            {
                res.push_back(i);
                res.push_back(it->second);
                return res;
            }
            else
            {
                m[nums[i]] = i;
            }
        }
        return res;
    }
};