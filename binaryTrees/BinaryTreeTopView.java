 /*
 * Question:
 * https://www.hackerrank.com/challenges/tree-top-view/problem/
 */

/*
 *Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 * Example 1:
 *  1
 *   \
 *    2
 *     \
 *      5
 *     /  \
 *    3    6
 *     \
 *      4
 *
 * Output: 1 2 5 6
 *
 * Solution Approach Used:
 * We will use a helper method to traverse the tree in preOrder(Have to check if inOrder or postOrder also works, Should be fine it seems as we use map to store the distance and nodes at distance and level)
 * The the horizontal distance of root would be 0, and of the left sub tree node would be its parent nodess distance - 1, and of the right sub tree node would be its parent nodess distance + 1
 * We will use a map with Integer key and ArrayList value to store the horizontal distance as key and the List of (level and node data) as the value
 * The level at which node data is found is important as while traversing if there exists a value already in the map at that horizontal distance, We will check if the existing level in the map value is greater than the level of node being considered and update the map if yes.
 * This is because in top view the for nodes at same distance, the nodes at lower levels(from top) will be visible in the top view. (Could be opposite in case of bottom view, have to verify it)
 * 
 * In the main method we'll start from root, distance 0 and level 0.
 * Time Complexity: O(n), Space Complexity: O(n)
 */

class Node {
    Node left;
    Node right;
    int data;
    
    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class Solution {
  
    // Map used to store horizontal distance as key and List of integer with (level and node data) as value
    private static Map<Integer, List<Integer>> nodeAtDistance = new HashMap<>();
    
    // PreOrder traversal method, with node, distance and level as parameters
    private static void preOrder(Node node, int distance, int level) {
      
        // If node not equal
        if(node != null) {
            
            // If the map already has value at the distance
            if(nodeAtDistance.get(distance) != null) {
              
                // If the level in the list value of the map is greater than the level of node being considered(current), then the map value at the distance has to be updated
                if((nodeAtDistance.get(distance)).get(0) > level) {
                    List<Integer> levelAndDataList = new ArrayList<>();
                    levelAndDataList.add(level);
                    levelAndDataList.add(node.data);
                    nodeAtDistance.put(distance, levelAndDataList);
                }
              
            } else {
              
                // If the map doesnt have any value at that distance then add new entry in the map
                List<Integer> levelAndDataList = new ArrayList<>();
                levelAndDataList.add(level);
                levelAndDataList.add(node.data);
                nodeAtDistance.put(distance, levelAndDataList);
            }
          
            // Pre Order recurssive calls
            // For left sub tree, the distance would be parent node distance - 1 and level will be level + 1
            // For right sub tree, the distance would be parent node distance + 1 and level will be level + 1          
            preOrder(node.left, distance - 1, level + 1);
            preOrder(node.right, distance + 1, level + 1);            
        }
    }
       
	  public static void topView(Node root) {
        preOrder(root, 0, 0);
        
        // After populating the nodes at distances map, we need to sort the distances (..-2, -1, 0, 1, 2..)
        List<Integer> distances = new ArrayList<>();
        
        for(Map.Entry<Integer, List<Integer>> entry: nodeAtDistance.entrySet()) {
            distances.add(entry.getKey());
        }
        
        Collections.sort(distances);
        
        for(int i = 0; i < distances.size(); i++) {
            System.out.print(nodeAtDistance.get(distances.get(i)).get(1) + " ");
        }
    }
  
    // The below main and node insert methods are given by the hackerrank 
	  public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        topView(root);
    }	
}  
