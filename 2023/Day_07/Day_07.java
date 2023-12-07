package Day_07;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import Globals.ResourceLoader;

public class Day_07 {
	public static void main(String[] args) {
		List<String> lines = ResourceLoader.getContentAsLines("2023", "Day_07" + File.separator + "Input.txt");

		partOne(lines);
		partTwo(lines);
	}

	private static void partOne(List<String> lines) {

		Map<String, Integer> values = new HashMap<>();

		Map<String, Integer> types = new LinkedHashMap<>();

		Map<Character, Integer> counts;
		for (String line : lines) {
			counts = new HashMap<>();
			String[] split = line.split(" ");
			String cards = split[0];
			values.put(cards, Integer.parseInt(split[1]));

			for (char c : cards.toCharArray()) {
				counts.put(c, counts.containsKey(c) ? counts.get(c) + 1 : 1);
			}
			types.put(cards, calculateType(counts));
		}
		
		calculateHandValue(types, values, MyComparator.charValuesPt1);
	}

	private static void partTwo(List<String> lines) {

		Map<String, Integer> values = new HashMap<>();

		Map<String, Integer> types = new LinkedHashMap<>();

		Map<Character, Integer> counts;
		for (String line : lines) {
			counts = new HashMap<>();
			String[] split = line.split(" ");
			String cards = split[0];
			values.put(cards, Integer.parseInt(split[1]));

			for (char c : cards.toCharArray()) {
				counts.put(c, counts.containsKey(c) ? counts.get(c) + 1 : 1);
			}
			
			String replacement = bestReplacement(counts, cards);
			counts.clear();
			for (char c : replacement.toCharArray()) {
				counts.put(c, counts.containsKey(c) ? counts.get(c) + 1 : 1);
			}
			types.put(cards, calculateType(counts));
		}
		calculateHandValue(types, values, MyComparator.charValuesPt2);
	}

	private static String bestReplacement(Map<Character, Integer> counts, String input) {
		counts = MapUtil.sortByValue(counts);
		Character[] keys = counts.keySet().toArray(new Character[] {});
		if (keys.length != 1 && counts.containsKey('J')) {
			char replacement = keys[keys.length - 1] != 'J' ? keys[keys.length - 1] : keys[keys.length - 2];
			input = input.replaceAll("J", Character.toString(replacement));
		}
		return input;
	}

	private static void calculateHandValue(Map<String, Integer> types, Map<String, Integer> values, Map<Character, Integer> charValues) {
		Map<String, Integer> resultMap = new LinkedHashMap<>();
		types.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.forEachOrdered(entry -> resultMap.put(entry.getKey(), entry.getValue()));

		// Group the keys by their values into a List
		List<List<String>> lists = resultMap.entrySet().stream()
				.collect(Collectors.groupingBy(Map.Entry::getValue, LinkedHashMap::new,
						Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
				.values().stream().collect(Collectors.toList());

		for (List<String> list : lists) {
			list.sort(new MyComparator(charValues));
		}

		List<String> result = lists.stream().flatMap(List::stream).collect(Collectors.toList());

		int resultValue = 0;
		for (int i = 0; i < result.size(); i++) {
			resultValue += (i + 1) * values.get(result.get(i));
		}
		System.out.println(result);
		System.out.println(resultValue);
	}

	private static int calculateType(Map<Character, Integer> counts) {
		switch (counts.size()) {
		case 1: {
			return 8;
		}
		case 2: {
			if (counts.values().contains(1)) {
				return 7;
			}
			if(counts.values().contains(3) && counts.values().contains(2)) {
				return 6;
			}
			return 5;
		}
		case 3: {
			if (counts.values().contains(3)) {
				return 4;
			}
			return 3;
		}
		case 4: {
			return 2;
		}
		case 5: {
			return 1;
		}
		default:
			return -1;
		}
	}
}