import java.util.*;

class Solution {
    static final int LOG = 18;

    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[arr[i][1]] = i;
        }

        int[][] up = new int[LOG][n];

        int j = 0;
        for (int i = 0; i < n; i++) {
            while (j + 1 < n && arr[j + 1][0] - arr[i][0] <= maxDiff) {
                j++;
            }
            up[0][i] = j;
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];

        for (int idx = 0; idx < queries.length; idx++) {
            int u = pos[queries[idx][0]];
            int v = pos[queries[idx][1]];

            if (u == v) {
                ans[idx] = 0;
                continue;
            }

            if (u > v) {
                int t = u;
                u = v;
                v = t;
            }

            if (up[LOG - 1][u] < v) {
                ans[idx] = -1;
                continue;
            }

            int cur = u;
            int steps = 0;

            for (int k = LOG - 1; k >= 0; k--) {
                if (up[k][cur] < v) {
                    steps += 1 << k;
                    cur = up[k][cur];
                }
            }

            ans[idx] = steps + 1;
        }

        return ans;
    }
}