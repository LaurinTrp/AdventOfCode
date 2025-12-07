package Day_06;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import Globals.ResourceLoader;

public class Day_06_2025 {
	public static void main(String[] args) {
		long result1 = 0;
		long result2 = 0;
		List<String> lines = ResourceLoader.getContentAsLines("2025", "Day_06" + File.separator + "Input.txt");

		List<String> linesPt1 = lines.stream().map(x -> x.replaceAll("\\s+", " ").strip()).collect(Collectors.toList());

		String[] operators = linesPt1.removeLast().split(" ");

		List<Long[]> values = linesPt1.stream()
				.map(line -> Arrays.stream(line.split(" ")).map(Long::parseLong).toArray(Long[]::new))
				.collect(Collectors.toList());

		Long[][] transposedValues = transpose(values.toArray(Long[][]::new));

		for (int row = 0; row < transposedValues.length; row++) {
			long preResult = transposedValues[row][0];
			char operator = operators[row].charAt(0);
			for (int column = 1; column < transposedValues[row].length; column++) {
				long cell = transposedValues[row][column];
				if (operator == '*') {
					preResult *= cell;
				} else if (operator == '+') {
					preResult += cell;
				}
			}
			result1 += preResult;
		}

		System.out.println("Result 1: " + result1);

		List<String> linesPt2 = new ArrayList<>(lines);

		List<List<Integer>> spaces = new ArrayList<>();

		for (int i = 0; i < linesPt2.size(); i++) {
			String line = linesPt2.get(i);
			spaces.add(new ArrayList<>());
			for (int j = 0; j < line.length(); j++) {
				if (line.charAt(j) == ' ') {
					spaces.get(i).add(j);
				}
			}
		}

		List<Integer> valids = new ArrayList<>();
		for (Integer index : spaces.get(0)) {
			boolean isValid = true;
			for (int i = 1; i < spaces.size(); i++) {
				isValid = spaces.get(i).contains(index);
			}
			if (isValid) {
				valids.add(index);
			}
		}

		List<int[]> blocksIndices = new ArrayList<>();
		int prev = -1;
		int width = linesPt2.get(0).length();

		for (int sep : valids) {
			blocksIndices.add(new int[] { prev + 1, sep - 1 });
			prev = sep;
		}

		if (prev < width - 1) {
			blocksIndices.add(new int[] { prev + 1, width - 1 });
		}
		List<List<Long>> nums = new ArrayList<>();
		for (int i = 0; i < blocksIndices.size(); i++) {
			int start = blocksIndices.get(i)[0];
			int end = blocksIndices.get(i)[1];

			 nums.add(extractNumbers(linesPt2, start, end));
		}
		
		for (int i = 0; i < nums.size(); i++) {
			char operator = operators[i].charAt(0);
			result2 += nums.get(i).stream().reduce(operator == '*' ? 1l : 0l, (x, y) -> operator == '*' ? x * y : x + y);
		}
		

		System.out.println("Result 2: " + result2);
	}

	static List<Long> extractNumbers(List<String> lines, int start, int end) {
		int rows = lines.size();
		List<Long> numbers = new ArrayList<>();

		for (int col = end; col >= start; col--) {
			StringBuilder sb = new StringBuilder();

			for (int row = 0; row < rows; row++) {
				char ch = lines.get(row).charAt(col);
				if (ch != ' ')
					sb.append(ch);
			}

			if (sb.length() > 0) {
				numbers.add(Long.parseLong(sb.toString()));
			}
		}

		return numbers;
	}

	public static Long[][] transpose(Long[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;

		Long[][] transposed = new Long[cols][rows];

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				transposed[c][r] = matrix[r][c];
			}
		}

		return transposed;
	}
}
