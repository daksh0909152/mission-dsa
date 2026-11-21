
import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] children = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        int[] newParent = parent.clone();
        int[] last = new int[26];
        int[] saved = new int[n];

        Arrays.fill(last, -1);

        // DFS with entry and exit events
        int[] stack = new int[2 * n];
        int top = 0;

        stack[top++] = 0;

        while (top > 0) {
            int event = stack[--top];

            if (event < 0) {
                int node = ~event;
                int ch = s.charAt(node) - 'a';

                last[ch] = saved[node];
            } else {
                int node = event;
                int ch = s.charAt(node) - 'a';

                saved[node] = last[ch];

                if (last[ch] != -1) {
                    newParent[node] = last[ch];
                }

                last[ch] = node;

                stack[top++] = ~node;

                for (int i = children[node].size() - 1; i >= 0; i--) {
                    stack[top++] = children[node].get(i);
                }
            }
        }

        // Build modified tree
        List<Integer>[] newTree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            newTree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            newTree[newParent[i]].add(i);
        }

        // Iterative DFS order
        int[] order = new int[n];
        top = 0;
        int count = 0;

        stack = new int[n];
        stack[top++] = 0;

        while (top > 0) {
            int node = stack[--top];
            order[count++] = node;

            for (int child : newTree[node]) {
                stack[top++] = child;
            }
        }

        // Calculate subtree sizes
        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        for (int i = count - 1; i > 0; i--) {
            int node = order[i];
            ans[newParent[node]] += ans[node];
        }

        return ans;
    }
}
