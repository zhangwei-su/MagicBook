package MagicBook.leetcode;

public class L044_Wildcard_Matching {
/*
'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") -> false
isMatch("aa","aa") -> true
isMatch("aaa","aa") -> false
isMatch("aa", "*") -> true
isMatch("aa", "a*") -> true
isMatch("ab", "?*") -> true
isMatch("aab", "c*a*b") -> false
 */
	static boolean isMatch(String S, String T) {
		int lenS = S.length();
		int lenT = T.length();
		boolean dp[][] = new boolean[lenS+1][lenT+1];
		dp[0][0] = true;
		for (int i=0; i <= lenS; i++) {
			for (int j=1; j <= lenT; j++) {
				if (T.charAt(j-1) == '*') {
					dp[i][j] = (i>0) ? dp[i-1][j]|| dp[i][j-1] : dp[i][j-1]; 
					System.out.printf("branch-*, dp[%d][%d]=%b\n", i,j,dp[i][j]);
				} else if (i>0 &&(T.charAt(j-1) == '?' || T.charAt(j-1) == S.charAt(i-1))){
					dp[i][j] = dp[i-1][j-1];
					System.out.printf("branch-other, dp[%d][%d]=%b\n", i,j,dp[i][j]);
				}
			}
		}
		return dp[lenS][lenT];
	}
	public static void main(String[] args) {
		String S = "aab";
		String T = "a???*";
		System.out.printf("S=%s, T=%s, isMatch=%b\n", S, T, isMatch(S, T));
	}

}
