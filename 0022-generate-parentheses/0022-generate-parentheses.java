class Solution {

    List<String> ans = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        backtrack("", 0, 0, n);
        return ans;
    }

    void backtrack(String str, int open, int close, int n) {

        // Complete valid combination
        if (open == n && close == n) {
            ans.add(str);
            return;
        }

        // Add '('
        if (open < n) {
            backtrack(str + "(", open + 1, close, n);
        }

        // Add ')'
        if (close < open) {
            backtrack(str + ")", open, close + 1, n);
        }
    }
}