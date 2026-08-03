class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;

        // dp[i] = maximum score difference Alice can achieve
        // starting from index i
        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Integer.MIN_VALUE;
            int sum = 0;

            // Take 1, 2, or 3 stones
            for (int j = i; j < Math.min(n, i + 3); j++) {
                sum += stoneValue[j];

                dp[i] = Math.max(dp[i], sum - dp[j + 1]);
            }
        }

        if (dp[0] > 0)
            return "Alice";
        else if (dp[0] < 0)
            return "Bob";
        else
            return "Tie";
    }
}