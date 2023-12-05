package Day_01;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Globals.ResourceLoader;

public class Day_01 {
	public static void main(String[] args) {
		List<String> lines = ResourceLoader.getContentAsLines("2023", "Day_01" + File.separator + "Input.txt");
		partOne(lines);
		partTwo(lines);
	}
	
	private static void partOne(List<String> lines) {
		Pattern pattern	 = Pattern.compile("\\d");
		int count = 0;
		for (String line : lines) {
			List<String> numbers = new ArrayList<>();
			Matcher matcher = pattern.matcher(line);
			while(matcher.find()) {
				numbers.add(matcher.group());
			}
			if(!numbers.isEmpty()) {
				count += Integer.parseInt(numbers.get(0) + "" + numbers.get(numbers.size() - 1));
			}
		}
		System.out.println(count);
	}
	
	private static void partTwo(List<String> lines) {
		Pattern p0 = Pattern.compile("one|two|three|four|five|six|seven|eight|nine");
		Pattern p1 = Pattern.compile("eno|owt|eerht|ruof|evif|xis|neves|thgie|enin");
		
		
		List<String> newLines = new ArrayList<String>();
		for (String string : lines) {
			String newLine = (new StringBuilder(findAndReplace((new StringBuilder(findAndReplace(string, p0, false)).reverse()).toString(), p1, true)).reverse()).toString();
			newLines.add(newLine);
		}
		partOne(newLines);
	}
	
	private static String findAndReplace(String input, Pattern pattern, boolean reversed) {
		Map<String, Integer> numbersAsString = new LinkedHashMap<>() {{
			put("one", 1);
			put("two", 2);
			put("three", 3);
			put("four", 4);
			put("five", 5);
			put("six", 6);
			put("seven", 7);
			put("eight", 8);
			put("nine", 9);
		}};
		String output = "";
		
		Matcher matcher = pattern.matcher(input);
		if(matcher.find()) {
			String value = matcher.group();
			output = input.replace(value, "" + numbersAsString.get(reversed ? (new StringBuilder(value).reverse()).toString() : value));
			return output;
		}
		return input;
	}
	
}
