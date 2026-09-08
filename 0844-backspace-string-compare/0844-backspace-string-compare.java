import java.util.*;

class Solution {
    
    public boolean backspaceCompare(String s, String t) {
        
        return build(s).equals(build(t));
    }
    
    public String build(String str) {
        
        Stack<Character> stack = new Stack<>();
        
        for (char ch : str.toCharArray()) {
            
            if (ch == '#') {
                
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                
            } else {
                stack.push(ch);
            }
        }
        
        StringBuilder result = new StringBuilder();
        
        for (char ch : stack) {
            result.append(ch);
        }
        
        return result.toString();
    }
}