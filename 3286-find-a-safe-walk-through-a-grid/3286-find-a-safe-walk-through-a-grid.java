class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();

        int[][] best = new int[m][n];
        for (int[] row : best) {
            Arrays.fill(row, -1);
        }

        int startHealth = health - grid.get(0).get(0);

        if (startHealth <= 0)
            return false;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, startHealth});
        best[0][0] = startHealth;

        int[][] dir = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            int r = curr[0];
            int c = curr[1];
            int h = curr[2];

            if (r == m - 1 && c == n - 1)
                return true;

            for (int[] d : dir) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr < 0 || nc < 0 || nr >= m || nc >= n)
                    continue;

                int nh = h - grid.get(nr).get(nc);

                if (nh <= 0)
                    continue;

                if (nh > best[nr][nc]) {
                    best[nr][nc] = nh;
                    queue.offer(new int[]{nr, nc, nh});
                }
            }
        }

        return false;
    }
}