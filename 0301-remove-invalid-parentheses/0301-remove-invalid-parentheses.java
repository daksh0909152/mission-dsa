class Solution {

    public List<String> removeInvalidParentheses(String s) {

        List<String> ans = new ArrayList<>();

        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        q.offer(s);
        visited.add(s);

        boolean found = false;

        while (!q.isEmpty()) {

            int size = q.size();

            while (size-- > 0) {

                String current = q.poll();

                if (isValid(current)) {
                    ans.add(current);
                    found = true;
                }

                // Agar current level par valid mil gaya,
                // toh next level ki zarurat nahi.
                if (found) {
                    continue;
                }

                for (int i = 0; i < current.length(); i++) {

                    // Sirf parentheses remove karne hain
                    if (current.charAt(i) != '(' &&
                        current.charAt(i) != ')') {
                        continue;
                    }

                    String next =
                        current.substring(0, i) +
                        current.substring(i + 1);

                    if (!visited.contains(next)) {
                        visited.add(next);
                        q.offer(next);
                    }
                }
            }

            if (found) {
                break;
            }
        }

        return ans;
    }

    private boolean isValid(String s) {

        int balance = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                balance++;
            } 
            else if (c == ')') {
                balance--;

                // Closing bracket without opening bracket
                if (balance < 0) {
                    return false;
                }
            }
        }

        return balance == 0;
    }
}