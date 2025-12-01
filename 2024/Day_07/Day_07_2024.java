package Day_07;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import Globals.ResourceLoader;

public class Day_07_2024 {
	public static void main(String[] args) {
//		32292731
		int result1 = 0;
		int result2 = 0;
		List<String> lines = ResourceLoader.getContentAsLines("2024", "Day_07" + File.separator + "Input.txt");

		Map<Long, Long[]> parsedValues = new LinkedHashMap<>();

		for (String line : lines) {
			String[] firstSplit = line.split("\\:");
			long result = Long.parseLong(firstSplit[0]);
			String[] secondSplit = firstSplit[1].trim().split("\\s");
			Long[] values = Arrays.stream(secondSplit).map(x -> Long.parseLong(x)).toArray(Long[]::new);

			parsedValues.put(result, values);

			if (evaluate(result, values, createCombinations(values.length))) {
				result1+=result;
			}
		}

		System.out.println("Result 1: " + result1);
		System.out.println("Result 2: " + result2);
	}

	private static List<char[]> createCombinations(int length) {
		List<char[]> combinations = new ArrayList<>();
		int totalCombinations = (int) Math.pow(2, length - 1);
		for (int i = 0; i < totalCombinations; i++) {
			char[] operators = new char[length - 1];
			for (int j = 0; j < length - 1; j++) {
				if ((i & (1 << j)) == 0) {
					operators[j] = '+';
				} else {
					operators[j] = '*';
				}
			}
			combinations.add(operators);
		}
		return combinations;
	}

	private static boolean evaluate(Long expected, Long[] values, List<char[]> combinations) {
		for (char[] combination : combinations) {
			long result = values[0];
			for (int i = 0; i < values.length - 1; i++) {
				if (combination[i] == '+') {
					result += values[i + 1];
				} else if (combination[i] == '*') {
					result *= values[i + 1];
				}
			}
			if (expected.equals(result)) {
//				System.out.println(result + " " + Arrays.toString(values) + " " + Arrays.toString(combination));
				return true;
			}
		}
		return false;
	}
}
