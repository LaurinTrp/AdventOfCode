package Day_02;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import Globals.ResourceLoader;

public class Day_02_2024 {
	public static void main(String[] args) {
		List<String> lines = ResourceLoader.getContentAsLines("2024", "Day_02" + File.separator + "Input.txt");

		List<List<Integer>> lists = lines.stream().map(
				line -> Arrays.stream(line.split("\\s")).map(x -> Integer.parseInt(x)).collect(Collectors.toList()))
				.collect(Collectors.toList());

		int part1Solution = (int) lists.stream().filter(list -> {
			return isSafe(list);
		}).count();
		
		System.out.println("Part 1: " + part1Solution);
		
		int part2Solution = (int) lists.stream().filter(list -> {
			for (int i = 0; i < list.size(); i++) {
				List<Integer> newList = new ArrayList<>(list);
				newList.remove(i);
				if(isSafe(newList)) {
					return true;
				}
			}
			return false;
		}).count();
		
		System.out.println("Part 2: " + part2Solution);
	}
	
	private static boolean isSafe(List<Integer> list) {
		boolean rightOrder = false;
		List<Integer> sorted = list.stream().sorted().collect(Collectors.toList());
		List<Integer> reversed = new ArrayList<>(sorted);
		Collections.reverse(reversed);
		rightOrder = (list.equals(sorted) || list.equals(reversed));
		
		boolean distance = !IntStream.range(0, list.size() - 1)
				.anyMatch(i -> Math.abs(list.get(i) - list.get(i + 1)) > 3 || Math.abs(list.get(i) - list.get(i + 1)) < 1);
		
		return rightOrder && distance;
		
	}
}