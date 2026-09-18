class Solution {

    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        // Find first and last occurrence
        for (int i = 0; i < n; i++) {

            int index = s.charAt(i) - 'a';

            first[index] = Math.min(first[index], i);
            last[index] = i;
        }

        List<String> ans = new ArrayList<>();

        int prevEnd = -1;

        // Try every character's first occurrence
        for (int left = 0; left < n; left++) {

            int ch = s.charAt(left) - 'a';

            // Only start from first occurrence
            if (first[ch] != left) {
                continue;
            }

            int right = last[ch];

            boolean valid = true;

            // Expand the interval
            for (int j = left; j <= right; j++) {

                int curr = s.charAt(j) - 'a';

                // Character occurred before left
                if (first[curr] < left) {
                    valid = false;
                    break;
                }

                // Need to include all occurrences
                right = Math.max(right, last[curr]);
            }

            if (!valid) {
                continue;
            }

            // Greedy
            if (left > prevEnd) {

                ans.add(s.substring(left, right + 1));
                prevEnd = right;

            } else {

                // Current interval is smaller/better
                ans.set(ans.size() - 1,
                        s.substring(left, right + 1));

                prevEnd = right;
            }
        }

        return ans;
    }
}