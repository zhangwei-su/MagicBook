package MagicBook.leetcode;

public class Bin786_Kth_Smallest_Prime_Fraction {
	public static int[] kthSmallestPrimeFraction(int[] A, int K) {
        int n = A.length;
        int[] ret= new int[2];
        double left=0, right=1;
        while (left < right) {
            int p=0, q=0, cnt=0;
            double mid = left + (right - left)/2;
            for (int i=n-1; i>=0; i--) {
                int j=0;
                while (j<i && A[j]<mid*A[i]) {
                    if (p==0 || p*A[i] < q*A[j]) {
                        p = A[j]; q=A[i];
                    }
                    j++;
                }
                cnt += j;
            }
            if (cnt < K) {
                left = mid + 1/30000;
            } else {
                right = mid;
                ret[0] = p;
                ret[1] = q;
                if (cnt == K) return ret;
            }
        }
        return ret;
    }

	public static void main(String[] args) {
		int[] A = {1, 2, 3, 5};
		int K = 3;
		int[] ret = kthSmallestPrimeFraction(A, K);
		System.out.println("ret=" + ret);

	}

}
