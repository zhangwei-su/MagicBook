# N sum
## 2 sum

## N sum

# Combination
## subset
### non-duplication
[[LC78 Subsets]]
- 子集续新；
- combination；
- 二进制bit组合

combination:
```

```
### duplication allowed
- 子集续新 `if S[i]==Last, append S[i] on CurSize-LastSize ~ CurSize Only);` 
- combinationWithDup


[[LC698 Partition to K Equal Sum Subsets]]
```
sort(nums)
combination(l, s) {
    if (l==n) return func();
    for (i=s;i<n;i++) {
        if(i>s && nums[i-1]==nums[i]) continue;
        combination(l+1, i+1)
    }
}//: visited+idx=start
```

# Permutation
## backtrack
```
for (int i = 0; i < num.size(); ++i) {
	if (visited[i] == 1) continue;
	visited[i] = 1; // if (i > 0 && eq to num[i-1] && !visited[i - 1])
	out.push_back(num[i]);
	permuteDFS(num, level + 1, visited, out, res);
	out.pop_back();
	visited[i] = 0;
}
```

## swap
```
for (int i = start; i < num.size(); ++i) {
	swap(num[start], num[i]); //use local visit if dup
	permuteDFS(num, start + 1, res);
	swap(num[start], num[i]);
}
```