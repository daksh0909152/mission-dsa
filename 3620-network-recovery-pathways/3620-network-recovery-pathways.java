import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;

        List<int[]>[] graph = new ArrayList[n];
        int[] indegree = new int[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        int maxCost = 0;
        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            indegree[e[1]]++;
            maxCost = Math.max(maxCost, e[2]);
        }

        // Topological Order
        int[] topo = new int[n];
        Queue<Integer> q = new ArrayDeque<>();
        int idx = 0;

        int[] deg = indegree.clone();
        for (int i = 0; i < n; i++) {
            if (deg[i] == 0) q.offer(i);
        }

        while (!q.isEmpty()) {
            int u = q.poll();
            topo[idx++] = u;
            for (int[] edge : graph[u]) {
                if (--deg[edge[0]] == 0) {
                    q.offer(edge[0]);
                }
            }
        }

        int left = 0, right = maxCost;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (check(mid, graph, topo, online, k)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    private boolean check(int limit, List<int[]>[] graph, int[] topo,
                          boolean[] online, long k) {

        int n = graph.length;
        long INF = Long.MAX_VALUE / 4;

        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[0] = 0;

        for (int u : topo) {
            if (dist[u] == INF) continue;

            if (u != 0 && u != n - 1 && !online[u]) continue;

            for (int[] edge : graph[u]) {
                int v = edge[0];
                int w = edge[1];

                if (w < limit) continue;
                if (v != n - 1 && !online[v]) continue;

                dist[v] = Math.min(dist[v], dist[u] + w);
            }
        }

        return dist[n - 1] <= k;
    }
}