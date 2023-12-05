package Day_02;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Globals.ResourceLoader;

public class Day_02 {
	public static void main(String[] args) {
		List<String> lines = ResourceLoader.getContentAsLines("2023", "Day_02" + File.separator + "Input.txt");

		partOne(lines);
		partTwo(lines);
	}

	private static void partOne(List<String> lines) {
		int maxRed = 12, maxGreen = 13, maxBlue = 14;

		int totalRounds = 0;
		
		for (String line : lines) {
			String[] splitted = line.split(":");
			int round = Integer.parseInt(splitted[0].split(" ")[1]);
			List<Integer[]> counts = counts(splitted[1].split(";"));
			boolean pass = true;
			for (Integer[] integers : counts) {
				if (integers[0] > maxRed || integers[1] > maxGreen || integers[2] > maxBlue) {
					pass = false;
				}
			}
			if (pass) {
				totalRounds += round;
			}
		}

		System.out.println("Part one: " + totalRounds);
	}
	
	private static void partTwo(List<String> lines) {
		int totalPower = 0;
		for (String line : lines) {
			String[] splitted = line.split(":");
			int round = Integer.parseInt(splitted[0].split(" ")[1]);
			List<Integer[]> counts = counts(splitted[1].split(";"));
			
			int minRed = Integer.MIN_VALUE, minGreen = Integer.MIN_VALUE, minBlue = Integer.MIN_VALUE;
			for (Integer[] integers : counts) {
				minRed = Math.max(minRed, integers[0]);
				minGreen = Math.max(minGreen, integers[1]);
				minBlue = Math.max(minBlue, integers[2]);
			}
			totalPower += minRed * minGreen * minBlue;
		}
		
		System.out.println("Part two: " + totalPower);
	}

	private static List<Integer[]> counts(String[] rounds) {
		List<Integer[]> counts = new ArrayList<>();
		for (int i = 0; i < rounds.length; i++) {
			String string = rounds[i];
			Integer[] countsArray = { 0, 0, 0 };
			String[] subRounds = string.split(", ");
			for (int j = 0; j < subRounds.length; j++) {

				subRounds[j] = subRounds[j].trim();
				String[] values = subRounds[j].split(", ");
				for (int k = 0; k < values.length; k++) {
					String[] split = values[k].split(" ");

					if (split[1].equals("red")) {
						countsArray[0] = Integer.parseInt(split[0]);
					}
					if (split[1].equals("green")) {
						countsArray[1] = Integer.parseInt(split[0]);
					}
					if (split[1].equals("blue")) {
						countsArray[2] = Integer.parseInt(split[0]);
					}
				}

			}
			counts.add(countsArray);

		}
		return counts;
	}
}
