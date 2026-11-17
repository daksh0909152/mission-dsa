
class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();

        int[][] cost = new int[n][n];

        // Calculate cost of every substring
        for (int len = 2; len <= n; len++) {
            for (int l = 0; l + len <= n; l++) {
                int r = l + len - 1;
                int ans = Integer.MAX_VALUE;

                // Try every proper divisor
                for (int d = 1; d < len; d++) {
                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;

                    // Divide characters into d groups
                    for (int start = 0; start < d; start++) {
                        int count = (len - 1 - start) / d + 1;

                        // Check palindrome in each group
                        for (int t = 0; t < count / 2; t++) {
                            int left = l + start + t * d;
                            int right = l + start + (count - 1 - t) * d;

                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }
                        }
                    }

                    ans = Math.min(ans, changes);
                }

                cost[l][r] = ans;
            }
        }

        // DP
        int INF = 1000000;
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            java.util.Arrays.fill(dp[i], INF);
        }

        dp[0][0] = 0;

        for (int i = 2; i <= n; i++) {
            for (int parts = 1; parts <= k; parts++) {

                // Last substring length must be at least 2
                for (int j = 2; j <= i; j++) {
                    if (dp[i - j][parts - 1] == INF) {
                        continue;
                    }

                    dp[i][parts] = Math.min(
                        dp[i][parts],
                        dp[i - j][parts - 1] + cost[i - j][i - 1]
                    );
                }
            }
        }

        return dp[n][k];
    }
}
