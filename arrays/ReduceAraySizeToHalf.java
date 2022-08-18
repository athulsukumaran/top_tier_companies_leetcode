/*
 * Question:
 * https://leetcode.com/problems/reduce-array-size-to-the-half
 */

/*
 * You are given an integer array arr. You can choose a set of integers and remove all the occurrences of these integers in the array.
 * Return the minimum size of the set so that at least half of the integers of the array are removed.
 *
 * Example 1:
 * Input: arr = [3,3,3,3,5,5,5,2,2,7]
 * Output: 2
 * Explanation: Choosing {3,7} will make the new array [5,5,5,2,2] which has size 5 (i.e equal to half of the size of the old array).
 * Possible sets of size 2 are {3,5},{3,2},{5,2}.
 * Choosing set {2,7} is not possible as it will make the new array [3,3,3,3,5,5,5] which has a size greater than half of the size of the old array.
 * 
 * Example 2:
 * Input: arr = [7,7,7,7,7,7]
 * Output: 1
 * Explanation: The only possible set you can choose is {7}. This will make the new array empty.
 *
 * Solution Approach Used:
 * The appraoch is brute force
 * We need to iterate over the input array and store the frequency of numbers in the array
 * Once we have the numbers and their frequency, the minimum numbers to be removed from array to reduce the array size by half is to start removing numbers in their decreasing order of frequecy and checking if the reduced array size is less than or equal to half of original array size
 * 
 * For this, we have to first write a custom comparator that compares 2 entries with key and value as number and its count and return them in decreasing order
 * In the main method, we'll first use a hashmap to store the count of numbers in the array
 * Then we need to convert this Map to a list of map entries and do the sorting.(HashMap doesnt have any order, Have to check about linked hashmap as well as lambdas or stream APIs which converts Map to List of map entries)
 * We then call the custom comparator to sort the list of entries based on the decreasing order of count of numbers
 *
 * Finally iterate over the sorted list and see if removing the entry at begining will make the reduced array size less than or equal to half of original array size
 *
 * Time Complexity: O(n), Space Complexity: O(n) where n is length of array
 */

class Solution {
  
    // Custom comparator class added to compare entries that represent number as key and its frequency count as value in decreasing order
    public class SortListByValueDescending implements Comparator<Map.Entry<Integer, Integer>> {
        public int compare(Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2) {
            return entry2.getValue() - entry1.getValue();
        }
    } 
    
  
    // Main method
    public int minSetSize(int[] arr) {
        // Declaring hashmap to store the numbers and their respective count
        Map<Integer, Integer> numCountsMap = new HashMap<>();
        
      
        // Iterating over the input array and calculating count for each numbers in the array. O(n) time
        for(int i = 0; i < arr.length; i++) {
            if(numCountsMap.get(arr[i]) != null) {
                numCountsMap.put(arr[i], numCountsMap.get(arr[i]) + 1);
            } else {
                numCountsMap.put(arr[i], 1);
            }
        }
        
        // Declaring list to store the entries in hashmap as haashmap doesnt have any particular index to denote sorted order
        List<Map.Entry<Integer, Integer>> numCountsEntryList = new ArrayList<>();
        
        // Iterating over hashmap entries and adding it to the arraylist
        for(Map.Entry<Integer, Integer> entry : numCountsMap.entrySet()) {
            numCountsEntryList.add(entry);
        }
        
        // Sorting the arraylist with custom comparator
        // This will sort the entries in array list based on the freuency of each number in decreasing order
        Collections.sort(numCountsEntryList, new SortListByValueDescending());
        
        // Declaring variables for iterating over the sorted list and keeping track of reduced array size
        int countNumsToRemove, reducedArraySize;
        
      
        // Iterating from the first entry in sorted list till reduced array size is greater than half of original array size
        // When this condition fails, the reduced aray size becomes less than or equal to half of original array size => no more elements to be removed from the array
        // Hence we can exit the loop and return the count of numbers to remove
        for(countNumsToRemove = 0, reducedArraySize = arr.length; countNumsToRemove < numCountsEntryList.size() && reducedArraySize > arr.length / 2; countNumsToRemove++) {
            int numCount = numCountsEntryList.get(countNumsToRemove).getValue();
            reducedArraySize -= numCount;
        }
        
        // Returning the result
        return countNumsToRemove;
        
    }
}
