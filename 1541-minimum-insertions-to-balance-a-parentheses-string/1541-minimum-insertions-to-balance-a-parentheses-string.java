
class Solution {
    public int minInsertions(String s) {
        int ans = 0;
        int open = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++; // Pair mil gaya: ))
                } else {
                    ans++; // Ek ) insert karo
                }

                if (open == 0) {
                    ans++; // Missing (
                } else {
                    open--;
                }
            }
        }

        ans += open * 2;
        return ans;
    }
}