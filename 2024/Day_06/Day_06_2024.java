package Day_06;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import Globals.ResourceLoader;

public class Day_06_2024 {
	public static void main(String[] args) {
		int result1 = 0;
		int result2 = 0;
		List<String> lines = ResourceLoader.getContentAsLines("2024", "Day_06" + File.separator + "Test.txt");

		result1 = part1(lines);
		result2 = part2(lines);

		System.out.println("Result 1: " + result1);
//		System.out.println("Result 2: " + result2);
	}

	private static int part1(List<String> lines) {
		int result = 0;

		int[] sequence = { 0, 1, 0, -1 };
		int seqIndexX = 0;
		int seqIndexY = 3;

		int positionX = 0, positionY = 0;
		int directionX = 0, directionY = -1;

		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			if (line.contains("^")) {
				positionX = line.indexOf('^');
				positionY = i;
			}
		}

		List<String[]> maze = lines.stream().map(x -> x.split("")).collect(Collectors.toList());
		boolean run = true;
		while (run) {
			try {
				if (!maze.get(positionY + directionY)[positionX + directionX].equals("#")) {
					maze.get(positionY)[positionX] = "X";
					positionX += directionX;
					positionY += directionY;
				} else {
					seqIndexX = (seqIndexX + 1) % sequence.length;
					seqIndexY = (seqIndexY + 1) % sequence.length;
					directionX = sequence[seqIndexX];
					directionY = sequence[seqIndexY];
				}
			} catch (IndexOutOfBoundsException e) {
				run = false;
				maze.get(positionY)[positionX] = "X";
				break;
			}
		}

		for (String[] row : maze) {
			for (String string : row) {
				if (string.equals("X")) {
					result++;
				}
			}
		}

		return result;
	}

	private static int part2(List<String> lines) {
		int result = 0;

		int[] sequence = { 0, 1, 0, -1 };
		int seqIndexX = 0;
		int seqIndexY = 3;

		int positionX = 0, positionY = 0;
		int directionX = 0, directionY = -1;

		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			if (line.contains("^")) {
				positionX = line.indexOf('^');
				positionY = i;
			}
		}

		List<Point> lastHits = new ArrayList<>();
		List<Point> allHits = new ArrayList<>();

		List<String[]> maze = lines.stream().map(x -> x.split("")).collect(Collectors.toList());
		boolean run = true;
		while (run) {
			try {
				if (!maze.get(positionY + directionY)[positionX + directionX].equals("#")) {
					positionX += directionX;
					positionY += directionY;
				} else {
					lastHits.add(new Point(positionX + directionX, positionY + directionY));
					allHits.add(new Point(positionX + directionX, positionY + directionY));
					seqIndexX = (seqIndexX + 1) % sequence.length;
					seqIndexY = (seqIndexY + 1) % sequence.length;
					directionX = sequence[seqIndexX];
					directionY = sequence[seqIndexY];

					if (lastHits.size() == 3) {
						System.out.println(lastHits);
						System.out.println(getCollision(lastHits));
						Point newColl = getCollision(lastHits);
						maze.get(newColl.y)[newColl.x] = "Ä";
						int seqIndexX2 = (seqIndexX + 1) % sequence.length;
						int seqIndexY2 = (seqIndexY + 1) % sequence.length;
						int directionX2 = sequence[seqIndexX2];
						int directionY2 = sequence[seqIndexY2];
						
						Point nextCollPoint = nextCollision(maze, new Point(newColl.x - directionX, newColl.y-directionY),
								new Point(directionX2, directionY2));

						for (String[] strings : maze) {
							System.out.println(Arrays.toString(strings));
						}
						
						System.out.println(nextCollPoint.equals(lastHits.get(0)));
						System.out.println();

						lastHits.remove(0);
					}

				}
			} catch (IndexOutOfBoundsException e) {
				run = false;
				break;
			}
		}

		for (String[] row : maze) {
			for (String string : row) {
				if (string.equals("X")) {
					result++;
				}
			}
		}

		return result;
	}

	private static Point nextCollision(List<String[]> maze, Point start, Point direction) {
		int positionX = start.x;
		int positionY = start.y;
		int directionX = direction.x;
		int directionY = direction.y;

		boolean run = true;
		while (run) {
			try {
				System.out.println(maze.get(positionY + directionY)[positionX + directionX]);
				if (!maze.get(positionY + directionY)[positionX + directionX].equals("#")) {
					positionX += directionX;
					positionY += directionY;
					maze.get(positionY)[positionX] = "Ö";

					for (String[] strings : maze) {
						System.out.println(Arrays.toString(strings));
					}
					System.out.println();
				} else {
					return new Point(positionX + directionX, positionY + directionY);
				}
			} catch (IndexOutOfBoundsException e) {
				run = false;
				break;
			}
		}

		return new Point(-1, -1);
	}

	private static Point getCollision(List<Point> lastHits) {
		Point p0 = lastHits.get(0);
		Point p1 = lastHits.get(1);
		Point p2 = lastHits.get(2);

		Point delta = new Point(p0.x - p1.x, p0.y - p1.y);
		Point p3 = new Point(p2.x + delta.x, p2.y + delta.y);
		return p3;
	}
}
