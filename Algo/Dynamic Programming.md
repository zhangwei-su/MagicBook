![[Dynamic Programming common template]]

# 接⻰型
: dp[i+1] = max{dp[j] + 1}               2D接龙型: LC1277. Count Square Submatrices with All Ones;LC221. Maximal Square

ex: prefix sum, LC70. climbing stairs; LC198 House Robber; LC801Minimum Swaps To Make Sequences Increasing; LC926Flip String to Monotone Increasing(left[]/right[], cal ret from left/right)

# Classic problem
## Same prefix-suffix, KMPNext
```
public static int[] getKMPNext(String str) {
	int[] next = new int[str.length()];
	next[0] = -1; 
	for (int i=1; i<str.length(); i++) {
		int j = next[i-1];
		while (j>=0 && str.charAt(j)!=str.charAt(i-1)) {
			j = next[j];
		}
		if (j < 0) {
			next[i] = 0;
		} else {
			next[i] = j + 1;
		}
	}
	return next;
}
```
