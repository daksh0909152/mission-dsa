
class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] count = new int[31];
        long mod = 1_000_000_007L;

        // Count set bits at each position
        for (int num : nums) {
            for (int bit = 0; bit < 31; bit++) {
                if (((num >> bit) & 1) == 1) {
                    count[bit]++;
                }
            }
        }

        long ans = 0;

        // Build k maximum numbers greedily
        for (int i = 0; i < k; i++) {
            long num = 0;

            for (int bit = 0; bit < 31; bit++) {
                if (count[bit] > 0) {
                    num |= (1L << bit);
                    count[bit]--;
                }
            }

            ans = (ans + num * num) % mod;
        }

        return (int) ans;
    }
}
