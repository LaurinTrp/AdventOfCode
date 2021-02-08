package Day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day1 {
	public static void main(String args[]) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					new File("E:\\Programmieren\\Java\\W�rkbensch\\AdventOfCode\\2020\\Day1\\NumberList")));
			ArrayList<Long> numbers = new ArrayList<>();
			String line = reader.readLine();
			while (line != null) {
				numbers.add(Long.parseLong(line.trim()));
				line = reader.readLine();
			}
			for (int i = 0; i < numbers.size(); i++) {
				for (int j = 0; j < numbers.size(); j++) {
					for (int j2 = 0; j2 < numbers.size(); j2++) {
						if(numbers.get(i) + numbers.get(j) + numbers.get(j2) == 2020) {
							System.out.println(numbers.get(i) * numbers.get(j) * numbers.get(j2));
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
