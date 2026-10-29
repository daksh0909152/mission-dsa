import java.util.*;

class Solution {

    int n;
    List<Integer>[] adj;
    int[] parent, depth, size, heavy;
    int[] head, pos;
    int timer;
    SegTree seg;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        this.n = n;

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        parent = new int[n];
        depth = new int[n];
        size = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];

        Arrays.fill(parent, -1);
        Arrays.fill(heavy, -1);

        // Build parent and traversal order iteratively
        int[] order = new int[n];
        int[] stack = new int[n];
        int top = 0, count = 0;

        stack[top++] = 0;
        parent[0] = 0;

        while (top > 0) {
            int u = stack[--top];
            order[count++] = u;

            for (int v : adj[u]) {
                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        // Calculate subtree sizes and heavy child
        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            size[u] = 1;
            int maxSize = 0;

            for (int v : adj[u]) {
                if (parent[v] != u) continue;

                size[u] += size[v];

                if (size[v] > maxSize) {
                    maxSize = size[v];
                    heavy[u] = v;
                }
            }
        }

        // Heavy Light Decomposition
        int[] chainNodes = new int[n];
        int[] chainHeads = new int[n];
        int chains = 0;

        chainNodes[0] = 0;
        chainHeads[0] = 0;
        chains = 1;

        timer = 0;

        while (chains > 0) {
            int u = chainNodes[--chains];
            int h = chainHeads[chains];

            while (u != -1) {
                head[u] = h;
                pos[u] = timer++;

                for (int v : adj[u]) {
                    if (parent[v] == u && v != heavy[u]) {
                        chainNodes[chains] = v;
                        chainHeads[chains++] = v;
                    }
                }

                u = heavy[u];
            }
        }

        // Segment Tree initialization
        seg = new SegTree(n);
        char[] chars = s.toCharArray();

        for (int i = 0; i < n; i++) {
            seg.update(pos[i], 1 << (chars[i] - 'a'));
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");

            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char c = p[2].charAt(0);

                chars[u] = c;
                seg.update(pos[u], 1 << (c - 'a'));

            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);

                int mask = 0;

                while (head[u] != head[v]) {
                    if (depth[head[u]] < depth[head[v]]) {
                        int temp = u;
                        u = v;
                        v = temp;
                    }

                    mask ^= seg.query(
                        pos[head[u]], pos[u]
                    );

                    u = parent[head[u]];
                }

                if (depth[u] > depth[v]) {
                    int temp = u;
                    u = v;
                    v = temp;
                }

                mask ^= seg.query(pos[u], pos[v]);

                // At most one odd-frequency character
                ans.add((mask & (mask - 1)) == 0);
            }
        }

        return ans;
    }

    class SegTree {
        int[] tree;
        int n;

        SegTree(int n) {
            this.n = n;
            tree = new int[4 * n];
        }

        void update(int idx, int value) {
            update(1, 0, n - 1, idx, value);
        }

        void update(int node, int l, int r,
                    int idx, int value) {

            if (l == r) {
                tree[node] = value;
                return;
            }

            int mid = (l + r) / 2;

            if (idx <= mid)
                update(node * 2, l, mid, idx, value);
            else
                update(node * 2 + 1, mid + 1, r, idx, value);

            tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
        }

        int query(int ql, int qr) {
            return query(1, 0, n - 1, ql, qr);
        }

        int query(int node, int l, int r,
                  int ql, int qr) {

            if (ql <= l && r <= qr)
                return tree[node];

            if (r < ql || l > qr)
                return 0;

            int mid = (l + r) / 2;

            return query(node * 2, l, mid, ql, qr)
                 ^ query(node * 2 + 1, mid + 1, r, ql, qr);
        }
    }
}