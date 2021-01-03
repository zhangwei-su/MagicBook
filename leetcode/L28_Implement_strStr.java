package MagicBook.leetcode;

import java.util.Arrays;

public class L28_Implement_strStr {

	//Index to check further char when mismatch, also length of same prefix and postfix of substr ending BEFORE each char
    //[aaaaa] => [-1,0,1,2,3]
    //[issip] => [-1, 0, 0, 0, 1]
	public static int[] getKMPNext(String str) {
        int[] next = new int[str.length()];
        next[0] = -1; //Ending BEFORE first char, no same prefix and postfix;
        for (int i=1; i<str.length(); i++) {
            //matching b/w char[i-1] with next[i-1] and its next: next[next[i-1]]....
            //result stored in next[i] <-----ATTENTION here, next[i] *NOT* depend on char[i]
            int j = next[i-1]; 
            // next[i-1] is length of same prefix and postfix BEFORE i-1, the Index to check further same prefix and postfix for i-1
            while (j>=0 && str.charAt(j)!=str.charAt(i-1)) {
                //if char[i-1] not match next[j], it can not extend "same prefix and postfix" on next[j]
                //need to shrink pattern in 0~next[j];
                //In KMP main func, when mismatch, do same: j = next[j];
                j = next[j];
            }
            //Two cases:
            //1. j = -1, then all previous char[j] != char[i-1], set next[i] = 0: length of same prefix and postfix of substr ending BEFORE i is zero.
            //2. one char[j] == char[i-1], char[i-1] can extend length of same prefix and postfix on j, so next[i] = j + 1;
            if (j < 0) {
                next[i] = 0;
            } else {
                next[i] = j + 1;
            }
        }
        return next;
    }
    public static int KMP(String haystack, String needle) {
        int[] next = getKMPNext(needle);
        System.out.println("getKMPNext:" + Arrays.toString(next));
        int i = 0;
        int j = 0;
        while(i<haystack.length() && j < needle.length()) {
            if (j == -1 || needle.charAt(j)==haystack.charAt(i)) {
                j++;
                i++;
            } else {
            	System.out.printf("i = %d: j jump %d -> %d\n", i , j, next[j]);
                j = next[j];
            }
        }
        if (j == needle.length()) {
            return i - needle.length();
        } else {
            return -1;
        }
    }
    public static int Sunday(String haystack, String needle) {
        int N = needle.length();
        int H = haystack.length();
        int[] charMap = new int[256];
        Arrays.fill(charMap, -1);
        //record most right index of each char in needle.
        for (int i=0; i < N; i++) {
            charMap[needle.charAt(i)] = i;
        }
        int i = 0;
        int j = 0;
        while(i<H && j < N) {
            if (needle.charAt(j)==haystack.charAt(i)) {
                i++;
                j++;
            } else {
                //when mismatch, check the char after pattern cover (nextI).
                int nextI = i+ N-j;
                if (nextI >= H) break;
                j = charMap[haystack.charAt(nextI)];
                if (j < 0) {
                    // if it NOT appear in pattern, start new matching from nextI+1;
                    i = nextI + 1;
                    j=0;
                } else {
                    // if it appear in pattern, align pattern at this char
                    // calculate i, and match pattern from beginning.
                    i = nextI - j;
                    j = 0;
                }
            }
        }
        if (j == N) {
            return i - needle.length();
        } else {
            return -1;
        }
    }
    public static int strStr(String haystack, String needle) {
        int n = needle.length();
        if (n == 0) return 0;
        return KMP(haystack, needle);
    }
	public static void main(String[] args) {
		String needle = "aaaaa";
		String haystack = "mississippi";
        int ret = strStr(haystack, needle);
        System.out.println("ret = " + ret);
	}

}
