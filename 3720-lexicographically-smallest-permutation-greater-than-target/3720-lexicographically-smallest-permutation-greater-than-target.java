class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];
        for (char ch : s.toCharArray()) cnt[ch - 'a']++;

        int fallbackPos = -1;
        char fallbackChar = 0;
        int[] fallbackCnt = null;

        int i = 0;
        for (; i < n; i++) {
            int tc = target.charAt(i) - 'a';

            // Is there a letter strictly greater than target[i] available right now?
            int g = -1;
            for (int c = tc + 1; c < 26; c++) {
                if (cnt[c] > 0) { g = c; break; }
            }
            if (g != -1) {
                fallbackPos = i;
                fallbackChar = (char) ('a' + g);
                fallbackCnt = cnt.clone();
            }

            if (cnt[tc] > 0) {
                cnt[tc]--;          // keep matching target exactly
            } else {
                break;               // can't match further, must use a fallback
            }
        }

        if (fallbackPos == -1) return ""; // no branch point ever existed

        StringBuilder sb = new StringBuilder();
        sb.append(target, 0, fallbackPos);   // exact prefix matching target
        sb.append(fallbackChar);             // first strictly greater letter here

        int[] rem = fallbackCnt;
        rem[fallbackChar - 'a']--;

        for (int c = 0; c < 26; c++) {        // smallest possible suffix
            for (int k = 0; k < rem[c]; k++) {
                sb.append((char) ('a' + c));
            }
        }

        return sb.toString();
    }
}