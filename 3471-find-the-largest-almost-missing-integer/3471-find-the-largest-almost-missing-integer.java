import java.util.*;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> count = new HashMap<>();

        // Har window of size k me number ko count karo
        for (int i = 0; i <= n - k; i++) {
            HashSet<Integer> set = new HashSet<>();

            for (int j = i; j < i + k; j++) {
                set.add(nums[j]);
            }

            for (int num : set) {
                count.put(num, count.getOrDefault(num, 0) + 1);
            }
        }

        int ans = -1;

        // Jo number exactly 1 window me aaya,
        // wahi almost missing hai
        for (int num : count.keySet()) {
            if (count.get(num) == 1) {
                ans = Math.max(ans, num);
            }
        }

        return ans;
    }
}