class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int[] dp = new int[budget + 1];

        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            minPrice = Math.min(minPrice, item[1]);
        }

        for (int i = 0; i < n; i++) {
            int factor = items[i][0];
            int price = items[i][1];

            int count = 0;

            // Count items received from the first purchase
            for (int j = 0; j < n; j++) {
                if (items[j][0] % factor == 0) {
                    count++;
                }
            }

            // 0-1 Knapsack: first purchase of this item type
            for (int money = budget; money >= price; money--) {
                dp[money] = Math.max(
                    dp[money],
                    dp[money - price] + count
                );
            }
        }

        int answer = 0;

        // Use remaining budget to buy cheapest items
        for (int money = 0; money <= budget; money++) {
            int extra = (budget - money) / minPrice;

            answer = Math.max(answer, dp[money] + extra);
        }

        return answer;
    }
}