package Day_12;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Globals.ResourceLoader;

public class Day_12_2025 {
	public static void main(String[] args) {
		long result1 = 0;
		long result2 = 0;
		List<String> lines = ResourceLoader.getContentAsLines("2025", "Day_12" + File.separator + "Input.txt");

		Map<Integer, Integer> numberOfPresents = new HashMap<>();

		List<Long> spaces = new ArrayList<>();
		List<long[]> spots = new ArrayList<>();
		
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);

			if (line.matches("\\d*:")) {
				int id = Integer.parseInt(line.replace(":", ""));
				int counter = 1;
				int presentCount = 0;
				while (!lines.get(i + counter).isEmpty()) {
					String currLine = lines.get(i + counter);
					presentCount += currLine.length() - currLine.replace("#", "").length();

					counter++;
				}

				numberOfPresents.put(id, presentCount);
				i += counter;
			}else if(line.matches("\\d*x\\d*: .*")) {
				String[] splitted = line.split(": ");
				long space = getSpaces(splitted[0]);
				long[] presents = getPresents(splitted[1]);
				
				spaces.add(space);
				spots.add(presents);
			}
		}
		
		for (int i = 0; i < spaces.size(); i++) {
			int counter = 0;
			for (int j = 0; j < spots.get(i).length; j++) {
				counter+= spots.get(i)[j] * numberOfPresents.get(j);
			}
			if(spaces.get(i) >= counter) {
				result1++;
			}
		}
		

		System.out.println("Result 1: " + result1);
		System.out.println("Result 2: " + result2);
	}
	
	public static long getSpaces(String inp) {
		String[] splitted = inp.split("x");
		return Long.parseLong(splitted[0]) * Long.parseLong(splitted[1]);
	}
	

	public static long[] getPresents(String inp) {
		long[] presents = Arrays.stream(inp.split("\\s")).mapToLong(x -> Long.parseLong(x)).toArray();
		return presents;
	}
}
