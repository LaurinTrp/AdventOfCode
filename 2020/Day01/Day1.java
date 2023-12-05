package Day01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Globals.ResourceLoader;

public class Day1 {
	public static void main(String args[]) {
		List<String> lines = ResourceLoader.getContentAsLines("2020", "Day_01" + File.separator + "Input.txt");
		partOne(lines);
		partTwo(lines);
	}

	private static void partOne(List<String> lines) {
		List<Integer> numbers = lines.stream().map(x -> {
			try {
				return Integer.parseInt(x);
			} catch (Exception e) {
				return null;
			}
		}).collect(Collectors.toList());

		for (int i = 0; i < numbers.size(); i++) {
			for (int j = i; j < numbers.size(); j++) {
				if (numbers.get(i) + numbers.get(j) == 2020) {
					System.out.println(numbers.get(i) * numbers.get(j));
					return;
				}
			}
		}
	}
	private static void partTwo(List<String> lines) {
		List<Integer> numbers = lines.stream().map(x -> {
			try {
				return Integer.parseInt(x);
			} catch (Exception e) {
				return null;
			}
		}).collect(Collectors.toList());

		for (int i = 0; i < numbers.size(); i++) {
			for (int j = i; j < numbers.size(); j++) {
				for (int k = j; k < numbers.size(); k++) {
					if (numbers.get(i) + numbers.get(j) + numbers.get(k) == 2020) {
						System.out.println(numbers.get(i) * numbers.get(j) * numbers.get(k));
						return;
					}
				}
			}
		}
	}
}
