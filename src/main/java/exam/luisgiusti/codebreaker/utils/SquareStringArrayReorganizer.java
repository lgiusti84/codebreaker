package exam.luisgiusti.codebreaker.utils;


import java.util.ArrayList;
import java.util.List;

public class SquareStringArrayReorganizer {

	private SquareStringArrayReorganizer() {}

	public static List<String> rotate(String[] input) {
		char[][] matrix = stringArrayToCharMatrix(input);
		matrix = rotateMatrix(matrix);
		return charMatrixToStringList(matrix);
	}

	public static List<String> diagonalize(String[] input) {
		char[][] matrix = stringArrayToCharMatrix(input);
		List<String> tempList = new ArrayList<>();

		tempList.addAll(getDiagnolasDownwardsToRight(matrix));
		matrix = rotateMatrix(matrix);
		tempList.addAll(getDiagnolasDownwardsToRight(matrix));

		return tempList;
	}

	private static List<String> getDiagnolasDownwardsToRight(char[][] matrix) {
		int length = matrix.length;
		List<String> tempList = new ArrayList<>();
		StringBuilder rowMovingSb;
		StringBuilder colMovingSb;
		for (int pos = 0; pos < length; pos++) {
			rowMovingSb = new StringBuilder();
			colMovingSb = new StringBuilder();
			for (int dif = 0; pos + dif < length; dif++) {
				rowMovingSb.append(matrix[dif][pos + dif]);
				colMovingSb.append(matrix[pos + dif][dif]);
			}
			tempList.add(rowMovingSb.toString());
			tempList.add(colMovingSb.toString());
		}
		tempList.remove(0);

		return tempList;
	}

	private static char[][] rotateMatrix(char[][] matrix) {
		int length = matrix.length;
		char[][] temp = new char[length][length];

		int newCol;
		for (int row = 0; row < length; row++) {
			for (int col = 0; col < length; col++) {
				newCol = (-1) * (col - length + 1);
				temp[newCol][row] = matrix[row][col];
			}
		}
		return temp;
	}

	private static char[][] stringArrayToCharMatrix(String[] input) {
		int length = input.length;
		char[][] matrix = new char[length][length];
		for (int row = 0; row < length; row++) {
			matrix[row] = input[row].toCharArray();
		}
		return matrix;
	}

	private static List<String> charMatrixToStringList(char[][] input) {
		List<String> strList = new ArrayList<>();
		for (char[] inputRow : input) {
			strList.add(new String(inputRow));
		}
		return strList;
	}
}
