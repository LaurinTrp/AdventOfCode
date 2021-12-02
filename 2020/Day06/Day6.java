package Day06;

import java.util.ArrayList;
import java.util.HashSet;

import Globals.FileReader;

public class Day6 {
	public static void main(String[] args) {

		ArrayList<String> strings = FileReader.getFileContent("Day6\\Answers"), answer = new ArrayList<>();
		ArrayList<String[]> stringArrays = new ArrayList<>();
		String lines = "";
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

		for (int i = 0; i < strings.size(); i++) {
			lines = "";
			if (strings.get(i).equals("")) {
				for (int j = i - 1; j > 0; j--) {
					lines += strings.get(j) + " ";
					if (strings.get(j).equals("")) {
						break;
					}
				}
			}
			if (i == strings.size() - 1) {
				for (int j = i; j > 0; j--) {
					lines += strings.get(j) + " ";
					if (strings.get(j).equals("")) {
						break;
					}
				}
			}
			if (!lines.equals("")) {
				stringArrays.add(lines.split(" "));
				answer.add(lines.replaceAll(" ", ""));
			}
		}
		HashSet<String> set = new HashSet<>();
		Object[] setArray = null;
		String line = "";
		for (int j = 0; j < answer.size(); j++) {
			set.clear();
			line = "";
			for (int i = 0; i < answer.get(j).length(); i++) {
				set.add(Character.toString(answer.get(j).charAt(i)));
				setArray = set.toArray();
			}
			for (int i = 0; i < setArray.length; i++) {
				line += setArray[i];
			}
			answer.set(j, line);
		}
		int counter = 0;
		for (String string : answer) {
			counter += string.length();
		}
		System.err.println(counter);
		int counterAll = 0;
		
		for (String[] string : stringArrays) {
			counter=0;
			for (int i = 0; i < alphabet.length; i++) {
				if(allContain(string, alphabet[i])) {
					counter++;
				}
			}
			counterAll += counter;
		}
		
		System.err.println(counterAll);
		
		
	}
	
	private static boolean allContain(String[] string, char c) {
		switch (string.length) {
		case 10: {
			if(!string[9].contains(Character.toString(c))) {
				return false;
			}
		}
		case 9: {
			if(!string[8].contains(Character.toString(c))) {
				return false;
			}
		}
		case 8: {
			if(!string[7].contains(Character.toString(c))) {
				return false;
			}
		}
		case 7: {
			if(!string[6].contains(Character.toString(c))) {
				return false;
			}
		}
		case 6: {
			if(!string[5].contains(Character.toString(c))) {
				return false;
			}			
		}
		case 5: {
			if(!string[4].contains(Character.toString(c))) {
				return false;
			}
		}
		case 4: {
			if(!string[3].contains(Character.toString(c))) {
				return false;
			}
		}
		case 3: {
			if(!string[2].contains(Character.toString(c))) {
				return false;
			}
		}
		case 2: {
			if(!string[1].contains(Character.toString(c))) {
				return false;
			}
		}
		case 1: {
			if(!string[0].contains(Character.toString(c))) {
				return false;
			}
			return true;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + string.length);
		}
	}
	
}
