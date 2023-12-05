package Day02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import Loaders.ResourceLoader;

public class Day2 {
	public static void main(String[] args) {

		List<String> lines = ResourceLoader.getContentAsLines("2020", "Day_02" + File.separator + "Test.txt");

		partOne(lines);
	}

	private static void partOne(List<String> lines) {
		List<String[]> splittedList = lines.stream().map(x -> {
			return x.split(":");
		}).collect(Collectors.toList());
		
		int lower, upper, valids = 0;
		String searched[] = new String[1];
		Pattern patternNumber = Pattern.compile("\\d{1,}");
		for (String[] splitted : splittedList) {
			Matcher matcher = patternNumber.matcher(splitted[0]);
			matcher.find();
			
			lower = Integer.parseInt(matcher.group());
			upper = Integer.parseInt(matcher.group());
			searched[0] = splitted[0].substring(splitted[0].length() - 1);

			long count = splitted[1].chars().filter(x -> {
				return x == searched[0].charAt(0);
			}).count();
			
			if(count >= lower && count <= upper) {
				valids++;
			}
		}
		System.out.println(valids);
	}
}
