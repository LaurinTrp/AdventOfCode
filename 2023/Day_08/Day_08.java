package Day_08;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import Globals.ResourceLoader;

public class Day_08 {

	private static char[] directions;
	private static Map<String, String[]> navigations = new HashMap<>();

	public static void main(String[] args) {
		List<String> lines = ResourceLoader.getContentAsLines("2023", "Day_08" + File.separator + "Input.txt");

		lines = lines.stream().filter(x -> !x.isBlank()).collect(Collectors.toList());
		directions = lines.get(0).toCharArray();

		Pattern pattern = Pattern.compile("\\w{3}");
		Matcher matcher;
		for (int i = 1; i < lines.size(); i++) {
			matcher = pattern.matcher(lines.get(i));
			String[] matches = new String[3];
			for (int j = 0; j < matches.length; j++) {
				matcher.find();
				matches[j] = matcher.group();
			}
			navigations.put(matches[0], new String[] { matches[1], matches[2] });
		}

//		partOne();
		partTwo();
	}

	private static void partOne() {
		String currentNav = "AAA";
		System.out.println(calculateStepsPt1(currentNav, "ZZZ"));
	}

	private static void partTwo() {
		List<String> currentNodes = navigations.keySet().stream().filter(x -> x.endsWith("A"))
				.collect(Collectors.toList());
		List<Integer> movesList = new ArrayList<>();
		String pattern = "\\w{2}Z";
		for (int i = 0; i < currentNodes.size(); i++) {
			String node = currentNodes.get(i);
			Map<String, Integer> newValues = calculateStepsPt2(node, pattern, 0);
			String[] options = navigations.get(newValues.keySet().toArray()[0]);
			int moves = newValues.values().toArray(new Integer[] {})[0];

			newValues = calculateStepsPt2(options[moves % directions.length], pattern, moves-1);
			moves = newValues.values().toArray(new Integer[] {})[0] + 1;
			movesList.add(moves);
		}
		System.out.println(LCMCalculator.calculateLCM(movesList.stream().mapToInt(Integer::intValue).toArray()));
	}

	private static Map<String, Integer> calculateStepsPt2(String start, String end, int m) {
		Map<String, Integer> output = new HashMap<>();
		String currentNav = start;
		int i = m;
		int moves = 0;
		while (!currentNav.matches(end)) {
			i %= directions.length;
			String[] options = navigations.get(currentNav);
			currentNav = directions[i] == 'L' ? options[0] : options[1];

			i++;
			moves++;
		}
		output.put(currentNav, moves);
		return output;
	}

	private static int calculateStepsPt1(String start, String end) {
		String currentNav = start;
		int i = 0;
		int moves = 0;
		while (!currentNav.matches(end)) {
			i %= directions.length;
			String[] options = navigations.get(currentNav);
			currentNav = directions[i] == 'L' ? options[0] : options[1];

			i++;
			moves++;
		}
		System.out.println(currentNav);
		return moves;
	}
}