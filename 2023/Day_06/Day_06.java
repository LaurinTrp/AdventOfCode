package Day_06;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Globals.ResourceLoader;

public class Day_06 {
	
	private static List<Long> times = new ArrayList<>();
	private static List<Long> records = new ArrayList<>();
	
	public static void main(String[] args){
		List<String> lines = ResourceLoader.getContentAsLines("2023", "Day_06" + File.separator + "Input.txt");
	
		fillListPt1(lines.get(0), times);
		fillListPt1(lines.get(1), records);	
		
		calculateResult();
		times.clear();
		records.clear();

		fillListPt2(lines.get(0), times);
		fillListPt2(lines.get(1), records);	
		calculateResult();
	}
	
	private static void calculateResult() {
		int result = 1;
		for (int i = 0; i < times.size(); i++) {
			int counter = 0;
			for (int j = 0; j < times.get(i) + 1; j++) {
				long holdTime = j;
				long speed = j;
				
				long distance = speed * (times.get(i) - holdTime);
				if(distance > records.get(i)) {
					counter++;
				}
			}
			result *= counter;
		}
		System.out.println(result);
	}
	
	
	private static void fillListPt1(String input, List<Long> list) {
		Pattern pattern = Pattern.compile("\\b\\d+\\b");
		Matcher matcher = pattern.matcher(input);
		while(matcher.find()) {
			list.add(Long.parseLong(matcher.group().strip()));
		}
	}
	private static void fillListPt2(String input, List<Long> list) {
		Pattern pattern = Pattern.compile("\\b\\d+\\b");
		Matcher matcher = pattern.matcher(input.replaceAll(" ", ""));
		while(matcher.find()) {
			list.add(Long.parseLong(matcher.group().strip()));
		}
	}
}