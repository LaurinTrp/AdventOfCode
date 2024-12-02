package Day_01;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import Globals.ResourceLoader;

public class Day_01_2024 {
	public static void main(String[] args){
		List<String> lines = ResourceLoader.getContentAsLines("2024", "Day_01" + File.separator + "Input.txt");
		
		List<Integer> left = new ArrayList<>();
		List<Integer> right = new ArrayList<>();
		
		lines.forEach(line -> {
			String[] split = line.split("\\s{3}");
			left.add(Integer.parseInt(split[0]));
			right.add(Integer.parseInt(split[1]));
		});
		
		List<Integer> leftSorted = left.stream().sorted().collect(Collectors.toList()); //left.sort((a, b) -> a.compareTo(b));
		List<Integer> rightSorted = right.stream().sorted().collect(Collectors.toList());
		
		int sum = 0;
		for (int i = 0; i < leftSorted.size(); i++) {
			sum += Math.abs(leftSorted.get(i) - rightSorted.get(i));
		}
		System.out.println("Part 1: " + sum);
		
		int[] similarityScore = {0};
		left.forEach(x -> {
			similarityScore[0] += x * right.stream().filter(y -> x.equals(y)).count();
		});
		
		System.out.println("Part 2: " + similarityScore[0]);
	}
}