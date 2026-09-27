class Solution {
    public String reverseParentheses(String s) {

        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {

            if (ch == ')') {

                StringBuilder temp = new StringBuilder();

                // '(' tak characters nikalo
                while (stack.peek() != '(') {
                    temp.append(stack.pop());
                }

                // '(' remove karo
                stack.pop();

                // reversed string wapas stack mein
                for (char c : temp.toString().toCharArray()) {
                    stack.push(c);
                }

            } else {
                stack.push(ch);
            }
        }

        StringBuilder ans = new StringBuilder();

        while (!stack.isEmpty()) {
            ans.append(stack.pop());
        }

        return ans.reverse().toString();
    }
}