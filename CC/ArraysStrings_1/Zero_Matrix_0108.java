package MagicBook.CC.ArraysStrings_1;

public class Zero_Matrix_0108 {
	void drowZeroRow(int[][] matrix, int row) {
		for (int i=0; i < matrix[row].length; i++) {
			matrix[row][i] = 0;
		}
	}
	void drowZeroColumn(int[][] matrix, int column) {
		for (int i=0; i < matrix.length; i++) {
			matrix[i][column] = 0;
		}
	}
	void setZero(int[][] matrix) {
		boolean firstRowHaveZero = false;
		boolean firstColumnHaveZero = false;
		for (int i=0; i < matrix.length; i++) {
			if (matrix[i][0] == 0) {
				firstColumnHaveZero = true;
				break;
			}
		}
		for (int i=0; i < matrix[0].length; i++) {
			if (matrix[0][i] == 0) {
				firstRowHaveZero = true;
				break;
			}
		}
		for (int i=1; i < matrix.length; i++) {
			for (int j=1; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					matrix[0][j] = 0;
					matrix[i][0] = 0;
				}
			}
		}
		for (int i=0; i < matrix[0].length; i++) {
			if (matrix[0][i] == 0) drowZeroColumn(matrix, i);
		}
		for (int i=0; i < matrix.length; i++) {
			if (matrix[i][0] == 0) drowZeroRow(matrix, i);
		}
		if (firstRowHaveZero) drowZeroRow(matrix, 0);
		if (firstColumnHaveZero) drowZeroColumn(matrix, 0);
	}

}
