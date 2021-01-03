package MagicBook.leetcode;

public class Bin4_Median_of_Two_Sorted_Arrays {

	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1=nums1.length, n2=nums2.length;
        if (n1>n2) return findMedianSortedArrays(nums2, nums1);
        int k = (n1+n2+1)/2;
        int left=0, right=n1;
        int m1=0, m2=0;
        while (left < right) {
            //m1 cut spot is from before idx 0 to before idx n1
            m1 = left + (right - left)/2;
            m2 = k - m1;
            if (nums1[m1] < nums2[m2-1]) {
                left = m1+1;
            } else {
                right = m1;
            }
        }
        m1 = left;
        m2 = k-left;
        int c1 = Math.max(m1 == 0 ? Integer.MIN_VALUE : nums1[m1 - 1],
                         m2 == 0 ? Integer.MIN_VALUE : nums2[m2 - 1]);
        if ((n1+n2) % 2 == 1) return c1;
        int c2 = Math.min(m1 >= n1 ? Integer.MAX_VALUE : nums1[m1],
                         m2 >= n2? Integer.MAX_VALUE : nums2[m2]);
        return (c1+c2)*0.5;
    }
	public static void main(String[] args) {
		int[] nums1 = {1,3}, nums2 = {2};
		double ret = findMedianSortedArrays(nums1, nums2);
		System.out.println("ret = " + ret);
	}

}