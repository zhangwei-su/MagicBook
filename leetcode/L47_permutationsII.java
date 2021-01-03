package MagicBook.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L47_permutationsII {
	//used
    static void permuteUnique(int[] nums, int used, List<Integer> curr, List<List<Integer>> ret) {
        int fullUsed = (1<<nums.length) - 1;
        if (used == fullUsed) {
            ret.add(new ArrayList(curr));
            return;
        }
        for (int i=0; i<nums.length; i++) {
            if ((used & (1<<i)) != 0) continue;
            //since nums is always kept sorted, AND, we always scan it from i=0
            //(used & (1<<(i-1))) == 0, means We ever tried this for this spot, we should skip same number.
            //used & (1<<(i-1))) == 1, it means the number was used in Other spot, we can use it in current spot
            if (i > 0 && nums[i-1] == nums[i] && 
               (used & (1<<(i-1))) == 0) continue;
            used |= 1<<i;
            curr.add(nums[i]);
            permuteUnique(nums, used, curr, ret);
            curr.remove(curr.size()-1);
            used &= ~(1<<i);
        }
    }

	//swap
    static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
     }
    static void permuteUnique(int[] nums, int curr,  List<List<Integer>> ret) {
        if (curr == nums.length) {
            List<Integer> list = new ArrayList<>(nums.length);
            for (int i : nums) {
                list.add(Integer.valueOf(i));
            }
            ret.add(list);
            return;
        }
        for (int i=curr; i<nums.length; i++) {
            //if (i>curr && nums[i]==nums[i-1]) continue;
            //if (i>curr && nums[i]==nums[i-1]) continue;
            //above can not work, have to use following
            //because, Not only checking nums[curr], but also All number b/w i-1 and curr,
            //any number same as nums[i], it means the number was tried on this spot, 
            //the number should be skipped.
            //for example, [0,0,0,1,9] when curr=1,i=4->[0,9,0,1,0], the subArray[9,0,1,0], sort is broken. 
            //recursive, curr=2, i=4, got issue for above checking. 
            //so, sort can not help on swap solution
            int j = i - 1;
            while (j >= curr && nums[j] != nums[i]) --j; //We can use Set here instead of while-loop.
            if (j != curr - 1) continue;
            swap(nums, curr, i);
            permuteUnique(nums, curr+1, ret);
            swap(nums, curr, i);
        }
    }
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ret = new ArrayList();
        //Arrays.sort(nums);
        permuteUnique(nums, 0, ret);
        return ret;
    }
	public static void main(String[] args) {
		int[] nums = {0,1,0,0,9};
		List<List<Integer>> ret = permuteUnique(nums);
		System.out.println("num of ret=" + ret.size());
		System.out.println(ret);
	}

}
