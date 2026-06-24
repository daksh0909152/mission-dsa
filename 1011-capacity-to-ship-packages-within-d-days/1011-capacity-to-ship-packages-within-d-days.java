class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int minweight = 0;
        int maxweight = 0;

for (int weight : weights) {
    minweight = Math.max(minweight, weight);
    maxweight += weight;
}
        //binary search
        while(minweight<maxweight){
            int mid= minweight + (maxweight-minweight)/2;
            if(CapacityToShipPackages(weights,days,mid))
            maxweight= mid;
            else
            minweight= mid+1;
        }
        return minweight;
        
    }
   private boolean CapacityToShipPackages(int[] weights, int days, int capacity) {
    int day = 1;          // pehla day
    int currentLoad = 0;  // current day ka load

    for (int weight : weights) {

        // agar current package add karne se capacity exceed ho rahi hai
        if (currentLoad + weight > capacity) {
            day++;              // naya day start
            currentLoad = 0;
        }

        currentLoad += weight;
    }

    return day <= days;
}
}