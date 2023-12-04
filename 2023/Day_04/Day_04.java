package Day_04;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import Loaders.ResourceLoader;

public class Day_04 {
	public static void main(String[] args) {
		List<String> lines = ResourceLoader.getContentAsLines("2023", "Day_04" + File.separator + "Input.txt");
		partOne(lines);
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
			
			count += winningNumbers == 1 ? 1 : Math.pow(2, winningNumbers-1);
		}
		System.out.println(count);
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