
class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > num * 9) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        while (num > 0) {
            int digit = Math.min(9, sum);
            ans.append(digit);
            sum -= digit;
            num--;
        }

        return ans.toString();
    }
}
