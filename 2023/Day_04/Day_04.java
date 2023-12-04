package Day_04;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import Loaders.ResourceLoader;

public class Day_04 {
	public static void main(String[] args) {
		List<String> lines = ResourceLoader.getContentAsLines("2023", "Day_04" + File.separator + "Input.txt");
		partOne(lines);
		partTwo(lines);
	}

	private static void partOne(List<String> lines) {
		int count = 0;

		for (String line : lines) {
			line = line.replaceAll(" +", " ");
			String[] round = line.split(": ");
			String[] winsAndNumbers = round[1].split("\\s[|]\\s");
			String[] wins = winsAndNumbers[0].split(" ");
			String[] numbers = winsAndNumbers[1].split(" ");

			Set<Integer> winsSet = convertArray(wins);
			Set<Integer> numbersSet = convertArray(numbers);

			int winningNumbers = (int) numbersSet.stream().filter(x -> {
				return winsSet.contains(x);
			}).count();

			count += winningNumbers == 1 ? 1 : Math.pow(2, winningNumbers - 1);
		}
		System.out.println(count);
	}

	private static void partTwo(List<String> lines) {

		Map<Integer, Integer> copies = new HashMap<>();

		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			line = line.replaceAll(" +", " ");
			String[] round = line.split(": ");
			String[] winsAndNumbers = round[1].split("\\s[|]\\s");
			String[] wins = winsAndNumbers[0].split(" ");
			String[] numbers = winsAndNumbers[1].split(" ");

			Set<Integer> winsSet = convertArray(wins);
			Set<Integer> numbersSet = convertArray(numbers);

			int winningNumbers = (int) numbersSet.stream().filter(x -> {
				return winsSet.contains(x);
			}).count();

			copies.put(i, winningNumbers);
		}

		Map<Integer, Integer> amounts = new HashMap<>();
		for (int i = 0; i < copies.size(); i++) {
			amounts.put(i, 1);
		}

		processCopies(copies, amounts);

		System.out.println(amounts.values().stream().reduce(0, (x, y) -> x + y));

	}

	private static void processCopies(Map<Integer, Integer> values, Map<Integer, Integer> amounts) {
		for (int i = 0; i < amounts.size(); i++) {
			int key = amounts.keySet().toArray(new Integer[] {})[i];
			
			for (int j = 0; j < amounts.get(key); j++) {
				
				for (int k = 0; k < values.get(key); k++) {
					int index = key + k + 1;
					int value = amounts.get(index);
					amounts.put(index, value + 1);
				}

			}
		}
	}

	private static Set<Integer> convertArray(String[] array) {
		Set<Integer> set = Arrays.stream(array).map(str -> {
			try {
				return Integer.parseInt(str);
			} catch (NumberFormatException e) {
				System.err.println("Error parsing integer from string: " + str);
				return null;
			}
		}).collect(Collectors.toSet());
		return set;
	}
}