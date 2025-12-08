package Day_08;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Globals.ResourceLoader;

public class Day_08_2025 {
	public static void main(String[] args) {
		long result1 = 0;
		long result2 = 0;
		List<String> lines = ResourceLoader.getContentAsLines("2025", "Day_08" + File.separator + "Input.txt");
		List<Point> boxes = lines.stream().map(line -> {
			int[] coords = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
			return new Point(coords[0], coords[1], coords[2]);
		}).toList();

		List<Connection> connections = new ArrayList<>();
		int n = boxes.size();

		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				Point p1 = boxes.get(i);
				Point p2 = boxes.get(j);

				// Euclidean distance formula
				double dist = Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2) + Math.pow(p1.z - p2.z, 2));

				connections.add(new Connection(i, j, dist));
			}
		}
		Collections.sort(connections);

		DisjointSet dsu = new DisjointSet(n);
		int connectionsToMake = 1000;

		for (int k = 0; k < connectionsToMake; k++) {
			Connection c = connections.get(k);
			dsu.union(c.indexA, c.indexB);
		}

		Map<Integer, Integer> circuitSizes = new HashMap<>();
		for (int l = 0; l < n; l++) {
			int root = dsu.find(l);
			circuitSizes.put(root, circuitSizes.getOrDefault(root, 0) + 1);
		}

		List<Integer> sizes = new ArrayList<>(circuitSizes.values());
		Collections.sort(sizes, Collections.reverseOrder());

		result1 = (long) sizes.get(0) * sizes.get(1) * sizes.get(2);
		System.out.println("Result 1: " + result1);
		
		
		dsu = new DisjointSet(n); // reset everything

		for (Connection c : connections) {
		    boolean merged = dsu.union(c.indexA, c.indexB);
		    if (merged && dsu.getNumCircuits() == 1) {
		        // This is the final connection
		        Point p1 = boxes.get(c.indexA);
		        Point p2 = boxes.get(c.indexB);
		        result2 = (long)p1.x * p2.x;
		        break;
		    }
		}
        System.out.println("Result 2: " + result2);
	}

	static class Point {
		int x, y, z;

		public Point(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	static class Connection implements Comparable<Connection> {
		int indexA;
		int indexB;
		double distance;

		public Connection(int indexA, int indexB, double distance) {
			this.indexA = indexA;
			this.indexB = indexB;
			this.distance = distance;
		}

		@Override
		public int compareTo(Connection other) {
			return Double.compare(this.distance, other.distance);
		}
	}

	static class DisjointSet {
		int[] parent;
		int numCircuits;

		public DisjointSet(int size) {
			parent = new int[size];
			for (int i = 0; i < size; i++) {
				parent[i] = i; // Initially, every box is its own circuit
			}
		    numCircuits = size;
		}

		// Find the representative ID of the circuit a box belongs to
		public int find(int i) {
			if (parent[i] != i) {
				parent[i] = find(parent[i]); // Path compression for speed
			}
			return parent[i];
		}

		// Connect two boxes
		public boolean union(int i, int j) {
            int rootA = find(i);
            int rootB = find(j);
            if (rootA != rootB) {
                parent[rootA] = rootB;
                numCircuits--; // We just merged two circuits, so total count decreases
                return true;
            }
            return false;
        }
		
		public int getNumCircuits() {
            return numCircuits;
        }
	}
}
