class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            map.computeIfAbsent(seat[0], k -> new HashSet<>()).add(seat[1]);
        }

        // Rows with no reserved seats
        int ans = (n - map.size()) * 2;

        for (HashSet<Integer> seats : map.values()) {

            boolean left = true;
            boolean middle = true;
            boolean right = true;

            // Left: 2,3,4,5
            for (int i = 2; i <= 5; i++) {
                if (seats.contains(i)) {
                    left = false;
                    break;
                }
            }

            // Middle: 4,5,6,7
            for (int i = 4; i <= 7; i++) {
                if (seats.contains(i)) {
                    middle = false;
                    break;
                }
            }

            // Right: 6,7,8,9
            for (int i = 6; i <= 9; i++) {
                if (seats.contains(i)) {
                    right = false;
                    break;
                }
            }

            if (left && right) {
                ans += 2;
            } else if (left || middle || right) {
                ans += 1;
            }
        }

        return ans;
    }
}