import java.util.*;

class Solution {

    static class Interval {
        int l, r, weight, index;

        Interval(int l, int r, int weight, int index) {
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.index = index;
        }
    }

    static class Result {
        long weight;
        List<Integer> indices;

        Result(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = indices;
        }
    }

    Interval[] arr;
    Result[][] dp;

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            );
        }

        // Sort by starting point
        Arrays.sort(arr, (a, b) -> {
            if (a.l != b.l)
                return Integer.compare(a.l, b.l);

            if (a.r != b.r)
                return Integer.compare(a.r, b.r);

            return Integer.compare(a.index, b.index);
        });

        dp = new Result[n][5];

        Result ans = solve(0, 4);

        int[] result = new int[ans.indices.size()];

        for (int i = 0; i < ans.indices.size(); i++) {
            result[i] = ans.indices.get(i);
        }

        return result;
    }

    private Result solve(int i, int remaining) {

        if (i == arr.length || remaining == 0) {
            return new Result(0, new ArrayList<>());
        }

        if (dp[i][remaining] != null) {
            return dp[i][remaining];
        }

        // Option 1: Skip current interval
        Result skip = solve(i + 1, remaining);

        // Option 2: Take current interval
        int next = findNext(i);

        Result nextResult = solve(next, remaining - 1);

        List<Integer> takeIndices =
            new ArrayList<>(nextResult.indices);

        takeIndices.add(arr[i].index);

        Collections.sort(takeIndices);

        Result take = new Result(
            arr[i].weight + nextResult.weight,
            takeIndices
        );

        // Decide better result
        if (take.weight > skip.weight) {
            dp[i][remaining] = take;
        }
        else if (take.weight < skip.weight) {
            dp[i][remaining] = skip;
        }
        else {
            // Same weight → lexicographically smaller
            if (compare(take.indices, skip.indices) < 0) {
                dp[i][remaining] = take;
            } else {
                dp[i][remaining] = skip;
            }
        }

        return dp[i][remaining];
    }

    // Find first interval whose start > current end
    private int findNext(int i) {

        int target = arr[i].r;

        int low = i + 1;
        int high = arr.length;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (arr[mid].l > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    // Lexicographical comparison
    private int compare(List<Integer> a, List<Integer> b) {

        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return Integer.compare(a.get(i), b.get(i));
            }
        }

        return Integer.compare(a.size(), b.size());
    }
}