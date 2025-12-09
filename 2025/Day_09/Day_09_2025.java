package Day_09;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

		List<int[]> edges = new ArrayList<>();

		for (int i = 0; i < points.size(); i++) {
			int[] p0 = points.get(i);
			int[] p1 = points.get((i + 1) % points.size());
			if (p0[1] == p1[1]) {
				int minX = Math.min(p0[0], p1[0]);
				int maxX = Math.max(p0[0], p1[0]);
				for (int x = minX; x <= maxX; x++) {
					edges.add(new int[]{ x, p0[1]});
				}
			}
			if (p0[0] == p1[0]) {
				int minY = Math.min(p0[1], p1[1]);
				int maxY = Math.max(p0[1], p1[1]);
				for (int y = minY; y <= maxY; y++) {
					edges.add(new int[]{ p0[0], y});
				}
			}
		}

		for (int i = 0; i < points.size(); i++) {
			int[] p0 = points.get(i);
			for (int j = i + 1; j < points.size(); j++) {
				int[] p1 = points.get(j);
				
				long x0 = p0[0];
				long y0 = p0[1];
				long x1 = p1[0];
				long y1 = p1[1];
				
				long area = (Math.abs(x0 - x1) + 1) * (Math.abs(y0 - y1) + 1);
				if(area <= result2) {
					continue;
				}
				
				long minX = Math.min(x0, x1);
				long maxX = Math.max(x0, x1);
				long minY = Math.min(y0, y1);
				long maxY = Math.max(y0, y1);
				
				boolean foundConflict = false;
				
				for (int[] edgePoint : edges) {
					if(edgePoint[0] > minX && edgePoint[0] < maxX && edgePoint[1] > minY && edgePoint[1] < maxY) {
						foundConflict = true;
						break;
					}
				}
				
				if(!foundConflict) {
					result2 = area;
				}
			}
			
		}
		System.out.println("Result 2: " + result2);

	}

}
