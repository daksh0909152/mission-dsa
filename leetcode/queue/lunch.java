class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        int[] count = new int[2];
        // Count preferences
        for (int s : students) {
            count[s]++;
        }
        // Process each sandwich
        for (int sand : sandwiches) {
            if (count[sand] == 0) {
                // No student wants this type -> stuck here
                return count[sand ^ 1];  
            }
            // Serve sandwich
            count[sand]--;
        }
        // All ate
        return 0;
    }
}
