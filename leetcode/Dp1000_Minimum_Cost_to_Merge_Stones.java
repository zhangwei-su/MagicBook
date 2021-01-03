package MagicBook.leetcode;

public class Dp1000_Minimum_Cost_to_Merge_Stones {

    static public int mergeStones(int[] stones, int K) {
        int n = stones.length;
        if (n == 0 || n < K) return -1;
        if ((n-1) % (K-1) != 0) return -1;
        int dp[][] = new int[n][n];
        int maxInt = Integer.MAX_VALUE;
        int sum[] = new int[n+1];
        for (int i=0; i<n; i++) {
            sum[i+1] = sum[i] + stones[i];
        }
        for (int l=K; l <= n; l++) {
            for(int i=0; i+l<=n; i++) {
                int j = i+l-1;
                dp[i][j] = maxInt;
                for (int m=i; m <j; m+=K-1) {
                    dp[i][j]=Math.min(dp[i][j], dp[i][m] + dp[m+1][j]);
                }
                if ((j-i) % (K-1) == 0) dp[i][j] += sum[j+1] - sum[i];
            }
        }
        return dp[0][n-1];
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int input[] = {3,5,1,2,6};
		int ret = mergeStones(input, 3);
		System.out.println("got result=" + ret);
	}

}
