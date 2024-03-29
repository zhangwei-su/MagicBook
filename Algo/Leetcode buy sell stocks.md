这个问题的「状态」有三个，
第一个是天数，
第二个是允许交易的最大次数，
第三个是当前的持有状态（即之前说的 rest 的状态，我们不妨用 1 表示持有，0 表示没有持有）

定义 base case，即最简单的情况。
```
dp[-1][k][0] = 0

解释：因为 i 是从 0 开始的，所以 i = -1 意味着还没有开始，这时候的利润当然是 0 。

dp[-1][k][1] = -infinity

解释：还没开始的时候，是不可能持有股票的，用负无穷表示这种不可能。

dp[i][0][0] = 0

解释：因为 k 是从 1 开始的，所以 k = 0 意味着根本不允许交易，这时候利润当然是 0 。

dp[i][0][1] = -infinity

解释：不允许交易的情况下，是不可能持有股票的，用负无穷表示这种不可能。
```
关键就在于列举出所有可能的「状态」，然后想想怎么穷举更新这些「状态」。一般用一个多维 dp 数组储存这些状态，从 base case 开始向后推进，推进到最后的状态，就是我们想要的答案。

  

base case：
```
dp[-1][k][0] = dp[i][0][0] = 0

dp[-1][k][1] = dp[i][0][1] = -infinity
```
  

  

状态转移方程：
```
dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]) //one buy+sell count one in k

dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]) //when buy, k-1, if cooldown, dp[i-cooldown][k-1][0] - prices[i]
```
