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
## backtrack non-duplication
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

## backtrack duplication allowed
```
Arrays.sort(nums)
for (int i = 0; i < nums.size(); ++i) { //start with Zero
  if (used[i]) continue;
  // Same number can be only used once at each depth.
  if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
  used[i] = 1;
  cur.push_back(nums[i]);
//used here, go with recursive call. so need clear after call
 permuteDFS(nums, cur, used, ans); 
  cur.pop_back();
  used[i] = 0;
}
```
## swap non-duplication
```
for (int i = start; i < num.size(); ++i) {
	swap(num[start], num[i]); //use local visit if dup
	permuteDFS(num, start + 1, res);
	swap(num[start], num[i]);
}
```

## swap duplication allowed
```
Set visited;
for (int i = start; i < nums.size(); ++i) { //start with start
    //Not like above "used" solution, sort can NOT help on swap solution
    //We have to use Set (or while-loop) to check dup
    if(visited.contains(nums[i])) continue;
    visited.add(nums[i])
    swap(nums[start], nums[i]);
    permuteDFS(nums, start + 1, res); //visited here, is local variable, only for skipping dup on current level.
    swap(nums[start], num[i]);
}
```