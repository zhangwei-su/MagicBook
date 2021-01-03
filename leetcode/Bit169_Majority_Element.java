package MagicBook.leetcode;

public class Bit169_Majority_Element {

	public static int majorityElement(int[] nums) {
        int ret = nums[0], times = 1;
        for (int i=1; i<nums.length; i++) {
            if (ret != nums[i]) {
                times--;
            } else {
                times++;
            }
            if (times < 0) {
                ret = nums[i];
                times = 1;
            }
        }
        return ret;
    }
	public static void main(String[] args) {
		int[] nums = {3,2,3};
		int ret = majorityElement(nums);
        System.out.println("ret=" + ret);
	}

}
