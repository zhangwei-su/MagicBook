# standard
[[LC727 Minimum Window Subsequence]]
```
int left = 0, right = 0;
while (right < s.size()) {
	// 增大窗口
	window.add(s[right]);
	right++;
	while (window needs shrink) {
		// 缩小窗口
		window.remove(s[left]);
		left++;
	}
}
```
现在开始套模板，只需要思考以下四个问题：
1、当移动 right 扩大窗口，即加入字符时，应该更新哪些数据？
2、什么条件下，窗口应该暂停扩大，开始移动 left 缩小窗口？
3、当移动 left 缩小窗口，即移出字符时，应该更新哪些数据？
4、我们要的结果应该在扩大窗口时还是缩小窗口时进行更新？
```
//win always expend to end; left cover case of left==right
int left = 0;
for (int right=0; right < n; right++) {
    expend win to new right
    while (left <= right && win satisfy) {
        record wind
        left++; //Shrink win
    }
}
```

# MonotonicQueue
[[LC1499 Max Value of Equation]]
[[LC862 Shortest Subarray with Sum at Least K]]
[[LC316. Remove Duplicate Letters]]
[[LC907 Sum of Subarray Minimums]]
[[LC456 132 Pattern]]
[[IV Find-max-unhealthy-period]]


虽然rule nums[i]单调的，但Queue储存也可能是坐标i。

```
ArrayDeque dq //Two pointer+Deque-Monotronic
for (int right=0; right < n; right++) { //may right<=n if PrefixSum
    while (dq.size()>0) {
        record/update satisfied ret;
        remove calced/Unsatisfied dq.removeFist()
    } // following is Monotronic Q (Asc/Des)
    while(dq.size()>0; check dq.getLast and right) dq.removeLast.
    dq.add(right)
}
```

单独看 push 操作的复杂度确实不是 O(1)，但是算法整体的复杂度依然是 O(N) 线性时间。要这样想，nums 中的每个元素最多被 push_back 和 pop_back 一次，没有任何多余操作，所以整体的复杂度还是 O(N)。

单调队列在添加元素的时候靠删除元素保持队列的单调性，相当于抽取出某个函数中单调递增（或递减）的部分；而优先级队列（二叉堆）相当于自动排序，差别大了去了。

