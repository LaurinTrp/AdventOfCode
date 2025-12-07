package Day_07;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Globals.ResourceLoader;

public class Day_07_2025 {
	public static void main(String[] args) {
		int result1 = 0;
		long result2 = 0;
		List<String> lines = ResourceLoader.getContentAsLines("2025", "Day_07" + File.separator + "Input.txt");
		char[][] gridPt1 = lines.stream().map(x -> x.toCharArray()).toArray(char[][]::new);

		Point startPoint = new Point();
		for (int i = 0; i < gridPt1[0].length; i++) {
			if (gridPt1[0][i] == 'S') {
				startPoint.setLocation(i, 0);
			}
		}

		List<Point> points = new ArrayList<>();
		points.add(startPoint);
		for (int y = 1; y < gridPt1.length; y++) {
			for (Point point : points) {
				if (gridPt1[y][point.x] == '^') {
					result1++;
					gridPt1[y][point.x - 1] = '|';
					gridPt1[y][point.x + 1] = '|';
				} else {
					gridPt1[y][point.x] = '|';
				}
			}
			points.clear();
			for (int x = 0; x < gridPt1[y].length; x++) {
				if (gridPt1[y][x] == '|') {
					points.add(new Point(x, y));
				}
			}
		}

		System.out.println("Result 1: " + result1);

		char[][] gridPt2 = lines.stream().map(x -> x.toCharArray()).toArray(char[][]::new);

		Map<Long, Long> memo = new HashMap<>();

		startPoint.y = 1;
		
		result2 = timelines(gridPt2, memo, startPoint);
		
		System.out.println("Result 2: " + result2);
	}

	public static long timelines(char[][] grid, Map<Long, Long> memo, Point current) {
		long key = (((long) current.y) << 32) ^ (current.x & 0xffffffffL);
		if (memo.containsKey(key)) {
			return memo.get(key);
		}

		int treeHeight = grid.length;

		while (grid[current.y][current.x] == '.') {
			current.setLocation(current.x, current.y + 1);;
			if (current.y == treeHeight - 1) {
				memo.put(key, 1l);
				return 1;
			}
		}

		long result = timelines(grid, memo, new Point(current.x - 1, current.y))
				+ timelines(grid, memo, new Point(current.x + 1, current.y));
		memo.put(key, result);
		return result;
	}
}
