package Day_03;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Loaders.ResourceLoader;

public class Day_03_2022 {
	public static void main(String[] args) {
		List<String> lines = ResourceLoader.getContentAsLines("2022", "Day_03" + File.separator + "Input.txt");
		second(lines);
	}

	private static void first(List<String> lines) {
		List<Character> doublicates = new ArrayList<>();

		for (int i = 0; i < lines.size(); i++) {
			String sub0 = lines.get(i).substring(0, lines.get(i).length() / 2);
			String sub1 = lines.get(i).substring(lines.get(i).length() / 2, lines.get(i).length());

			for (int j = 0; j < sub0.length(); j++) {
				if (sub1.contains(sub0.charAt(j) + "")) {
					doublicates.add(sub0.charAt(j));
					break;
				}
			}
		}

		int sum = 0;

		for (Character d : doublicates) {
			sum += charToInt(d);
		}
		System.out.println(sum);
	}
	
	private static int charToInt(Character d) {
		int value = d - 97 + 1;
		if (value <= 0) {
			char c = d.toLowerCase(d);
			value = c - 97 + 1 + 26;
		}
		return value;
	}
	
	private static void second(List<String> lines) {
		
		List<Character> badges = new ArrayList<>();
		
		for (int i = 0; i < lines.size(); i+=3) {
			for (int j = 0; j < lines.get(i).length(); j++) {
				if(lines.get(i+1).contains(lines.get(i).charAt(j) + "") &&
						lines.get(i+2).contains(lines.get(i).charAt(j) + "")) {
					badges.add(lines.get(i).charAt(j));
					break;
				}
			}
		}
		
		int sum = 0;
		
		for (Character badge : badges) {
			sum += charToInt(badge);
		}
		
		System.out.println(sum);
	}
}
