import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {

        int n = classroom.length;
        int m = classroom[0].length();

        int sr = -1, sc = -1;
        List<int[]> litter = new ArrayList<>();

        // Find start and litter
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    sr = i;
                    sc = j;
                } else if (ch == 'L') {
                    litter.add(new int[]{i, j});
                }
            }
        }

        int k = litter.size();

        if (k == 0) {
            return 0;
        }

        // Assign bit to every litter
        int[][] index = new int[n][m];

        for (int[] row : index) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < k; i++) {
            int r = litter.get(i)[0];
            int c = litter.get(i)[1];
            index[r][c] = i;
        }

        int fullMask = (1 << k) - 1;

        // visited[row][col][energy][mask]
        boolean[][][][] visited =
            new boolean[n][m][energy + 1][1 << k];

        Queue<State> q = new LinkedList<>();

        q.offer(new State(sr, sc, energy, 0));
        visited[sr][sc][energy][0] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int moves = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            while (size-- > 0) {

                State cur = q.poll();

                int r = cur.r;
                int c = cur.c;
                int e = cur.energy;
                int mask = cur.mask;

                // All litter collected
                if (mask == fullMask) {
                    return moves;
                }

                // No energy
                if (e == 0) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {

                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    // Outside grid
                    if (nr < 0 || nr >= n ||
                        nc < 0 || nc >= m) {
                        continue;
                    }

                    // Obstacle
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    int newEnergy = e - 1;
                    int newMask = mask;

                    char cell = classroom[nr].charAt(nc);

                    // Collect litter
                    if (cell == 'L') {
                        int idx = index[nr][nc];
                        newMask |= (1 << idx);
                    }

                    // Recharge
                    if (cell == 'R') {
                        newEnergy = energy;
                    }

                    if (!visited[nr][nc][newEnergy][newMask]) {

                        visited[nr][nc][newEnergy][newMask] = true;

                        q.offer(
                            new State(
                                nr,
                                nc,
                                newEnergy,
                                newMask
                            )
                        );
                    }
                }
            }

            moves++;
        }

        return -1;
    }

    static class State {

        int r;
        int c;
        int energy;
        int mask;

        State(int r, int c, int energy, int mask) {
            this.r = r;
            this.c = c;
            this.energy = energy;
            this.mask = mask;
        }
    }
}