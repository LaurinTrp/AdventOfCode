package Day_04;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import Globals.ResourceLoader;

public class Day_04_2025 {
	public static void main(String[] args) {
		int result1 = 0;
		int result2 = 0;
		List<String> lines = ResourceLoader.getContentAsLines("2025", "Day_04" + File.separator + "Input.txt");
		char[][] grid = lines.stream().map(x -> x.toCharArray()).toArray(char[][]::new);
		char[][] newGrid = lines.stream().map(x -> x.toCharArray()).toArray(char[][]::new);
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[r].length; c++) {
				if (grid[r][c] == '@' && isAvailable(grid, r, c)) {
					result1++;
					newGrid[r][c] = 'X';
				}
			}
		}

		System.out.println("Result 1: " + result1);

		List<int[]> listToRemove = new ArrayList<>();
		do {
			listToRemove.clear();
			for (int r = 0; r < grid.length; r++) {
				for (int c = 0; c < grid[r].length; c++) {
					if (grid[r][c] == '@' && isAvailable(grid, r, c)) {
						result2++;
						listToRemove.add(new int[] { r, c });
					}
				}
			}
			for (int[] remove : listToRemove) {
				grid[remove[0]][remove[1]] = '.';
			}
		} while (!listToRemove.isEmpty());

		System.out.println("Result 2: " + result2);
	}

	private static boolean isAvailable(char[][] grid, int row, int column) {
		int counter = 0;
		for (int r = row - 1; r <= row + 1; r++) {
			for (int c = column - 1; c <= column + 1; c++) {
				try {
					boolean isNotSameRow = r != row;
					boolean isNotSameColumn = c != column;
					if ((isNotSameRow || isNotSameColumn) && grid[r][c] == '@') {
						counter++;
					}
				} catch (IndexOutOfBoundsException e) {
				}
			}
		}
		return counter < 4;
	}
}
