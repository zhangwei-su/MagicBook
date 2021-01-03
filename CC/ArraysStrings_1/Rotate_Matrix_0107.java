package MagicBook.CC.ArraysStrings_1;

public class Rotate_Matrix_0107 {
	boolean rotate(int[][] matrix) {
		if (matrix.length == 0 || matrix.length != matrix[0].length) return false;
		for (int row = 0; row < matrix.length / 2; row++) {
			for (int col = row; col < matrix[0].length - row; col++) {
				int tmp = matrix[row][col];
				matrix[row][col] = matrix[matrix.length - col - 1][row];
				matrix[matrix.length - col - 1][row] = matrix[matrix.length - row - 1][matrix.length - col - 1];
				matrix[matrix.length - row - 1][matrix.length - col - 1] = matrix[matrix.length - col - 1][matrix.length - row - 1];
				matrix[matrix.length - col - 1][matrix.length - row - 1] = tmp;
			}
		}
		return true;
	}
}
