class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;

        // Find the smallest odd number
        for (int num : nums1) {
            if (num % 2 != 0) {
                minOdd = Math.min(minOdd, num);
            }
        }

        // Check if any even number is smaller than the minimum odd
        for (int num : nums1) {
            if (num % 2 == 0 && minOdd != Integer.MAX_VALUE && num < minOdd) {
                return false;
            }
        }

        return true;
    }
}