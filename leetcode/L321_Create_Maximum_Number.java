package MagicBook.leetcode;

import java.util.Arrays;

public class L321_Create_Maximum_Number {

	private static int[] findMaxKValue(int[] nums, int k) {
	    int[] result = new int[k];
	    int len = 0;
	    for (int i = 0; i < nums.length; i++){
	        while (len > 0 && len + nums.length - i > k && result[len - 1] < nums[i]){
	            len--;
	        }
	        if (len < k)
	            result[len++] = nums[i];
	    }
	    return result;
	}
	private static int[] maxNumber(int[] nums1, int k){
        int n = nums1.length;
        int[] ans = new int[k];
        for (int i = 0, j = 0; i < n; ++i) {
            while (n - i + j > k && j > 0 && ans[j - 1] < nums1[i]) 
                j--;
            if (j < k) 
                ans[j++] = nums1[i];
        }
        return ans;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {9, 1, 2, 5, 8, 3};
        int[] result = maxNumber(nums, 3);
        System.out.print(Arrays.toString(result));
	}

}
