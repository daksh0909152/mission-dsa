class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];
        for (char ch : s.toCharArray()) cnt[ch - 'a']++;

        int oddCount = 0, oddChar = -1;
        for (int c = 0; c < 26; c++) {
            if (cnt[c] % 2 != 0) {
                oddCount++;
                oddChar = c;
            }
        }

        Integer midChar;
        if (n % 2 == 0) {
            if (oddCount != 0) return "";
            midChar = null;
        } else {
            if (oddCount != 1) return "";
            midChar = oddChar;
        }

        int half = n / 2;
        int[] halfCounts = new int[26];
        for (int c = 0; c < 26; c++) halfCounts[c] = cnt[c] / 2;

        String head = target.substring(0, half);

        // Option 1: use target's head exactly, if achievable
        int[] headCounts = new int[26];
        for (char ch : head.toCharArray()) headCounts[ch - 'a']++;

        if (Arrays.equals(headCounts, halfCounts)) {
            StringBuilder tail = new StringBuilder(head).reverse();
            StringBuilder candidate = new StringBuilder(head);
            if (midChar != null) candidate.append((char) (midChar + 'a'));
            candidate.append(tail);
            String candStr = candidate.toString();
            if (candStr.compareTo(target) > 0) {
                return candStr;
            }
        }

        // Option 2: smallest H strictly greater than head
        String H = latestGreaterPermutation(halfCounts, head);
        if (H == null) return "";

        StringBuilder result = new StringBuilder(H);
        if (midChar != null) result.append((char) (midChar + 'a'));
        result.append(new StringBuilder(H).reverse());
        return result.toString();
    }

    private String latestGreaterPermutation(int[] halfCounts, String targetHead) {
        int n = targetHead.length();
        int[] cnt = halfCounts.clone();

        int bestI = -1, bestChar = -1;
        int[] bestSnapshot = null;

        for (int i = 0; i < n; i++) {
            int tc = targetHead.charAt(i) - 'a';

            for (int c = tc + 1; c < 26; c++) {
                if (cnt[c] > 0) {
                    bestI = i;
                    bestChar = c;
                    bestSnapshot = cnt.clone();
                    break;
                }
            }

            if (cnt[tc] > 0) {
                cnt[tc]--;
            } else {
                break;
            }
        }

        if (bestI == -1) return null;

        int[] cnt2 = bestSnapshot.clone();
        cnt2[bestChar]--;

        StringBuilder result = new StringBuilder();
        result.append(targetHead, 0, bestI);
        result.append((char) (bestChar + 'a'));
        for (int c = 0; c < 26; c++) {
            for (int k = 0; k < cnt2[c]; k++) {
                result.append((char) (c + 'a'));
            }
        }
        return result.toString();
    }
}