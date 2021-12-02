package Day01;

import java.nio.file.Paths;
import java.util.ArrayList;

import Globals.FileReader;

public class Day1_2021 {
	public static void main(String[] args) {
		ArrayList<String> lines = FileReader.getFileContent(Paths.get("").toAbsolutePath().toString(), "/2021/Day1/input.txt");
		ArrayList<Integer> values = new ArrayList();
		for (String line : lines) {
			values.add(Integer.parseInt(line));
		}
		
		int counter = 0;
		for (int i = 1; i < values.size(); i++) {
			if(values.get(i) > values.get(i-1)) {
				counter++;
			}
		}
		System.out.println(counter);
		
		int sum1, sum2;
		counter = 0;
		for (int i = 0; i < values.size()-3; i++) {
			sum1 = values.get(i) + values.get(i+1) + values.get(i+2);
			sum2 = values.get(i+1) + values.get(i+2) + values.get(i+3);
			if(sum2 > sum1) {
				counter++;
			}
		}
		System.out.println(counter);
	}
}
