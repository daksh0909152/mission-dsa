
import java.util.Arrays;

class Solution {
    public int minimizeSum(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;

        if (n <= 3) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;

        // Change two smallest elements
        ans = Math.min(ans, nums[n - 1] - nums[2]);

        // Change one smallest and one largest
        ans = Math.min(ans, nums[n - 2] - nums[1]);

        // Change two largest elements
        ans = Math.min(ans, nums[n - 3] - nums[0]);

        return ans;
    }
}
