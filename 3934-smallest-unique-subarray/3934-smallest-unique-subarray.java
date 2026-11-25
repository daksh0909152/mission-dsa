import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        long base1 = 100019;
        long base2 = 100043;

        long mod1 = 1000000007;
        long mod2 = 1000000009;

        int low = 1;
        int high = n;
        int answer = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (check(nums, mid, base1, base2, mod1, mod2)) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return answer;
    }

    private boolean check(
        int[] nums,
        int len,
        long base1,
        long base2,
        long mod1,
        long mod2
    ) {
        int n = nums.length;

        long power1 = 1;
        long power2 = 1;

        for (int i = 0; i < len; i++) {
            power1 = (power1 * base1) % mod1;
            power2 = (power2 * base2) % mod2;
        }

        long hash1 = 0;
        long hash2 = 0;

        for (int i = 0; i < len; i++) {
            hash1 = (hash1 * base1 + nums[i]) % mod1;
            hash2 = (hash2 * base2 + nums[i]) % mod2;
        }

        HashMap<Long, Integer> map = new HashMap<>();

        long key = (hash1 << 32) | hash2;
        map.put(key, 1);

        for (int i = len; i < n; i++) {
            long remove1 = (nums[i - len] * power1) % mod1;
            long remove2 = (nums[i - len] * power2) % mod2;

            hash1 = (hash1 * base1 + nums[i] - remove1 + mod1) % mod1;
            hash2 = (hash2 * base2 + nums[i] - remove2 + mod2) % mod2;

            key = (hash1 << 32) | hash2;

            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        for (int count : map.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}