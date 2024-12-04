package Day_04;

import java.io.File;
import java.util.List;
import Globals.ResourceLoader;

public class Day_04_2024 {

	private static int result1 = 0;
	private static int result2 = 0;

	public static void main(String[] args) {
		List<String> lines = ResourceLoader.getContentAsLines("2024", "Day_04" + File.separator + "Input.txt");

		int resultCounter = 0;

		int lineCounter = 0;
		for (String line : lines) {
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) == 'X') {
					checkDirection(lines, lineCounter, i, 1, 0);
					checkDirection(lines, lineCounter, i, -1, 0);
					checkDirection(lines, lineCounter, i, 0, 1);
					checkDirection(lines, lineCounter, i, 0, -1);

					checkDirection(lines, lineCounter, i, 1, 1);
					checkDirection(lines, lineCounter, i, -1, 1);
					checkDirection(lines, lineCounter, i, 1, -1);
					checkDirection(lines, lineCounter, i, -1, -1);
				}
			}
			lineCounter++;
		}

		System.out.println("Result 1: " + result1);

		lineCounter = 0;

		for (String line : lines) {
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) == 'A') {
					checkX_MAS(lines, lineCounter, i);
				}
			}
			lineCounter++;
		}
		
		System.out.println("Result 2: " + result2);
	}

	private static void checkDirection(List<String> lines, int lineCounter, int index, int directionX, int directionY) {
		char[] nextChars = { 'M', 'A', 'S' };
		for (int i = 0; i < 3; i++) {
			lineCounter += directionY;
			index += directionX;
			try {
				if (lines.get(lineCounter).charAt(index) != nextChars[i]) {
					return;
				}
			} catch (IndexOutOfBoundsException e) {
				return;
			}
		}
		result1++;
	}

	private static void checkX_MAS(List<String> line, int lineCount, int index) {
		try {
			char topLeft = line.get(lineCount - 1).charAt(index - 1);
			char topRight = line.get(lineCount - 1).charAt(index + 1);
			char bottomLeft = line.get(lineCount + 1).charAt(index - 1);
			char bottomRight = line.get(lineCount + 1).charAt(index + 1);

			if (((topLeft == 'M' && bottomRight == 'S') || (topLeft == 'S' && bottomRight == 'M'))
					&& ((topRight == 'M' && bottomLeft == 'S') || (topRight == 'S' && bottomLeft == 'M'))) {
				result2++;
			}
		} catch (IndexOutOfBoundsException e) {
			return;
		}
	}
}