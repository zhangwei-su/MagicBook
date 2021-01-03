package MagicBook.leetcode;

import java.util.Arrays;

public class L085_Maximal_Rectangle {

	static int maximalRectangle(char[][] matrix) {
	    if(matrix.length == 0) return 0;
	    int m = matrix.length;
	    int n = matrix[0].length;
	    int left[] = new int[n]; 
	    int right[] = new int[n]; 
	    int height[] = new int[n]; 
	    Arrays.fill(left, 0);
	    Arrays.fill(right, n);
	    Arrays.fill(height, 0);
	    int maxA = 0;
	    for(int i=0; i<m; i++) {
	        
	        // compute the area of rectangle (can do this from either side)
	        for(int j=0; j<n; j++)
	            maxA = Math.max(maxA,(right[j]-left[j])*height[j]);
	        System.out.println("Row#" + i + ", maxA=" + maxA);
	        System.out.println("height:\t" + Arrays.toString(height));
	        System.out.println("left:\t" + Arrays.toString(left));
	        System.out.println("right:\t" + Arrays.toString(right));
	    }
	    return maxA;
	}
	public static void main(String[] args) {
		char[][] matrix = {
				{'1','0','1','0','1'},
				{'0','0','1','1','0'},
				{'1','1','1','1','1'},
				{'1','0','1','0','1'},
		};
		maximalRectangle(matrix);

	}

}
