class Solution {
    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;
        int INF = Integer.MAX_VALUE / 2;

        int[] best = new int[n];

        for (int i = 0; i < n; i++) {
            best[i] = INF;
        }

        int left = 0;
        int sum = 0;
        int ans = INF;

        for (int right = 0; right < n; right++) {

            sum += arr[right];

            // Sum target se bada hai -> left ko move karo
            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            // Current window ka sum target hai
            if (sum == target) {

                int currentLength = right - left + 1;

                // Left side mein pehle koi valid subarray hai
                if (left > 0 && best[left - 1] != INF) {
                    ans = Math.min(
                        ans,
                        best[left - 1] + currentLength
                    );
                }

                // Current subarray ko best mein store karo
                if (left == 0) {
                    best[right] = currentLength;
                } else {
                    best[right] = Math.min(
                        best[right - 1],
                        currentLength
                    );
                }

            } else {

                // Koi current valid subarray nahi
                if (right > 0) {
                    best[right] = best[right - 1];
                }
            }
        }

        return ans == INF ? -1 : ans;
    }
}