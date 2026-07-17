class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        int[] freq = new int[max + 1];
        for (int x : nums) freq[x]++;

        long[] gcdCount = new long[max + 1];

        // Count pairs having gcd exactly i
        for (int i = max; i >= 1; i--) {
            int cnt = 0;

            for (int j = i; j <= max; j += i) {
                cnt += freq[j];
                gcdCount[i] -= gcdCount[j];
            }

            gcdCount[i] += 1L * cnt * (cnt - 1) / 2;
        }

        // Prefix sums
        for (int i = 2; i <= max; i++) {
            gcdCount[i] += gcdCount[i - 1];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            ans[i] = upperBound(gcdCount, queries[i]);
        }

        return ans;
    }

    private int upperBound(long[] arr, long target) {
        int l = 1;
        int r = arr.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (arr[mid] > target)
                r = mid;
            else
                l = mid + 1;
        }

        return l;
    }
}