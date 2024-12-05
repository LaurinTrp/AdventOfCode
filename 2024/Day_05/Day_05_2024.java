package Day_05;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Globals.ResourceLoader;

public class Day_05_2024 {
	public static void main(String[] args) {
		int result1 = 0;
		int result2 = 0;

		List<String> lines = ResourceLoader.getContentAsLines("2024", "Day_05" + File.separator + "Input.txt");

		List<String> rules = new ArrayList<>();
		List<String> updates = new ArrayList<>();
		boolean isRules = true;
		for (String line : lines) {
			if (line.isBlank()) {
				isRules = false;
				continue;
			}

			if (isRules) {
				rules.add(line);
			} else {
				updates.add(line);
			}
		}

		List<Integer[]> rulesInts = new ArrayList<>();
		for (String rule : rules) {
			String[] split = rule.split("\\|");
			rulesInts.add(new Integer[] { Integer.parseInt(split[0]), Integer.parseInt(split[1]) });
		}

		for (String line : updates) {
			Integer[] updatesValues = Arrays.stream(line.split(",")).map(x -> Integer.parseInt(x))
					.toArray(Integer[]::new);
			boolean validLine = true;
			for (int x = 0; x < updatesValues.length; x++) {
				for (int y = x + 1; y < updatesValues.length; y++) {
					if (!validPair(rulesInts, updatesValues[x], updatesValues[y])) {
						validLine = false;
					}
				}
			}
			if (validLine) {
				result1 += updatesValues[updatesValues.length / 2];
			}
		}

		System.out.println("Result 1: " + result1);

		for (String line : updates) {
			Integer[] updatesValues = Arrays.stream(line.split(",")).map(x -> Integer.parseInt(x))
					.toArray(Integer[]::new);

			boolean invalidLine = false;
			for (int x = 0; x < updatesValues.length; x++) {
				for (int y = x + 1; y < updatesValues.length; y++) {
					Integer[] update = updatedValues(rulesInts, updatesValues[x], updatesValues[y]);
					if (updatesValues[x] != update[0] && updatesValues[y] != update[1]) {
						updatesValues[x] = update[0];
						updatesValues[y] = update[1];
						invalidLine = true;
					}
				}
			}

			if (invalidLine) {
				result2 += updatesValues[updatesValues.length / 2];
			}
		}

		System.out.println("Result 2: " + result2);
	}

	private static boolean validPair(List<Integer[]> rules, int x, int y) {
		for (Integer[] rule : rules) {
			if ((rule[0].equals(x) && rule[1].equals(y))) {
				return true;
			}
		}
		return false;
	}

	private static Integer[] updatedValues(List<Integer[]> rules, int x, int y) {
		for (Integer[] rule : rules) {
			if ((rule[0].equals(x) && rule[1].equals(y))) {
				return new Integer[] { x, y };
			} else if ((rule[0].equals(y) && rule[1].equals(x))) {
				return new Integer[] { y, x };
			}
		}

		return new Integer[] { x, y };
	}
}
