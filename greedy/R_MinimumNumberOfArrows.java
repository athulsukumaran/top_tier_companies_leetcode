/*
 * Question:
 * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/description/
 */

/*
 * There are some spherical balloons taped onto a flat wall that represents the XY-plane.
 * The balloons are represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches between xstart and xend.
 * You do not know the exact y-coordinates of the balloons.
 * Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis.
 * A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the number of arrows that can be shot. 
 * A shot arrow keeps traveling up infinitely, bursting any balloons in its path.
 * Given the array points, return the minimum number of arrows that must be shot to burst all balloons.
 *
 * Example 1:
 * Input: points = [[10,16],[2,8],[1,6],[7,12]]
 * Output: 2
 * Explanation: The balloons can be burst by 2 arrows:
 *            - Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
 *            - Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
 *
 * Example 2:
 * Input: points = [[1,2],[3,4],[5,6],[7,8]]
 * Output: 4
 * Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.
 * 
 * Example 3:
 * Input: points = [[1,2],[2,3],[3,4],[4,5]]
 * Output: 2
 * Explanation: The balloons can be burst by 2 arrows:
 *            - Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
 *            - Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].
 *
 * Constraints:
 * 1 <= points.length <= 105
 * points[i].length == 2
 * -(2 ^ 31) <= xstart < xend <= (2 ^ 31) - 1
 *
 * Solution Approach Used:
 * From the question it can be inferred as a overlapping intervals question.
 * So the first step would be draw the intervals and vizualize.
 * Also inorder to find the overlapping regions or non overlapping regions from the array of regions
 *            * We need to pass through all the regions after sorting the regions in the order of their starting points.
 *
 * We can use a helper comparator class to sort the regions based on the region starting and ending points
 * 
 * After sorting the regions, we still need to find the minimum number of arrows to burst the balloons
 *
 * Brute force approach: 
 * The brute force way is to iterate over each regions and checking if the region is having intersection with some other regions in the intersecting regions array
 *              - If the region has an intersection with other regions, update the starting and ending points of the intersecting region
 *              - Else, insert this region as a new intersecting region with itself 
 *
 * After iterating over all the regions, the number of intersecting regions will be the number of minimum arrows needed to burst the balloons
 *
 * This works, but in worst case, if no regions have intersection with other regions, the time complexity will become O(n ^ 2)
 * Time Complexity: O(n ^ 2), Space Complexity: O(n) where n is the number of balloons
 *
 * Greedy approach:
 * This is similar to brute force way, but instead of keeping an array of intersecting regions, we can store the last intersecting regions ending point and keep count of regions(arrows)
 *
 * The condition to check if a region is having intersection with its previous regions would be to compare the starting point of this region with last intersection region ending point
 *              - If the starting point of region is greater than the ending point of last intersection region, increment the count and update the last intersecting regions end point with this regions ending point
 *              - Else, this region is intersecting with last intersection region, Hence update the ending point of last intersecting region with the minimum of its current value and this regions ending point
 * 
 * After iterating over all the regions, we will have the arrows count(count of intersecting regions)
 *
 * This works on the greedy approach, where in we dont compare across all the intersecting regions, but with the last intersection found
 * Time complexity is O(nlogn) as we need to sort the array of regions
 * Time Complexity: O(nlogn), Space Complexity: O(1) where n is the number of balloons
 *
 */
 
 class Solution {
 
   // Helper comparator class
   // balloon is of type int array with starting point at index 0 and ending point at index 1 as from the inputs in question
    private class BalloonSortComparator implements Comparator<int[]> {
        public int compare(int[] balloonA, int[] balloonB) {
            // If balloons starting point are equal, compare their ending points
            // Else compare the starting points
            if(balloonA[0] == balloonB[0]) {
                return balloonA[1] > balloonB[1] ? 1 : -1;
            } else {
                return balloonA[0] > balloonB[0] ? 1 : -1;
            }
        }
    }

    public int findMinArrowShots(int[][] points) {
        // Brute force method
        // List<List<Integer>> intersectingRegionArray = new ArrayList<>();
        // List<Integer> intersectingRegion = new ArrayList<>();
        //
        // Arrays.sort(points, new BalloonSortComparator());
        //
        // for(int i = 0; i < points.length; i++) {
        //     int balloonStartingPoint = points[i][0];
        //     int balloonEndingPoint = points[i][1];
        //
        //     boolean intersectionFound = false;
        //
        //     for(int j = 0; j < intersectingRegionArray.size(); j++) {
        //         ArrayList<Integer> region = (ArrayList) intersectingRegionArray.get(j);
        //         int regionStartingPoint = region.get(0);
        //         int regionEndingPoint = region.get(1);
        //
        //         if(balloonStartingPoint <= regionEndingPoint) {
        //             intersectionFound = true;
        //             region.set(0, Math.max(regionStartingPoint, balloonStartingPoint));
        //             region.set(1, Math.min(regionEndingPoint, balloonEndingPoint));
        //         }
        //
        //         if(intersectionFound) {
        //             intersectingRegionArray.set(j, region);
        //         }
        //     }
        //
        //     if(!intersectionFound) {
        //         ArrayList newRegion = new ArrayList<>();
        //         newRegion.add(balloonStartingPoint);
        //         newRegion.add(balloonEndingPoint);
        //         intersectingRegionArray.add(newRegion);
        //     }
        // }
        //
        // return intersectingRegionArray.size();

        // Greedy method
        // Sort the balloon regions array using custom comparator
        Arrays.sort(points, new BalloonSortComparator());

        // Initialize arraows count and lastRegionEndingPoint with base values
        int arrowsCount = 1;
        int lastRegionEndingPoint = points[0][1];

        // Iterate over the remaining balloon regions
        for(int i = 1; i < points.length; i++) {
            int balloonStartingPoint = points[i][0];
            int balloonEndingPoint = points[i][1];

            // If this balloons starting point is greater than last intersection regions ending point => this region and last intersection region doesnt intersect
            //          - Increment the arrows count
            //          - Update the last intersection regions ending point with this regions ending point
            //
            // Else update the last intersection regions ending point with the minimum of its current value and this regions ending point
            if(balloonStartingPoint > lastRegionEndingPoint) {
                arrowsCount++;
                lastRegionEndingPoint = balloonEndingPoint;
            } else {
                lastRegionEndingPoint = Math.min(lastRegionEndingPoint, balloonEndingPoint);
            }
        }

        // Return arrows count
        return arrowsCount;
    }
}
