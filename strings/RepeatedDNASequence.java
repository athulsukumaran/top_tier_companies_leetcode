/*
 * Question:
 * https://leetcode.com/problems/repeated-dna-sequences
 */

/*
 * The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
 * For example, "ACGAATTCCG" is a DNA sequence.
 * When studying DNA, it is useful to identify repeated sequences within the DNA.
 * Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 * You may return the answer in any order.
 * 
 * Example 1:
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * Output: ["AAAAACCCCC","CCCCCAAAAA"]
 *
 * Example 2:
 * Input: s = "AAAAAAAAAAAAA"
 * Output: ["AAAAAAAAAA"]
 *
 * Constraints:
 * 1 <= s.length <= 105
 * s[i] is either 'A', 'C', 'G', or 'T'. 
 *
 * Solution Approach Used:
 * This is a normal sliding window question. We need to check the possible continuous substrings of length 10(as its given in question) and record their counts.
 * After calculating the counts for all possible substring, If a sequence occurs more than once, we can add it to the result array list. 
 *
 * So looking at the first example may seem confusing as you see continuous A and C occurring more than once, and output only shows 2 seqs of length 10.
 * This is because in the continuous Cs, the first time, there are only 5 Cs and second time there are 6 Cs.
 * Hence seq like AAACCCCCAA, ACCCCCAAAA etc are not listed in the output.
 *
 * Now back to approach.
 * We can keep a hashmap to store the sequence and its count.
 * Iterate over the input sequence from i = 0 to input string length - 10(inclusive).
 * In each iteration, get a substring of s, starting at index i of length 10.
 * Check if the substring seq is already in the count map and increment the count or create a new entry in count map for the seq
 *
 * Now Iterate over the count map key set
 * If the key (seq) in count map has value greater than 1, then add the seq to the result array list.
 *
 * Time Complexity: O(n), Space Complexity: O(1) where n is length of complete sequence.
 * Though this is a O(n) solution, There is a solution with bit manipulation also it seems. Saw it in discuss section.
 * Have to go through that later.
 */

class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
             
        // Output array list
        List<String> result = new ArrayList<>();
      
        // Count Map to store count of occurrences of a seq in the input string
        HashMap<String, Integer> seqCount = new HashMap<>();
      
        // Iterating from 0 to input string length - 10
        // This is because, then the second arg in substring method doesnt go beyond the length of string
        for (int i = 0; i <= s.length() - 10; i++) {
          
            // Get the seq of length 10, starting at index i
            String seq = s.substring(i, i + 10);
          
            // If seq is already present in count map, increment the count
            // Else add an entry to count map for the seq and count 1
            if(seqCount.get(seq) != null) {
                seqCount.put(seq, seqCount.get(seq) + 1);
            } else {
                seqCount.put(seq, 1);
            }
        }
      
        // Iterate for keys(seq) in count map key set
        // If the value is greater than 1, add it to the result array list
        for (String seq : seqCount.keySet()) {
            if (seqCount.get(seq) > 1) {
                result.add(seq);
            }
        }
      
        // Return result
        return result;
    }
}
