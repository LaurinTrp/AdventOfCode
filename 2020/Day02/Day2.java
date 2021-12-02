package Day02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day2 {
	public static void main(String[] args) {

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					new File("E:\\Programmieren\\Java\\W�rkbensch\\AdventOfCode\\2020\\Day2\\PasswordList")));
			ArrayList<String[]> strings = new ArrayList<>();
			String line = reader.readLine();
			while (line != null) {
				strings.add(line.split(":"));
				line = reader.readLine();
			}
			int lower, upper, valids = 0;
			char searched;
			for (String[] string : strings) {
				for (int i = 0; i < string.length; i++) {
					string[i] = string[i].trim();
				}
			}
			for (String[] string : strings) {
				lower = Integer.parseInt(string[0].split("-")[0]);
				upper = Integer.parseInt(string[0].split("-")[1].split(" ")[0]);
				searched = string[0].charAt(string[0].length() - 1);

				if (string[1].charAt(lower-1) != string[1].charAt(upper - 1)) {
					if (string[1].charAt(lower - 1) == searched || string[1].charAt(upper - 1) == searched) {
						valids++;
						System.out.println(lower + "\t" + upper + "\t" + searched + "\t" + string[1] + "\t" + valids);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
