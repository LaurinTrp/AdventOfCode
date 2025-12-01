package Day_01;

import java.io.File;
import java.util.List;
import Globals.ResourceLoader;

public class Day_01_2025 {
	public static void main(String[] args) {
		int result1 = 0;
		int result2 = 0;
		List<String> lines = ResourceLoader.getContentAsLines("2025", "Day_01" + File.separator + "Input.txt");

		int value = 50;
		for (String line : lines) {
			char dir = line.charAt(0);
			int steps = Integer.parseInt(line.substring(1));
			if (dir == 'L') {
				steps = -steps;
			}
			value += steps;
			value = ((value % 100) + 100) % 100;
			if (value == 0) {
				result1++;
			}
		}

		System.out.println("Result 1: " + result1);

		value = 50;
		for (String line : lines) {
			char dir = line.charAt(0);
			int steps = Integer.parseInt(line.substring(1));

			if (dir == 'L') {
				steps = -steps;
			}

			if (value == 0 && value + steps < 0) {
				value += 100;
			}

			value += steps;

			while (value < 0) {
				value += 100;
				result2++;
			}

			if (value == 0) {
				result2++;
			}

			while (value > 99) {
				value -= 100;
				result2++;
			}

		}

		System.out.println("Result 2: " + result2);
	}
}
