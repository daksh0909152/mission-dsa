
import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        int[] count = new int[k];

        TreeSet<Integer> free = new TreeSet<>();

        // Stores {finishTime, serverId}
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        // Initially all servers are free
        for (int i = 0; i < k; i++) {
            free.add(i);
        }

        for (int i = 0; i < arrival.length; i++) {

            int time = arrival[i];

            // Release servers whose work is completed
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                long[] server = busy.poll();
                free.add((int) server[1]);
            }

            // If all servers are busy, drop request
            if (free.isEmpty()) {
                continue;
            }

            // Preferred server
            int preferred = i % k;

            // Find next available server
            Integer server = free.ceiling(preferred);

            // Circular search
            if (server == null) {
                server = free.first();
            }

            // Assign request
            count[server]++;

            free.remove(server);

            // Update server's finishing time
            busy.offer(new long[]{
                (long) time + load[i], server
            });
        }

        // Find maximum requests handled
        int max = 0;

        for (int c : count) {
            max = Math.max(max, c);
        }

        // Collect busiest servers
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                ans.add(i);
            }
        }

        return ans;
    }
}
