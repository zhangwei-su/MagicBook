package MagicBook.leetcode;

public class Bin1292_Maximum_Side_Length_of_Square_with_Sum_LE_Threshold {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] mat = {{1,1,3,2,4,3,2},{1,1,3,2,4,3,2},{1,1,3,2,4,3,2}};
		int ret = maxSideLength(mat, 4);
		System.out.print("ret=" + ret);
	}
	//1. the up/left should be with j-1 and i-1
	static boolean anySquareExist(int length, int maxSum, int[][] sums) {
        int rowNum = sums.length;
        int colNum = sums[0].length;
        for (int y=1; y < rowNum - length; y++) {
            for (int x=1; x< colNum - length; x++) {
                int rightBottom = sums[y + length][x + length];
                int left = sums[y + length][x-1];
                int up = sums[y-1][x + length];
                int upperLeft = sums[y-1][x-1];
                if (rightBottom - up - left + upperLeft > maxSum) {
                	System.out.printf("anySquareExist (%d) y=%d, x=%d\n", length, y, x);
                	return true;
                }
            }
        }
        System.out.printf("anySquareExist (%d) return false\n", length);
        return false;
    }
    //One more col and row can help to avoid lots bounder checking.
    public static int maxSideLength(int[][] mat, int threshold) {
        int rowNum = mat.length;
        int colNum = mat[0].length;
        int[][] sums = new int[rowNum+1][colNum+1];
        for (int r=1; r<rowNum+1; r++) {
            for (int c=1; c<colNum+1; c++) {
                sums[r][c] = sums[r-1][c] + sums[r][c-1] - sums[r - 1][c-1] + mat[r-1][c-1];
            }
        }
        int l = 0;
        int r = Math.min(rowNum, colNum) + 1;
        while (l < r) {
            int m = l + (r - l)/2;
            if (anySquareExist(m, threshold, sums)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

}
