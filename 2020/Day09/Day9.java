package Day09;

import java.util.ArrayList;

import Globals.FileReader;

public class Day9 {
	static final int length = 25;
	static ArrayList<String> strings = FileReader.getFileContent("Day9\\Numbers");
	static ArrayList<Integer> ints = new ArrayList<>(), ints2 = new ArrayList<>();
	static int current = length, currentValue = 0, part1Final = 0;
	static long counter = 0;
	static int[] counters = {2, 3, 4, 5, 6, 7};
	static boolean found;
	public static void main(String[] args) {
		for (int i = 0; i < strings.size(); i++) {
			method();
			current++;
		}
		
		for (int i = 0; i < counters.length; i++) {
			for (int j = 0; j < strings.size() - counters[i]; j++) {
				counter = 0;
				for (int k = 0; k < counters[i]; k++) 
					try {
						counter+= Long.parseLong(strings.get(j + k));
					} catch (Exception e) {
					}
				}
				if(counter == part1Final) {
					System.err.println("lol");
				}
			}
		}
	
	
	private static void method() {
		try {
			currentValue = Integer.parseInt(strings.get(current));
			search(current);
			ints2.clear();
			for (Integer integer : ints) {
				for (Integer integer2 : ints) {
					if(integer + integer2 == currentValue && integer != integer2 && ints2.size() == 0) {
						ints2.add(integer);
					}
				}
			}
			if(ints2.size() == 0) {
				System.err.println(currentValue);
				part1Final = currentValue;
			}
		} catch (Exception e) {
		}
		
	}
	
	private static void search(int currentLine) {
		ints.clear();
		for (int i = length; i > 0; i--) {
			ints.add(Integer.parseInt(strings.get(currentLine - i)));
		}
	}
	
}
