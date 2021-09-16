# Merge Sort
## merge sort (top-down)
```
merge_sort(a, l, r) {
```
## merge sort (Bottom up)
```
for (int k=1; k<=len; k=k<<1) {
```

# partition (quick Sort)
[[LC315 Count of Smaller Numbers After Self]]
```
 int partition(int[] nums, int left, int right) {
        int l = left+1;
        int r = right;
        int pivot = nums[left];
        while (l <= r) { //should <= here, final let r move to left partition.
            if (nums[l] > pivot && nums[r] < pivot) { //it is ascend
                swap(nums, l, r);
                l++;
                r--;
            }
            if (nums[l] <= pivot) l++; //ascend
            if (nums[r] >= pivot) r--; //ascend
        }
        swap(nums, left, r); //sinc r in left partition, No issue to move it from end of partition to head.
        return r;
    }
```

```
quickSort(arr, low, high) {
	if (low < high) {
		int pi = partition(arr, low, high);
		quickSort(arr, low, pi - 1);
		quickSort(arr, pi + 1, high);
	} 
}
```