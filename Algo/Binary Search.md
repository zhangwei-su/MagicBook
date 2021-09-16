# template
```
binarySearch(l, r) // [l, r)
	while (l < r) {
		int m = l + (r - l)/2;
		if(g(m)) {
			r = m;
		} else {
			l = m+1;
		}
	}
	return l; //Min value which satisfies g(m) [l<=r]
}
```
# Matrix
[[IV leftmost-column-index-of-1]]
Binary Search:O(row*log(col)); Start from top right. left until 0,down one O(row+col)