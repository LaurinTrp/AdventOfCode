package Day_11;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Globals.ResourceLoader;

public class Day_11_2025 {
	public static void main() {
		long result1 = 0;
		long result2 = 0;
		List<String> lines = ResourceLoader.getContentAsLines("2025", "Day_11" + File.separator + "Input.txt");

		Map<String, String[]> map = new HashMap<>();

		for (String line : lines) {
			String device = line.substring(0, line.indexOf(":"));
			String[] outputs = line.substring(line.indexOf(" ") + 1).split(" ");
			map.put(device, outputs);
		}

		result1 = endOnOut(map, "you");

		System.out.println("Result 1: " + result1);

		result2 = getValidPaths(map, "svr");

		System.out.println("Result 2: " + result2);
	}

	public static int endOnOut(Map<String, String[]> map, String current) {
		return endOnOut(map, current, new HashSet<>());
	}

	public static int endOnOut(Map<String, String[]> map, String current, Set<String> visited) {
		if (!visited.add(current)) {
			return 0;
		}

		String[] contents = map.get(current);

		int paths = 0;
		for (String next : contents) {
			if (next.equals("out")) {
				paths++;
			} else if (map.containsKey(next)) {
				paths += endOnOut(map, next, visited);
			}
		}

		visited.remove(current);
		return paths;

	}

	public static long getValidPaths(Map<String, String[]> graph, String start) {
	    Map<String, long[]> memo = new HashMap<>();
	    Set<String> visiting = new HashSet<>();
	    return getValidPaths(graph, start, 0, memo, visiting);
	}

	private static long getValidPaths(
	        Map<String, String[]> map,
	        String current,
	        int state,
	        Map<String, long[]> memo,
	        Set<String> visiting
	) {
	    // Cycle protection
	    if (!visiting.add(current)) {
	        return 0;
	    }

	    // Update state
	    if (current.equals("fft")) state = 1;
	    if (current.equals("dac") && state == 1) state = 2;

	    // Base case
	    if (current.equals("out")) {
	        visiting.remove(current);
	        return (state == 2) ? 1 : 0;
	    }

	    // Memo setup
	    memo.putIfAbsent(current, new long[] { -1, -1, -1 });
	    if (memo.get(current)[state] != -1) {
	        visiting.remove(current);
	        return memo.get(current)[state];
	    }

	    long total = 0;
	    String[] nexts = map.get(current);
	    if (nexts != null) {
	        for (String next : nexts) {
	            total += getValidPaths(map, next, state, memo, visiting);
	        }
	    }

	    memo.get(current)[state] = total;
	    visiting.remove(current);
	    return total;
	}

}
