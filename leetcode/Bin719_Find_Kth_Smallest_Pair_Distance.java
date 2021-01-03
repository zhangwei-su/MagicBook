package MagicBook.leetcode;

import java.util.Arrays;

public class Bin719_Find_Kth_Smallest_Pair_Distance {

	public static int smallestDistancePair(int[] nums, int k) {
		Arrays.sort(nums);
        int n = nums.length;
        int left = 0, right = nums[n-1] - nums[0];
        while (left < right) {
            int mid = left + (right - left)/2;
            int cnt=0;
            int j = 0;
            int dis = 0;
            for (int i=0; i<n; i++) {
                while (j<n && nums[j] - nums[i] <= mid) {
                	dis = Math.max(nums[j] - nums[i], dis);
                	j++;
                }
                cnt += j - i - 1;
            }
            if (cnt == k) return dis;
            if (cnt < k) left = mid+1;
            if (cnt > k) right = mid;
        }
        return right;
    }
	public static void main(String[] args) {
		//int[] nums = {9,10,7,10,6,1,5,4,9,8}; int k = 18; //expect 2;
		int[] nums = {1,6,1}; int k = 1; //expect 5;
		int ret = smallestDistancePair(nums, k);
		System.out.println("ret=" + ret);
	}

}
