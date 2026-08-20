import java.util.*;

class Solution {
    public int[] resultArray(int[] nums) {
        List<Integer> nums1 = new ArrayList<>();
        List<Integer> nums2 = new ArrayList<>();

        nums1.add(nums[0]);
        nums2.add(nums[1]);

        for (int i = 2; i < nums.length; i++) {
            if (nums1.get(nums1.size() - 1) > nums2.get(nums2.size() - 1)) {
                nums1.add(nums[i]);
            } else {
                nums2.add(nums[i]);
            }
        }

        int[] result = new int[nums.length];
        int index = 0;

        for (int num : nums1) {
            result[index++] = num;
        }

        for (int num : nums2) {
            result[index++] = num;
        }

        return result;
    }
}