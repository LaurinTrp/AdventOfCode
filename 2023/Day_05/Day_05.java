package Day_05;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import Globals.ResourceLoader;

public class Day_05 {

	private static List<Long> seeds = new ArrayList<>();

	public static void main(String[] args) {
		String content = ResourceLoader.getFileContentAsString("2023", "Day_05" + File.separator + "Input.txt");
		partOne(content);
		seeds.clear();
		partTwo(content);
	}

	private static void partOne(String content) {
		String[] splitted = content.split("\n\n");

		Pattern patternSeeds = Pattern.compile("\\b\\d+\\b");
		Matcher matcherSeeds = patternSeeds.matcher(splitted[0]);
		while (matcherSeeds.find()) {
			seeds.add(Long.parseLong(matcherSeeds.group().strip()));
		}
		
		calculateMinLocation(splitted);
	}
	
	private static void partTwo(String content) {
		String[] splitted = content.split("\n\n");

		Pattern patternSeeds = Pattern.compile("\\d+\\s\\d+");
		Matcher matcherSeeds = patternSeeds.matcher(splitted[0]);
		while (matcherSeeds.find()) {
			String match = matcherSeeds.group();
			long[] values = new long[] {
					Long.parseLong(match.split(" ")[0]),
					Long.parseLong(match.split(" ")[1]),
			};
			for (long i = values[0]; i < values[0] + values[1]; i++) {
				seeds.add(i);
			}
			calculateMinLocation(splitted);
			seeds.clear();
		}
//		System.out.println(seeds);
		
	}
	
	private static void calculateMinLocation(String[] splitted) {
		Long min = Long.MAX_VALUE;
		for (Long seed : seeds) {
			long value = seed;
			for (int i = 1; i < splitted.length; i++) {
				List<Long[]> boundaries = getValues(splitted[i]);
				for (int j = 0; j < boundaries.size(); j++) {
					Long[] boundary = boundaries.get(j);
					if(value >= boundary[1] && value <= boundary[1] + boundary[2] - 1) {
						long offset = Math.abs(boundary[1] - value);
						value = boundary[0] + offset;
						break;
					}
				}
			}
			min = Math.min(value, min);
		}
		System.out.println(min);
	}
	
	private static List<Long[]> getValues(String subset) {
		List<Long[]> out = new ArrayList<>();
		Pattern pattern = Pattern.compile("(\\d+\\s?){3}");
		Matcher matcher = pattern.matcher(subset);
		while (matcher.find()) {
			Long[] values = new Long[3];
			String[] match = matcher.group().trim().split(" ");
			for (int i = 0; i < values.length; i++) {
				values[i] = Long.parseLong(match[i]);
			}
			out.add(values);
		}
		return out;
	}
	
}