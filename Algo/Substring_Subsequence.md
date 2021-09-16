# Substring
Common thought:
- HashMap+prefix Sum
- Sliding window(Two pointers same direct)
## prefix sum/Counter
## Negative allowed

[[LC523 Continuous Subarray Sum]]
[[LC974 Subarray Sums Divisible by K]]
if a%c=b%c, (a-b)%c=0
Ensure reminder as none-negative before searching in HashMap:
```
sum = (sum + A[i]%K + K) % K
```

正负数sum之后的整除，要将负数余数拉伸为正数(+K)

### using Bit Manipulation
[[LC1371 Find the Longest Substring Containing Vowels in Even Counts]]
[[LC1915 Number of Wonderful Substrings]]
Whether counter is Odd or Even , can be recorded with Bit Manipulation
Diffs b/w Even and Even or b/w Odd and Odd are Even, so if bitMap same, diff is even
Diffs b/w Odd and Even are Odd, so if bitMap mutually exclusive, diff is Odd



## trick
[[LC30 Substring with Concatenation of All Words]]
```
Special attribution of  [0, 1, ..., N - 1] array
```
判断一组资源是否刚好用完，用总数计数最方便。

# Subsequence
## Sliding window
[[Sliding window]]

