package MagicBook.CC.ArraysStrings_1;

public class Palindrome_Permutation_0104 {
	int toggle(int src, int offset) {
		int mask = 1 << offset;
		if ((src & mask) == 0) {
			src |= mask;
		} else {
			src &= ~mask;
		}
		return src;
	}
	//assume only lowcase char
	boolean checkPalindrome(String src) {
		int checker = 0;
		for (int i=0; i < src.length(); i++) {
			checker = toggle(checker, src.charAt(i) - 'a');
		}
		if (checker == 0 || (checker&(checker-1)) == 0) return true;
		return false;
	}

}
