/*
    Link - https://leetcode.com/problems/group-anagrams  
*/

/*
    Approach
      - Sort each string and add it to the value field of
        a map where the key is the sorted value of that string.
        
        For example, eat and tea will be added as a vector with 
        content ["eat", "tea"] to that particular entry in the map with key 
        "aet".
        
      - Iterate through the map, push each value(which is a vector of strings)
        to the result vector.
        
      - To improve time complexity, count sort can be used. Count sort has a 
        complexity of O(n+k), where n = number of elements to sort, k = range of 
        values these elements can take. Count sort is most efficient when k<=n
*/

/* Complexity Analysis
      - Time Complexity - O(n*m), n = number of strings, m = size of string
      - Space complexity - O(n+k), n = number of strings in the array, k const 26
*/

class Solution {
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        vector<vector<string>> result;
        unordered_map<string, vector<string>> mp;
        
        //sort the string and append to map values
        for(string str : strs)
        {
            mp[countSort(str)].push_back(str);
        }
        
        //traverse through map and create the result vector
        for(pair<string, vector<string>> mpEntry : mp)
        {
            result.push_back(mpEntry.second);
        }
        
        return result;
    }

private:
    string countSort(string str)
    {
        //array of 26, since all lowercase char
        int counter[26] = {0};
        string sortedStr;
        
        //increment the counter for char in string by 1
        for(char c: str)
        {
            counter[c-'a']++;
        }
        
        //repeat each char in [0,26] counter of that char times
        // something like bacad will be changed to aabcd
        for(int c=0; c<26; c++)
        {
            sortedStr += string(counter[c], c+'a');
        }
        
        return sortedStr;
    }
};