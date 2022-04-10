class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for(int i = 0; i < nums.length; i++) {
            int num = nums[i];
            List<List<Integer>> tempList = new ArrayList<>();
            for(int j = 0; j < result.size(); j++) {
                for(int k = 0; k <= i; k++) {
                    List<Integer> temp = new ArrayList<>(result.get(j));
                    temp.add(k, nums[i]);
                    tempList.add(temp);
                }
            }
            result.clear();
            result.addAll(tempList);
        }
        return result;
    }
}
