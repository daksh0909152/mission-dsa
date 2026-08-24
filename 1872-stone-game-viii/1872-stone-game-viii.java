class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;

        // Prefix sums
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }

        // Start from last prefix sum
        int dp = stones[n - 1];

        for (int i = n - 2; i >= 1; i--) {
            dp = Math.max(dp, stones[i] - dp);
        }

        return dp;
    }
}