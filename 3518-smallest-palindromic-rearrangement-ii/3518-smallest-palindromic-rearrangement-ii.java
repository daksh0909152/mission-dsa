class Solution {
    private static final long CAP = 1_000_001L; // k <= 10^6, so anything past this is "enough"

    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        // Build half-frequency array + find middle char (if odd length)
        int[] half = new int[26];
        char mid = 0;
        boolean hasMid = false;
        int halfLen = 0;
        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            halfLen += half[i];
            if (freq[i] % 2 == 1) {
                mid = (char) ('a' + i);
                hasMid = true;
            }
        }

        long total = countArrangements(half);
        if (k > total) return "";

        // Greedily build the smallest half, skipping blocks of size = countArrangements
        StringBuilder left = new StringBuilder();
        long remaining = k;
        for (int pos = 0; pos < halfLen; pos++) {
            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) continue;
                half[c]--;
                long ways = countArrangements(half);
                if (ways >= remaining) {
                    left.append((char) ('a' + c));
                    break;
                } else {
                    remaining -= ways;
                    half[c]++;
                }
            }
        }

        StringBuilder result = new StringBuilder(left);
        if (hasMid) result.append(mid);
        result.append(left.reverse());
        return result.toString();
    }

    // Number of distinct permutations of the multiset described by count[],
    // capped at CAP to avoid overflow (k is at most 10^6).
    private long countArrangements(int[] count) {
        int total = 0;
        for (int f : count) total += f;

        long res = 1;
        for (int f : count) {
            res *= nCk(total, f);
            if (res >= CAP) return CAP;
            total -= f;
        }
        return res;
    }

    private long nCk(int n, int k) {
        k = Math.min(k, n - k);
        long res = 1;
        for (int i = 1; i <= k; i++) {
            res = res * (n - i + 1) / i;
            if (res >= CAP) return CAP;
        }
        return res;
    }
}