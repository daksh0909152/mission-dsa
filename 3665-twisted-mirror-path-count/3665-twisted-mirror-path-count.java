
class Solution {
    public int uniquePaths(int[][] grid) {

        int MOD = 1_000_000_007;

        int m = grid.length;
        int n = grid[0].length;

        int[] dp = new int[n];

        dp[0] = 1;

        int res = 0;

        for (int i = 0; i < m; i++) {

            res = 0;

            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1) {

                    int temp = res;
                    res = dp[j];
                    dp[j] = temp;

                } else {

                    int total = (res + dp[j]) % MOD;

                    res = total;
                    dp[j] = total;
                }
            }
        }

        return res;
    }
}
