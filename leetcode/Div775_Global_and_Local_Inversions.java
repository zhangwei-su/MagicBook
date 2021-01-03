package MagicBook.leetcode;

public class Div775_Global_and_Local_Inversions {

	public static boolean solution1(int[] A) {
        for (int i=0; i<A.length; i++) {
            if (Math.abs(A[i] - i)  > 1) return false;
        }
        return true;
    }
    public static boolean solution2(int[] A) {
        int max = 0;
        for (int i = 0; i+2 < A.length; i++) {
            max = Math.max(max, A[i]);
            if (max > A[i+2]) return false;
        }
        return true;
    }
    public static boolean solution3(int[] A) {
        int local = 0;
        for(int i=0; i+1<A.length; i++) {
            if (A[i] > A[i+1]) local++;
        }
        int globe = mergeSort(A, 0, A.length-1);
        return local == globe;
    }
    public static int mergeSort(int[] A, int l, int r) {
        if (l > r || l == r) return 0;
        int mid = l + (r - l) / 2;
        int globle = mergeSort(A, l, mid) + mergeSort(A, mid+1, r);
        int i = l;
        int j = mid + 1;
        int k = 0;
        int[] tmp = new int[r-l+1];
        while(i<=mid && j<=r) {
            if (A[i] < A[j]) {
                tmp[k++] = A[i++];
            } else {
                tmp[k++] = A[j++];
                globle += mid - i + 1;
            }
        }
        while(i<=mid) tmp[k++] = A[i++];
        while(j<=r) tmp[k++] = A[j++];
        for (k=0; k<tmp.length;) A[l++] = tmp[k++];
        return globle;
    }
    public static boolean isIdealPermutation(int[] A) {
        return solution3(A);
    }
	public static void main(String[] args) {
		int[] A = {2,0,1};
		boolean ret = isIdealPermutation(A);
		System.out.println("ret=" + ret);
	}

}
