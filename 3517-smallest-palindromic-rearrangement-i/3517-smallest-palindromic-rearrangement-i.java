class Solution {
    public String smallestPalindrome(String s) {
        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        StringBuilder left = new StringBuilder();
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            int half = cnt[i] / 2;

            while (half-- > 0) {
                left.append((char) ('a' + i));
            }

            if ((cnt[i] & 1) == 1) {
                mid = (char) ('a' + i);
            }
        }

        StringBuilder ans = new StringBuilder();
        ans.append(left);

        if (mid != 0) {
            ans.append(mid);
        }

        ans.append(new StringBuilder(left).reverse());

        return ans.toString();
    }
}