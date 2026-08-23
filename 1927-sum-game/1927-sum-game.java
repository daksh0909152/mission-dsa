class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int leftSum = 0, rightSum = 0;
        int leftQ = 0, rightQ = 0;

        for (int i = 0; i < n; i++) {
            if (num.charAt(i) == '?') {
                if (i < n / 2) leftQ++;
                else rightQ++;
            } else {
                if (i < n / 2)
                    leftSum += num.charAt(i) - '0';
                else
                    rightSum += num.charAt(i) - '0';
            }
        }

        // Odd number of '?' => Alice can always win
        if ((leftQ + rightQ) % 2 == 1)
            return true;

        // Bob can make sums equal only in this exact case
        return leftSum - rightSum != (rightQ - leftQ) * 9 / 2;
    }
}