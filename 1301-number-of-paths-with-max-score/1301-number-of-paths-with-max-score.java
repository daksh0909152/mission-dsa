class Solution {

    int MOD = 1000000007;

    public int[] pathsWithMaxScore(List<String> board) {

        int n = board.size();

        int[][] score = new int[n][n];
        int[][] ways = new int[n][n];

        for (int[] row : score)
            Arrays.fill(row, -1);

        score[n - 1][n - 1] = 0;
        ways[n - 1][n - 1] = 1;

        for (int i = n - 1; i >= 0; i--) {

            for (int j = n - 1; j >= 0; j--) {

                if (board.get(i).charAt(j) == 'X')
                    continue;

                update(score, ways, i, j, i + 1, j, n);

                update(score, ways, i, j, i, j + 1, n);

                update(score, ways, i, j, i + 1, j + 1, n);

                if (score[i][j] != -1) {

                    char ch = board.get(i).charAt(j);

                    if (Character.isDigit(ch))
                        score[i][j] += ch - '0';
                }
            }
        }

        if (score[0][0] == -1)
            return new int[]{0, 0};

        return new int[]{score[0][0], ways[0][0]};
    }

    private void update(int[][] score, int[][] ways,
                        int i, int j,
                        int x, int y,
                        int n) {

        if (x >= n || y >= n)
            return;

        if (score[x][y] == -1)
            return;

        if (score[x][y] > score[i][j]) {

            score[i][j] = score[x][y];
            ways[i][j] = ways[x][y];

        } else if (score[x][y] == score[i][j]) {

            ways[i][j] = (ways[i][j] + ways[x][y]) % MOD;
        }
    }
}