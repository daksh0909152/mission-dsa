class Solution {
    public int minOperations(int[] nums, int x) {

        int total = 0;

        // Find total sum
        for (int num : nums) {
            total += num;
        }

        int target = total - x;

        // If target is negative,
        // it is impossible
        if (target < 0) {
            return -1;
        }

        // If target = 0,
        // we have to remove every element
        if (target == 0) {
            return nums.length;
        }

        int left = 0;
        int sum = 0;
        int maxLen = -1;

        for (int right = 0; right < nums.length; right++) {

            sum += nums[right];

            // Reduce window if sum becomes too large
            while (sum > target) {
                sum -= nums[left];
                left++;
            }

            // Found a valid subarray
            if (sum == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

        // No valid subarray
        if (maxLen == -1) {
            return -1;
        }

        // Elements outside the longest subarray
        // are the ones we remove
        return nums.length - maxLen;
    }
}