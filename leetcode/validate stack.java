import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new ArrayDeque<>();
        int popIndex = 0;
        
        for (int x : pushed) {
            stack.push(x);
            // try to pop as many as possible in correct order
            while (!stack.isEmpty() && stack.peek() == popped[popIndex]) {
                stack.pop();
                popIndex++;
                // if we've matched all popped elements, can stop early
                if (popIndex == popped.length) {
                    break;
                }
            }
        }
        
        // If we've matched all popped items, then sequence is valid
        return popIndex == popped.length;
    }
}
