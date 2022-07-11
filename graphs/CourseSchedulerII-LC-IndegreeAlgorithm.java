/*
 * Question:
 * https://leetcode.com/problems/course-schedule-ii/
 */

/*
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 * 
 * Example 1:
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
 *
 * Example 2:
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 *
 *Example 3:
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 *
 * Solution Approach Used:
 * The solution to the question is intuitive based on topological ordering and keeping track of indegrees
 * We could also use the DFS way to detect cycle and then find the topological ordering
 * Note: Topological sort can have more than 1 result and hence we need to return only any of the one sorted order
 *
 * We have an array of edges, in either DFS or in Indegree method, we'll need to create an adjacency list from this edges array.
 * The edges are directed and in the order [A, B] meaning the course B should be completed before A.
 * Thus while building an adjacency list, we create a list of course A, for which course B is the prerequisite.
 * Another parameter we need to do the topological sorting in indegree algorithm is the number of incoming edges to a particular node.
 * Say there are 2 prerequisites for a course A, then the indegree of A is 2.
 *
 * In the function, we get input params as number of courses and array of edges
 * First we can initialize an array to store the sorted list of courses
 * Then we can initialize an array to store the indegree value at index i for course i
 * We can also initialize an adjacency list map for each course
 * 
 * For creating the adjacency list from the array of edges, we can iterate over the array of edges, get the course number and prerequisite course number from the edge.
 * Get the adjacency list of prerequisite course and add the course to the list
 * Increment the indegree count of course as well
 * 
 * Initialize a queue to keep track of courses with indegree 0. (Any data structure where we add new element to last and pop element from starting)
 * Iterate over the adjacency list map and add courses with no prerequisites required to the queue
 *
 * Do a while loop till queue is empty
 * Inside the while loop, we can first pop the element from front and add it to the result topological sorted array 
 * Now for that popped element, check the courses which have this course as prerequisite from adjacencyListMap and decrement their indegree counts.
 * Also add any course for which the indegree is 0 to the queue after decrementing the indegree count as all their prerequisite courses are visited.
 * After the loop is empty, check if all the courses are added to the topological sorted array (by checking if total num courses == count of elements in resulting topological sorted array)
 *
 * Time Complexity: O(v + e), Space Complexity: O(v + e) where v is the number of vertices and e is the number of edges
 *
 * Reference
 * https://leetcode.com/problems/course-schedule-ii/solution/
 */


class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
      
        // Initialize the course order array and course indegree array
        int[] courseOrderArray = new int[numCourses];        
        int[] courseIndegreeArray = new int[numCourses];
        
        // Declare and initialize an adjacency list map for storing the adjacency list for each course
        Map<Integer, List<Integer>> adjacencyListMap = new HashMap<>();
        
        for(int i = 0; i < numCourses; i++) {
            adjacencyListMap.put(i, new ArrayList<>());
        }
        
        // Iterate over the array of edges input and build the adjacency list
        for(int i = 0; i < prerequisites.length; i++) {
          
            // edge [A, B] in prerequisites array means B is a prerequisite for A
            int courseNo = prerequisites[i][0];
            int prerequisiteCourseNo = prerequisites[i][1];
            
            // Increment the indegree count for course
            courseIndegreeArray[courseNo]++;

            // Also add this course to the adjacency list of prerequisite course
            List<Integer> adjacencyList = adjacencyListMap.get(prerequisiteCourseNo);
            adjacencyList.add(courseNo);
            adjacencyListMap.put(prerequisiteCourseNo, adjacencyList);
        }
        
        // Queue to keep track of the courses with indegree 0 at a given point
        Queue<Integer> queue = new LinkedList<>();
        
        // Initializing the queue with courses which can be a potential starting point for traversals (0 prerequisites courses required)
        for(int i = 0; i < numCourses; i++) {
            if(courseIndegreeArray[i] == 0) {
                queue.add(i);
            }
        }
        
        // courseOrderIndex to keep track of position at which course popped from front of queue is in the course order array
        int courseOrderArrayIndex = 0;
        
        // Loop till the queue is empty
        while(! queue.isEmpty()) {
          
            // Pop element from first. This course have indegree 0 currently
            // Hence this course can be appended to the course order result array
            int courseNo = queue.poll();
            courseOrderArray[courseOrderArrayIndex++] = courseNo;
            
            // Get the adjacency list for this course and decrement the indegree for all the follow up courses for which this course was a prerequisite
            // This is because now that this course is already completed, if any follow up course doesnt depend on any other courses, they will have a indegree of 0
            // Hence those follow up courses can be added to the last of queue
          
            List<Integer> adjacencyList = adjacencyListMap.get(courseNo);
            
            for(int i = 0; i < adjacencyList.size(); i++) {
                int followUpCourseNo = adjacencyList.get(i);
                courseIndegreeArray[followUpCourseNo]--;
                
                if(courseIndegreeArray[followUpCourseNo] == 0) {
                    queue.add(followUpCourseNo);
                }
            }
        }
        
        // If the total number of courses and number of courses in the course order array are equal, all courses have been added to the order, then return the sorted order array
        // Else, some courses are missed while traversing and a valid order is not found, return empty array in such cases
        if(courseOrderArrayIndex == numCourses) {
            return courseOrderArray;
        } else {
            return new int[0];
        }
    }
}
