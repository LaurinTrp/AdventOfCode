package Day03;

import java.nio.file.Paths;
import java.util.ArrayList;

import Globals.FileReader;

public class Day03_2021 {

	public static void main(String[] args) {
		ArrayList<String> lines = FileReader.getFileContent(Paths.get("").toAbsolutePath().toString(),
				"/2021/Day03/input.txt");

		// Part 1
		int count0, count1;
		String gamma = "", epsilon = "";
		for (int i = 0; i < lines.get(0).length(); i++) {
			count0 = 0;
			count1 = 0;
			for (int j = 0; j < lines.size(); j++) {
				if (lines.get(j).charAt(i) == '1') {
					count1++;
				} else {
					count0++;
				}
			}
			gamma += (count1 > count0) ? "1" : "0";
			epsilon += (count1 < count0) ? "1" : "0";
		}
		int gammaInt = Integer.parseInt(gamma, 2);
		int epsilonInt = Integer.parseInt(epsilon, 2);
		System.out.println("Part 1: " + gammaInt * epsilonInt);

		// Part 2
		ArrayList<String> copy = new ArrayList<String>(lines);
		String oxygen = rating(copy, true);
		copy = new ArrayList<String>(lines);
		String co2Scrubber = rating(copy, false);
		
		System.out.println("Part 2: " + Integer.parseInt(oxygen, 2) * Integer.parseInt(co2Scrubber, 2));
	}

	private static String rating(ArrayList<String> list, boolean oxygen) {
		int length = list.get(0).length();
		int count0 = 0, count1 = 0;
		for (int i = 0; i < length; i++) {
			ArrayList<String> toRemove = new ArrayList<String>();
			count1 = 0;
			count0 = 0;
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j).charAt(i) == '1') {
					count1++;
				} else {
					count0++;
				}
			}
			char toCheck;
			if (oxygen)
				toCheck = (count1 >= count0) ? '1' : '0';
			else 
				toCheck = (count1 < count0) ? '1' : '0';
			
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j).charAt(i) != toCheck) {
					toRemove.add(list.get(j));
				}
			}
			list.removeAll(toRemove);
			if(list.size() == 1) {
				break;
			}
		}
		return list.get(0);
	}

}
