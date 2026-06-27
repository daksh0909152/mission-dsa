class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> freq = new HashMap<>();

        for (int num : nums) {
            freq.put((long) num, freq.getOrDefault((long) num, 0) + 1);
        }

        int ans = 1;

        // Special case for 1
        if (freq.containsKey(1L)) {
            int count = freq.get(1L);
            ans = Math.max(ans, (count % 2 == 0) ? count - 1 : count);
        }

        for (long num : freq.keySet()) {
            if (num == 1) continue;

            long cur = num;
            int len = 0;

            while (freq.getOrDefault(cur, 0) >= 2) {
                len += 2;
                if (cur > 1_000_000_000L) break; // avoid overflow
                cur = cur * cur;
            }

            if (freq.getOrDefault(cur, 0) == 1) {
                len += 1;
            } else {
                len -= 1; // keep length odd
            }

            ans = Math.max(ans, len);
        }

        return ans;
    }
}