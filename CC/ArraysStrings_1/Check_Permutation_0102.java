package MagicBook.CC.ArraysStrings_1;

import java.util.Arrays;

public class Check_Permutation_0102 {

	String sort(String src) {
		char[] content = src.toCharArray();
		Arrays.sort(content);
		return new String(content);
	}
	boolean CheckPermutation1(String src, String dst) {
		if (src.length() != dst.length()) return false;
		return sort(src).equals(sort(dst));
	}
//////////////////////////////////////////////
	boolean CheckPermutation2(String src, String dst) {
		if (src.length() != dst.length()) return false;
		int[] ascChar = new int[128];
		char[] content = src.toCharArray();
		for (char c : content) {
			ascChar[c]++;
		}
		content = dst.toCharArray();
		for (char c : content) {
			if (ascChar[c] == 0) {
				return false;
			}
			ascChar[c]--;
		}
		return true;
	}
}
