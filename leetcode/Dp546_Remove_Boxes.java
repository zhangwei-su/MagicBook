package MagicBook.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dp546_Remove_Boxes {

	 private static List<Integer> convertIntArrayToList(int[] input) {

        List<Integer> list = new ArrayList<>();
        for (int i : input) {
            list.add(i);
        }
        return list;

    }
	public static void main(String[] args) {
		
		int n = 50;
        int[][][] dp = new int[n][n][n];
        Math.max(dp[0][0][0], dp[1][1][1]);

	}

}
