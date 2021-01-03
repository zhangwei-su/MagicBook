package MagicBook.leetcode;

public class Dp664_Strange_Printer {
    public static int strangePrinter(String s) {
    	int n = s.length();
        if (n==0) return 0;
        int[][] dp = new int[n][n];
        for (int i=n-1; i>=0; i--) {
            for (int j=i; j<n; j++) {
                dp[i][j]=(i==j) ? 1:dp[i+1][j] + 1;
                for (int k=i+1;k<=j;k++) {
                    if(s.charAt(i)==s.charAt(k)) {
                        dp[i][j] = Math.min(dp[i][j], dp[i+1][k-1] + dp[k][j]);
                    }
                }
            }
        }
        return dp[0][n-1];
    }
	public static void main(String[] args) {
		String s = "aaabbb";  //#1 expect 2
		int ret = strangePrinter(s);
		System.out.println("ret=" + ret);
	}

}
