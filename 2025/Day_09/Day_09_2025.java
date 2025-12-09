package Day_09;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.BiPredicate;

import Globals.ResourceLoader;

public class Day_09_2025 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		long result1 = 0;
		long result2 = 0;
		List<String> lines = ResourceLoader.getContentAsLines("2025", "Day_09" + File.separator + "Input.txt");

		List<int[]> points = lines.stream().map(line -> {
			int[] coords = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
			return coords;
		}).toList();

		int[] indexBiggestArea = new int[2];
		long biggestArea = 0;
		for (int i = 0; i < points.size() - 1; i++) {
			for (int j = i + 1; j < points.size(); j++) {
				int[] point0 = points.get(i);
				int[] point1 = points.get(j);
				long width = Math.abs(point0[0] - point1[0]) + 1;
				long height = Math.abs(point0[1] - point1[1]) + 1;
				long area = width * height;
				if (area > biggestArea) {
					indexBiggestArea[0] = i;
					indexBiggestArea[1] = j;
					biggestArea = area;
				}
			}
		}

		result1 = biggestArea;

		System.out.println("Result 1: " + result1);
	}

}
