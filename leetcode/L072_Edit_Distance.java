package MagicBook.leetcode;

public class L072_Edit_Distance {
/*
Given two words word1 and word2, find the minimum number of steps
 required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character
 */
	static int minConvert(String S1, String S2) {
		int dp[][] = new int[S1.length()+1][S2.length()+1];
		for (int i=0; i<S1.length()+1; i++) {
			for (int j=0; j<S2.length()+1; j++) {
				//Intitial 00 and border
				if (i == 0 && j == 0) {
					dp[0][0] = 0;
					continue; 
				}
				if (i == 0) {
					dp[i][j] = dp[i][j-1]++;
					continue; 
				}
				if (j == 0) {
					dp[i][j] = dp[i-1][j]++;
					continue; 
				}
				//From dp[i-1][j-1], it is replace
				//From dp[i-1][j],  it is insert one to S1(i), to match S2(j)
				//From dp[i][j-1],  it is insert one to S2(j), to match S1(i)
				int min = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
				dp[i][j] = (S1[i-1] == S2[j-1]) ? min : min+1;
			}
		}
		return dp[S1.length()+1][S2.length()+1];
	}
	public static void main(String[] args) {
		String S1="abab";
		String S2="abba";
        int steps = minConvert(S1, S2);
        System.out.printf("min step number from %s to %s is %d\n", S1, S2, steps);
	}

}
