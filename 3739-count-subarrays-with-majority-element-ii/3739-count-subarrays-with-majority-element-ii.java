class Solution {

    class Fenwick {
        int[] bit;
        int n;

        Fenwick(int n) {
            this.n = n;
            bit = new int[n + 1];
        }

        void update(int idx, int val) {
            while (idx <= n) {
                bit[idx] += val;
                idx += idx & -idx;
            }
        }

        int query(int idx) {
            int sum = 0;
            while (idx > 0) {
                sum += bit[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        Fenwick ft = new Fenwick(2 * n + 3);

        int prefix = n + 1; // shift to avoid negative indices
        ft.update(prefix, 1);

        long ans = 0;

        for (int num : nums) {
            prefix += (num == target) ? 1 : -1;

            // count previous prefix sums < current prefix
            ans += ft.query(prefix - 1);

            ft.update(prefix, 1);
        }

        return ans;
    }
}