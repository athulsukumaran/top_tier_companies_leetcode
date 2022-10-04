/*
 * Question:
 * https://leetcode.com/problems/container-with-most-water
 */

/*
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * Return the maximum amount of water a container can store.
 *
 * Example 1:
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
 * In this case, the max area of water (blue section) the container can contain is 49.
 * 
 * Example 2:
 * Input: height = [1,1]
 * Output: 1
 *
 * Solution Approach Used:
 * The brute force way s to find the area of all possble rectangle containers an find the container with maximum area.
 * But the brute force way takes O(n^2) time coplexity. This is because there can be ((n - 1) * n) / 2 possible containers.
 *
 * A better way to solve this problem in linear time is to use the dynamic window approach and find the container with maximum area.
 * We can use the dynamic sliding window approach using left and right pointers and movind the pointers from both end to find the sub array size for which the sum is greater than or equal to target.
 * We can also keep a maxArea variable, initially set to 0 and update the maximum value if container area is greater than current maximum area and return this maximum area at the end
 *
 * We know that the area of a rectangle is the product of the width and height of the rectangle
 * The height of a container here will be the minimum of either height at left poninter or at right pointer
 * We can use a while loop to move the pointers and exit condition would be left = right as the width becomes zero in that case.
 *
 * Initally we can set the left pointer to the starting of input array(0) and right to array end(array len - 1).
 * Now the width of the container will be (right - left) and height will the minimum of either height at left or right index.
 * Once we calculate the area of this container, check if this area is greater than the existing max area and update it.
 * 
 * Now we already know that if height at left is lesser than that at right, there could be a taller line toward right direction of left such that the new container are could have a greater area compared to this container
 * So we can move the left pointer keeping the right pointer there itself.
 * Similarly if line at right is shorter or equal to that of left, then we could try to find a taller line towards left direction of right such that the new container area could have a greater area compared to this container
 * The direction we are moving is inside as we have started the pointers from noth the ends.
 * This way when we iterate till left is less than right, we will get the container with maximum area
 *
 * Time Complexity: O(n), Space Complexity: O(1) where n is length of array
 * Check neetcode solution in youtube for better understanding
 * https://www.youtube.com/watch?v=UuiTKBwPgAo
 *
 */
 
 class Solution {
    public int maxArea(int[] height) {
        // Max area variable to store the maximum area
        // Initially max area set to 0
        int maxArea = 0;
        
        // Initializing left and right pointers
        int left = 0, right = height.length - 1;
        
        // Checking the area of containers formed by x axis(width) and heights(vertical lines) at left and right pointers
        // Since we are starting from both ends, we are closing in from both ends in each iteration till left = right
        while(left < right) {
        
            // Finding container width, height and area
            int containerWidth = right - left;
            int containerHeight = Math.min(height[left], height[right]);
            int containerArea = containerWidth * containerHeight;
            
            // Checking if this container area is greater than the maximum value found so far (Greedy Approach)
            maxArea = Math.max(containerArea, maxArea);
            
            // Checking which pointer line is short and updating the pointer for next iteration
            if(height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        // Returning result at the end
        return maxArea;
    }
}
