package Day05;

import java.util.ArrayList;
import java.util.Collections;

import Globals.FileReader;

public class Day5 {

	public static void main(String[] args) {
		ArrayList<String> strings = FileReader.getFileContent("Day5\\Seats");
		ArrayList<String[]> stringsArray = new ArrayList<>();
		for (String string : strings) {
			stringsArray.add(string.split(""));
		}
		int lower = 0, upper = 127, middle = 0;
		ArrayList<Integer> finalValuesRow = new ArrayList<>(), finalValuesColumn = new ArrayList<>(),
				finalValues = new ArrayList<>();
		for (String[] string : stringsArray) {
			lower = 0;
			upper = 127;
			for (int i = 0; i < 7; i++) {
				if (i != 7) {
					if (string[i].equals("F")) {
						middle = (int) ((lower + upper) / 2);
						upper = middle;
					} else if (string[i].equals("B")) {
						middle = (int) ((lower + upper) / 2) + 1;
						lower = middle;
					}
				}
			}
			finalValuesRow.add(middle);
			lower = 0;
			upper = 7;

			for (int i = 0; i < 3; i++) {
				if (i != 3) {
					if (string[i + 7].equals("L")) {
						middle = (int) ((lower + upper) / 2);
						upper = middle;
					} else if (string[i + 7].equals("R")) {
						middle = (int) ((lower + upper) / 2) + 1;
						lower = middle;
					}
				}
			}
			finalValuesColumn.add(middle);
		}
		
		for (int i = 0; i < finalValuesRow.size(); i++) {
			finalValues.add(finalValuesRow.get(i) * 8 + finalValuesColumn.get(i));
		}
		Collections.sort(finalValues);
		System.out.println("Highest seat: " + finalValues.get(finalValues.size() - 1));
		
		for (int i = 1; i < finalValues.size(); i++) {
			if(finalValues.get(i) != finalValues.get(i - 1) + 1) {
				System.out.println("My seat: " + (finalValues.get(i) - 1));
				break;
			}
		}


	}
}
